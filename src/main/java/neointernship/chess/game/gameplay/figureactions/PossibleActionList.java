package neointernship.chess.game.gameplay.figureactions;

import neointernship.chess.game.gameplay.figureactions.patterns.potential.IPotentialBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.potential.PotentialBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.real.IRealBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.real.RealBasicPatterns;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;

import java.util.*;

public class PossibleActionList implements IPossibleActionList {
    private final IMediator mediator;

    private final IPotentialBasicPatterns potentialPatterns;
    private final IRealBasicPatterns realPatterns;

    private final Map<Figure, Collection<IField>> realFigureActions;
    private final Map<Figure, Collection<IField>> potentialFigureAction;

    public PossibleActionList(final IBoard board,
                              final IMediator mediator,
                              final IStoryGame storyGame) {
        this.mediator = mediator;

        this.potentialPatterns = new PotentialBasicPatterns(mediator, board, storyGame);
        this.realPatterns = new RealBasicPatterns(mediator, board, storyGame);

        this.realFigureActions = new HashMap<>();
        this.potentialFigureAction = new HashMap<>();
    }

    @Override
    public void updatePotentialLists() {
        potentialFigureAction.clear();

        for (Figure figure : mediator.getFigures()) {
            potentialFigureAction.put(figure, new ArrayList<>());

            potentialFigureAction.get(figure).addAll(Intermediary.getList(figure, potentialPatterns));
        }
    }

    @Override
    public void updatePotentialLists(Color color) {

        for (Figure figure : mediator.getFigures(color)) {
            potentialFigureAction.put(figure, new ArrayList<>());

            potentialFigureAction.get(figure).addAll(Intermediary.getList(figure, potentialPatterns));
        }
    }

    @Override
    public Collection<IField> getRealList(Figure figure) {
        return realFigureActions.get(figure);
    }

    @Override
    public void updateRealLists() {
        updatePotentialLists();
        realFigureActions.clear();

        for (Figure figure : mediator.getFigures()) {
            realFigureActions.put(figure, new ArrayList<>());

            realFigureActions.get(figure).addAll(realPatterns.getRealMoveList(
                    figure,
                    potentialFigureAction.get(figure)));
        }
    }

    @Override
    public Collection<IField> getPotentialList(Figure figure) {
        return potentialFigureAction.get(figure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PossibleActionList that = (PossibleActionList) o;
        return Objects.equals(realFigureActions, that.realFigureActions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediator, realFigureActions);
    }
}
