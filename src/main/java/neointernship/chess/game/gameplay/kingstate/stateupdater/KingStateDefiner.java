package neointernship.chess.game.gameplay.kingstate.stateupdater;

import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.figure.actions.IPossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class KingStateDefiner {
    public static KingState define(final IPossibleActionList possibleActionList,
                                   final IMediator mediator,
                                   final Figure king) {
        boolean isKingAttacked;
        boolean isKingHasMoves;

        Map<Figure, Collection<IField>> figuresMovesMap = possibleActionList.getMap();
        Set<Map.Entry<Figure, Collection<IField>>> entrySet = figuresMovesMap.entrySet();
        for (Map.Entry<Figure, Collection<IField>> pair : entrySet) {
            for (IField field : pair.getValue()) {
                if (Objects.equals(king, mediator.getFigure(field))) {
                    isKingAttacked = true;
                }
                if (Objects.equals(pair.getKey(), king)) {
                    isKingHasMoves = true;
                }
            }
        }


        return null;
    }
}
