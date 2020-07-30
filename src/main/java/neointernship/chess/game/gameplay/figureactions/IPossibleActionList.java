package neointernship.chess.game.gameplay.figureactions;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public interface IPossibleActionList {
    Collection<IField> getPotentialList(final Figure figure);

    void updatePotentialLists();

    /**
     * обновляет список потенциальных ходов для фигур цвета color
     * @param color
     */
    void updatePotentialLists(final Color color);

    Collection<IField> getRealList(final Figure figure);

    void updateRealLists();
}

