package neointernship.web.client.player.bot.ai.extended.figure.figures;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;

public class KnightExtended extends Figure {
    public KnightExtended(Color color) {
        super(
                "Knight",
                'N',
                color,
                (short) 320
        );
    }
}
