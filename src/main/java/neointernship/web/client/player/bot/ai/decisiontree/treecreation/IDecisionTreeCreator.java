package neointernship.web.client.player.bot.ai.decisiontree.treecreation;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.story.IStoryGame;

/**
 * An interface for
 */
public interface IDecisionTreeCreator {
    /**
     * Main method for getting decision from algorithm.
     *
     * @param mediator {@link IMediator} implementation - current figures position on the board.
     * @param board {@link IBoard} implementation - game board.
     * @param storyGame {@link IStoryGame} implementation - info about figures moves.
     * @param recursionDepth current depth of the recursion.
     * @return {@link IAnswer} - new answer to send to client.
     */
    IAnswer getDecision(
            final IMediator mediator,
            final IBoard board,
            final IStoryGame storyGame,
            final int recursionDepth
    );
}
