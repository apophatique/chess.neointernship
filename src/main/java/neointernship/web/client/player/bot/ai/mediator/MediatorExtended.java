package neointernship.web.client.player.bot.ai.mediator;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.*;
import java.util.stream.Collectors;

public class MediatorExtended implements IMediator {
    //private final Set<Figure> sortedMediatorSet;
    private final IMediator mediator;
    private final ArrayList<Figure> sortedFiguresList;

    public MediatorExtended(final IMediator mediator) {
        this.mediator = mediator;
        this.sortedFiguresList = new ArrayList<Figure>() {
            {
                addAll(mediator.getFigures()
                        .stream()
                        .sorted(Comparator.comparingInt(Figure::getPrice))
                        .collect(Collectors.toList()));
            }
        };
/*
        sortedMediatorSet = new TreeSet<>(
                Comparator.comparingInt(Figure::getPrice));

        sortedMediatorSet.addAll(mediator.getFigures());

 */
    }

    @Override
    public IField getField(final Figure figure) {
        return mediator.getField(figure);
    }

    @Override
    public Figure getFigure(final IField field) {
        return mediator.getFigure(field);
    }

    @Override
    public Collection<Figure> getFigures(final Color color) {
        return mediator.getFigures()
                .stream()
                .filter(f -> f.getColor() == color)
                .sorted(Comparator.comparingInt(Figure::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Figure> getFigures() {
        return new ArrayList<>(sortedFiguresList);
    }

    @Override
    public Figure getKing(final Color color) {
        return mediator.getKing(color);
    }

    @Override
    public void addNewConnection(final IField field, final Figure figure) {
        mediator.addNewConnection(field, figure);
    }

    @Override
    public void deleteConnection(final IField field) {
        mediator.deleteConnection(field);
    }

    @Override
    public void clear() {
        mediator.clear();
    }
}
