package neointernship.web.client.player.bot.ai.decisiontree;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.moveaction.commands.IMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.player.bot.ai.decisiontree.base.Node;
import neointernship.web.client.player.bot.ai.decisiontree.base.Tree;
import neointernship.web.client.player.bot.ai.positionvalue.IValueCalculator;
import neointernship.web.client.player.bot.ai.positionvalue.PositionValueCalculator;


public class DecisionTreeCreator {
    private Tree decisionTree = null;
    private final Color rootColor;
    private final IValueCalculator valueCalculator;

    public DecisionTreeCreator(final Color color) {
        this.rootColor = color;

        valueCalculator = new PositionValueCalculator(rootColor);
    }

    public void createNewTree(final IMediator mediator,
                              final IBoard board,
                              final IStoryGame storyGame,
                              final int recursionDepth) {
        decisionTree = new Tree(null, mediator, board, storyGame, rootColor);
        int a = nextLayer(decisionTree.getRoot(), recursionDepth, Integer.MIN_VALUE+1, Integer.MAX_VALUE-1);
        System.out.format("\n\nON ANSWER: %d\n\n", a);
    }

    public int nextLayer(final Node currentNode, final int recursionDepth, int alpha, int beta) {
        final IMediator mediator = currentNode.getMediator();
        final IBoard board = currentNode.getBoard();
        final IStoryGame storyGame = currentNode.getStoryGame();

        if (recursionDepth == 0) {
            return valueCalculator.calculate(board, mediator, storyGame, currentNode.getActiveColor());
        }

        int currentScore = Integer.MIN_VALUE;
        final Color activeColor = currentNode.getActiveColor();
        final IPossibleActionList possibleActionList = new PossibleActionList(board, mediator, storyGame);
        possibleActionList.updateRealLists();

        for (final Figure figure : mediator.getFigures(activeColor)) {
            for (final IField field : possibleActionList.getRealList(figure)) {
                final IMediator currentMediator = new Mediator(currentNode.getMediator());
                final IMoveCommand command = new AllowMoveCommand(currentMediator, possibleActionList, board, storyGame);
                final IAnswer answer = new Answer(currentMediator.getField(figure).getXCoord(),
                                                  currentMediator.getField(figure).getYCoord(),
                                                  field.getXCoord(),
                                                  field.getYCoord(), 'Q');
                command.execute(answer);

                final Node childNode = new Node(answer,
                                                currentMediator,
                                                board,
                                                storyGame,
                                                Color.swapColor(activeColor));

                final int tmp = - nextLayer(childNode, recursionDepth - 1, - beta, - alpha);

                if (tmp > currentScore) {
                    currentScore = tmp;
                    currentNode.setChild(childNode);
                }
                if (currentScore > alpha) {
                    alpha = currentScore;
                    currentNode.setChild(childNode);
                }
                if (alpha >= beta) {
                    currentNode.setChild(childNode);
                    return alpha;
                }
            }
        }

        return currentScore;
    }

    public Tree getTree() {
        return decisionTree;
    }
}
