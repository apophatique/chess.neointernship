package neointernship.chess.game.model.figure.factory;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;

public interface IFactory {
    /**
     * @param pieceName
     * @return
     */
    Figure createFigure(final Character pieceName, final Color color);
}
