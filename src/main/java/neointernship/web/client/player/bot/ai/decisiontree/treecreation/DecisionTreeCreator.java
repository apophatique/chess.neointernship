package neointernship.web.client.player.bot.ai.decisiontree.treecreation;

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
import neointernship.web.client.player.bot.ai.decisiontree.node.Node;
import neointernship.web.client.player.bot.ai.extended.mediator.MediatorExtended;
import neointernship.web.client.player.bot.ai.positionanalyze.analyzer.PositionAnalyzer;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.AIPossibleActionList;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.IAIPossibleActionList;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move.Move;

/**
 *
 */
public class DecisionTreeCreator {
    private final Color rootColor;

    /**
     * The class constructor with root color setting.
     *
     * @param color {@link Color} bot side color.
     */
    public DecisionTreeCreator(final Color color) {
        this.rootColor = color;
    }

    /**
     * Main method for getting decision from algorithm.
     *
     *
     *
     * @param mediator {@link IMediator} implementation - current figures position on the board.
     * @param board {@link IBoard} implementation - game board.
     * @param storyGame {@link IStoryGame} implementation - info about figures moves.
     * @param recursionDepth current depth of the recursion.
     * @return {@link IAnswer} - new answer to send to client.
     */
    public IAnswer getDecision(final IMediator mediator,
                               final IBoard board,
                               final IStoryGame storyGame,
                               final int recursionDepth) {
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
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
        );
        return rootNode
                .getChild()
                .getAnswerToCome();
    }

    public int nextLayer(final Node currentNode, final int recursionDepth, int alpha, int beta) {
        final Color activeColor = currentNode.getActiveColor();
        final IMediator mediator = currentNode.getMediator();
        final IBoard board = currentNode.getBoard();
        final IStoryGame storyGame = currentNode.getStoryGame();
        final IAIPossibleActionList possibleActionList = new AIPossibleActionList(
                board,
                mediator,
                storyGame
        );
        final PositionAnalyzer positionAnalyzer = new PositionAnalyzer(
                mediator,
                possibleActionList,
                activeColor
        );
        possibleActionList.update(
                activeColor,
                positionAnalyzer.getGamePhase(),
                recursionDepth
        );
        if (activeColor != rootColor && possibleActionList.getList().isEmpty()) {
            return Integer.MIN_VALUE;
        }
        if (recursionDepth == 0) {
            return positionAnalyzer.getEstimation();
        }

        for (final Move move : possibleActionList.getList()) {
            final Figure figure = move.getMovingFigure();
            final IField field = move.getFieldToMove();
            final IMediator currentMediator = new MediatorExtended(mediator);

            final IMoveCommand command = new AllowMoveCommand(
                    currentMediator,
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

            final Node childNode = new Node(
                    answer,
                    currentMediator,
                    board,
                    storyGame,
                    Color.swapColor(activeColor)
            );
            int /*nextDepthScore = - nextLayer(
                    childNode,
                    recursionDepth - 1,
                    - (alpha + 1),
                    - alpha
            );
            if (nextDepthScore > alpha && nextDepthScore < beta) {
                */nextDepthScore = - nextLayer(
                        childNode,
                        recursionDepth - 1,
                        - beta,
                        - alpha
                );
            //}

            if (nextDepthScore > alpha) {
                alpha = nextDepthScore;
                currentNode.setChild(childNode);
            }
            if (alpha >= beta) {
                currentNode.setChild(childNode);
                return alpha;
            }
        }

        return alpha;
    }
}
