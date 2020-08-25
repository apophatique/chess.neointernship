package neointernship.web.client.player.bot.ai.extended.possibleactionlist;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move.Move;

import java.util.Collection;
import java.util.Map;

public interface IAIPossibleActionList {
    void update(final Color color, final double phase);
    Collection<Move> getList();
}
