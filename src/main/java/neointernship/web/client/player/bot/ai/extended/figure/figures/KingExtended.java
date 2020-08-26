package neointernship.web.client.player.bot.ai.extended.figure.figures;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;

public class KingExtended extends Figure {
    public KingExtended(Color color) {
        super("King", 'K', color, (short) 1000);
    }
}
