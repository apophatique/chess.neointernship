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
        if (figure.getGameSymbol() == 'B') {
            basicAttackPatterns.getDiagonalFields(figure);

        } else if (figure.getGameSymbol() == 'Q') {
            basicAttackPatterns.getDiagonalFields(figure);
            basicAttackPatterns.getHorizonVerticalFields(figure);

        } else if (figure.getGameSymbol() == 'P') {
            basicAttackPatterns.getPawnFields(figure);

        } else if (figure.getGameSymbol() == 'R') {
            basicAttackPatterns.getHorizonVerticalFields(figure);

        } else if (figure.getGameSymbol() == 'K') {
            basicAttackPatterns.getKingFields(figure);

        } else if (figure.getGameSymbol() == 'N') {
            basicAttackPatterns.getKnightFields(figure);
        }
    }
}
