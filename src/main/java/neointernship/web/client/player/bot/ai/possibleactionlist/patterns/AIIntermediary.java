package neointernship.web.client.player.bot.ai.possibleactionlist.patterns;

import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.player.bot.ai.possibleactionlist.patterns.potential.AIPotentialBasicPatterns;

import java.util.ArrayList;

public class AIIntermediary {
    private final AIPotentialBasicPatterns basicAttackPatterns;

    public AIIntermediary(final AIPotentialBasicPatterns basicAttackPatterns) {
        this.basicAttackPatterns = basicAttackPatterns;
    }

    public ArrayList<IField> getList(final Figure figure) {
        final ArrayList<IField> list = new ArrayList<>();

        if (Bishop.class.equals(figure.getClass())) {
            list.addAll(basicAttackPatterns.getDiagonalFields(figure));

        } else if (Queen.class.equals(figure.getClass())) {
            list.addAll(basicAttackPatterns.getDiagonalFields(figure));
            list.addAll(basicAttackPatterns.getHorizonVerticalFields(figure));

        } else if (Pawn.class.equals(figure.getClass())) {
            list.addAll(basicAttackPatterns.getPawnFields(figure));

        } else if (Rook.class.equals(figure.getClass())) {
            list.addAll(basicAttackPatterns.getHorizonVerticalFields(figure));

        } else if (King.class.equals(figure.getClass())) {
            list.addAll(basicAttackPatterns.getKingFields(figure));

        } else if (Knight.class.equals(figure.getClass())) {
            list.addAll(basicAttackPatterns.getKnightFields(figure));
        }

        return list;
    }
}
