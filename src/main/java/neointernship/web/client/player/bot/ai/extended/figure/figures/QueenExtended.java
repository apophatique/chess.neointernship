package neointernship.web.client.player.bot.ai.extended.figure.figures;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;

public class QueenExtended extends Figure {
    public QueenExtended(Color color) {
        super(
                "Queen",
                'Q',
                color,
                (short) 900
        );
    }
}
