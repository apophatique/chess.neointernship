package neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.real;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move.Move;

import java.util.Collection;

public interface IRealBasicPatternsAI {
    Collection<Move> getRealMoveList(final Figure figure, final Collection<Move> potentialMoveList);
}
