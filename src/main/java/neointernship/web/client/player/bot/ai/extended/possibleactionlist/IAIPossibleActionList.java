package neointernship.web.client.player.bot.ai.extended.possibleactionlist;

import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move.Move;

import java.util.Collection;

public interface IAIPossibleActionList {
    void update(final Color color, final double phase, final int recursionDepth);
    Collection<Move> getList();
}
