package neointernship.web.client.player.bot.ai.decisiontree.treecreation;

import neointernship.chess.game.gameplay.moveaction.commands.IMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.player.bot.ai.decisiontree.node.Node;
import neointernship.web.client.player.bot.ai.extended.mediator.MediatorExtended;
import neointernship.web.client.player.bot.ai.positionanalyze.analyzer.PositionAnalyzer;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.AIPossibleActionList;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.IAIPossibleActionList;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move.Move;
import neointernship.web.client.player.bot.ai.positionanalyze.kingstate.AIKingIsAttackedViewer;

/**
 *
 */
public class DecisionTreeCreator {
    private final Color rootColor;
    private final PositionAnalyzer positionAnalyzer;
    /**
     * The class constructor with root color setting.
     *
     * @param color {@link Color} bot side color.
     */
    public DecisionTreeCreator(final Color color) {
        this.rootColor = color;
        this.positionAnalyzer = new PositionAnalyzer(rootColor);
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

    public double nextLayer(final Node currentNode, final int recursionDepth, double alpha, double beta) {
        final Color activeColor = currentNode.getActiveColor();
        final IMediator mediator = currentNode.getMediator();
        final IBoard board = currentNode.getBoard();
        final IStoryGame storyGame = currentNode.getStoryGame();

        if (recursionDepth == 0) {
            return positionAnalyzer.estimateBoard(
                    mediator,
                    activeColor
            );
        }
        final IAIPossibleActionList possibleActionList = new AIPossibleActionList(
                board,
                mediator,
                storyGame
        );
        possibleActionList.update(
                activeColor,
                positionAnalyzer.getMatchPhase(mediator, activeColor),
                recursionDepth
        );

        if (possibleActionList
                .getList()
                .isEmpty()) {
            return Integer.MIN_VALUE;
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
            final Node childNode = new Node(
                    answer,
                    currentMediator,
                    board,
                    storyGame,
                    Color.swapColor(activeColor)
            );
            command.execute(answer);


            double nextDepthScore = - nextLayer(
                    childNode,
                    recursionDepth - 1,
                    - beta,
                    - alpha
            );

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
