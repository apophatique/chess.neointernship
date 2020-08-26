package neointernship.web.client.player.bot.ai.extended.mediator;

import com.fasterxml.jackson.annotation.JsonValue;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.IFactory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.player.bot.ai.extended.figure.factory.FactoryExtended;
import neointernship.web.client.player.bot.ai.extended.figure.figures.KingExtended;

import java.util.*;
import java.util.stream.Collectors;

public class MediatorExtended implements IMediator {
    private final HashMap<IField, Figure> mediator;
    private final IFactory factory;

    public MediatorExtended() {
        mediator = new HashMap<>();
        factory = new FactoryExtended();
    }

    public MediatorExtended(final IMediator mediator) {
        this();

        if (mediator.getClass() == MediatorExtended.class) {
            for (final Figure figure : mediator.getFigures()) {
                final IField field = mediator.getField(figure);
                addNewConnection(field, figure);
            }
        } else {
            for (final Figure figure : mediator.getFigures()) {
                final IField field = mediator.getField(figure);
                addNewConnection(
                        field,
                        factory.createFigure(
                                figure.getGameSymbol(),
                                figure.getColor()
                        )
                );
            }
        }
    }

    /**
     * Добавление новой связи
     *
     * @param field  поле
     * @param figure фигура
     */
    @Override
    public void addNewConnection(final IField field, final Figure figure) {
        mediator.put(field, figure);
    }

    @Override
    public void deleteConnection(final IField field) {
        mediator.remove(field);
    }

    @Override
    public void clear() {
        mediator.clear();
    }

    @Override
    public Figure getKing(final Color color) {
        for (final Figure figure : mediator.values()) {
            if (figure.getClass().equals(KingExtended.class) && figure.getColor() == color) {
                return figure;
            }
        }
        return null;
    }


    /**
     * Получение фигуры, стоящей на данном поле.
     *
     * @param field - входящее поле.
     * @return фигура или null.
     */
    public Figure getFigure(final IField field) {
        return mediator.get(field);
    }

    @Override
    public Collection<Figure> getFigures(final Color color) {
        return getFigures()
                .stream()
                .filter(f -> f.getColor() == color)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает все фигуры.
     *
     * @return колекция фигур или null
     */
    public Collection<Figure> getFigures() {
        return mediator.values();
    }

    /**
     * Поиск поля, на котором стоит данная фигура.
     *
     * @param figure - входимая фигура.
     * @return поле.
     */
    public IField getField(final Figure figure) {
        final Set<Map.Entry<IField, Figure>> entrySet = mediator.entrySet();

        for (final Map.Entry<IField, Figure> pair : entrySet) {
            if (Objects.equals(figure, pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediator);
    }

    @Override
    @JsonValue
    public String toString() {
        String string = "";
        for (final IField field : mediator.keySet()) {
            string += "<" + field.toString() + ";" + mediator.get(field).toString() + ">";
        }
        return string;
    }
}
