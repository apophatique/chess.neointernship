package neointernship.web.client.player.bot.ai.extended.figure.figures;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;

public class BishopExtended extends Figure {
    public BishopExtended(Color color) {
        super(
                "Bishop",
                'B',
                color,
                (short) 330
        );
    }
}
