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
import neointernship.web.client.player.bot.ai.positionvalue.PositionValueEstimator;
import neointernship.web.client.player.bot.ai.possibleactionlist.AIPossibleActionList;
import neointernship.web.client.player.bot.ai.possibleactionlist.IAIPossibleActionList;


public class DecisionTreeCreator {
    private final Color rootColor;

    public DecisionTreeCreator(final Color color) {
        this.rootColor = color;
    }

    public IAnswer getDecision(final IMediator mediator,
                               final IBoard board,
                               final IStoryGame storyGame,
                               final int recursionDepth) {
       /* final IPossibleActionList possibleActionList = new PossibleActionList(
                board,
                mediator,
                storyGame
        );
        possibleActionList.updateRealLists();


        */
        final Node rootNode = new Node(
                null,
                mediator,
                board,
                storyGame,
                rootColor
        );
        nextLayer(
                rootNode,
                recursionDepth,
                Integer.MIN_VALUE + 1,
                Integer.MAX_VALUE - 1
        );

        return rootNode
                .getChild()
                .getAnswerToGet();
    }

    public int nextLayer(final Node currentNode, final int recursionDepth, int alpha, int beta) {
        System.out.format("Depth: %d\n", recursionDepth);
        final Color activeColor = currentNode.getActiveColor();
        final IMediator mediator = currentNode.getMediator();
        final IBoard board = currentNode.getBoard();
        final IStoryGame storyGame = currentNode.getStoryGame();

        if (recursionDepth == 0) {
            return PositionValueEstimator.calculate(
                    board,
                    mediator,
                    storyGame,
                    activeColor
            );
        }

        int currentScore = Integer.MIN_VALUE;
        final IPossibleActionList possibleActionList = new PossibleActionList(board, mediator, storyGame);
        possibleActionList.updateRealLists();

        for (final Figure figure : mediator.getFigures(activeColor)) {
            possibleActionList.getRealList(figure).forEach(System.out::println);
            for (final IField field : possibleActionList.getRealList(figure)) {
                final IMediator currentMediator = new Mediator(mediator);
                final IMoveCommand command = new AllowMoveCommand(
                        currentMediator,
                        null, // todo: удалить
                        board,
                        storyGame
                );
                final IAnswer answer = new Answer(
                        currentMediator.getField(figure).getXCoord(),
                        currentMediator.getField(figure).getYCoord(),
                        field.getXCoord(),
                        field.getYCoord(),
                        ' '
                );
                command.execute(answer);

            /*
                final IPossibleActionList moveList = new PossibleActionList(
                        board,
                        currentMediator,
                        storyGame,
                        actionList.getRealList(),
                        actionList.getPotentialList(),
                        actionList.getBarrierList()
                );

                moveList.updateRealLists();
                                */

                final Node childNode = new Node(
                        answer,
                        currentMediator,
                        board,
                        storyGame,
                        Color.swapColor(activeColor)
                );

                final int nextDepthScore = - nextLayer(
                        childNode,
                        recursionDepth - 1,
                        - beta,
                        - alpha
                );

                if (nextDepthScore > currentScore) {
                    currentScore = nextDepthScore;
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
}
