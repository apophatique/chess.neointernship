package neointernship.web.client.player.bot.ai.extended.figure.figures;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;

public class PawnExtended extends Figure {
    public PawnExtended(Color color) {
        super(
                "Pawn",
                'P',
                color,
                (short) 100
        );
    }
}
