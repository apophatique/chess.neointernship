package neointernship.web.client.player.bot.ai.extended.figure.figures;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;


public class RookExtended extends Figure {
    public RookExtended(Color color) {
        super(
                "Rook",
                'R',
                color,
                (short) 500
        );
    }
}
