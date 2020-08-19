package neointernship.web.client.player.bot.ai.possibleactionlist.barier;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FigureMoveBarrier {
    private final Map<Figure, Collection<IField>> figureMoveBarrier;

    public FigureMoveBarrier() {
        this.figureMoveBarrier = new HashMap<>();
    }

    public void put(final Figure barrier, final IField field) {
        figureMoveBarrier.get(barrier).add(field);
    }

    public void clear(final Figure figure) {
        figureMoveBarrier.put(figure, new ArrayList<>());
    }

    public void remove(final Figure figure) {
        figureMoveBarrier.remove(figure);
    }

    public Collection<IField> get(final Figure figure) {
        return figureMoveBarrier.get(figure);
    }

    public Map<Figure, Collection<IField>> getSet() {
        return figureMoveBarrier;
    }
}
