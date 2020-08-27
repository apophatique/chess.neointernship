package neointernship.web.client.player.bot.ai.positionanalyze.kingstate;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.AIPossibleActionList;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.IAIPossibleActionList;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public final class AIKingIsAttackedViewer {
    public AIKingIsAttackedViewer() {}

    public boolean kingIsAttacked(final IMediator mediator,
                                  final Collection<Move> opponentMoveList,
                                  final Color color) {
        final Figure king = mediator.getKing(color);
        final IField kingField = mediator.getField(king);

        for (final Move move : opponentMoveList) {
            if (move.getFieldToMove() == kingField) {
                return true;
            }
        }
        return false;
    }
}
