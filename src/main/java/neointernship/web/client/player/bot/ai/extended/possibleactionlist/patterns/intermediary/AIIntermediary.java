package neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.intermediary;

import neointernship.chess.game.model.figure.piece.*;
import neointernship.web.client.player.bot.ai.extended.figure.figures.*;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.AIPotentialBasicPatterns;

public class AIIntermediary {
    private final AIPotentialBasicPatterns basicAttackPatterns;

    public AIIntermediary(final AIPotentialBasicPatterns basicAttackPatterns) {
        this.basicAttackPatterns = basicAttackPatterns;
    }

    public void updateActionLists(final Figure figure) {
        if (BishopExtended.class.equals(figure.getClass())) {
            basicAttackPatterns.getDiagonalFields(figure);

        } else if (QueenExtended.class.equals(figure.getClass())) {
            basicAttackPatterns.getDiagonalFields(figure);
            basicAttackPatterns.getHorizonVerticalFields(figure);

        } else if (PawnExtended.class.equals(figure.getClass())) {
            basicAttackPatterns.getPawnFields(figure);

        } else if (RookExtended.class.equals(figure.getClass())) {
            basicAttackPatterns.getHorizonVerticalFields(figure);

        } else if (KingExtended.class.equals(figure.getClass())) {
            basicAttackPatterns.getKingFields(figure);

        } else if (KnightExtended.class.equals(figure.getClass())) {
            basicAttackPatterns.getKnightFields(figure);
        }
    }
}
