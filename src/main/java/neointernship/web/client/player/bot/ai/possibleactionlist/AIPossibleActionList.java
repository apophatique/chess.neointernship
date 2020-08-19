package neointernship.web.client.player.bot.ai.possibleactionlist;

import neointernship.chess.game.gameplay.figureactions.patterns.real.IRealBasicPatterns;
import neointernship.chess.game.gameplay.figureactions.patterns.real.RealBasicPatterns;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.player.bot.ai.possibleactionlist.barier.FigureMoveBarrier;
import neointernship.web.client.player.bot.ai.possibleactionlist.patterns.AIIntermediary;
import neointernship.web.client.player.bot.ai.possibleactionlist.patterns.potential.AIPotentialBasicPatterns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AIPossibleActionList implements IAIPossibleActionList {
    private IMediator mediator;
    private IStoryGame storyGame;
    private final IBoard board;
    private  AIPotentialBasicPatterns potentialPatterns;
    private  IRealBasicPatterns realPatterns;
    private final AIIntermediary intermediary;
    //private final FigureMoveBarrier figureMoveBarrier;

    private final Map<Figure, Collection<IField>> realFigureActions;
    private final Map<Figure, Collection<IField>> potentialFigureAction;

    public AIPossibleActionList(final IBoard board,
                                final IMediator mediator,
                                final IStoryGame storyGame) {
        this.storyGame = storyGame;
        this.mediator = new Mediator(mediator);
        this.board = board;

        //this.figureMoveBarrier = new FigureMoveBarrier();
        this.potentialPatterns = new AIPotentialBasicPatterns(
                null,
                this.mediator,
                board,
                storyGame
        );
        this.realPatterns = new RealBasicPatterns(
                this.mediator,
                board,
                storyGame);
        this.intermediary = new AIIntermediary(potentialPatterns);


        this.realFigureActions = new HashMap<>();
        this.potentialFigureAction = new HashMap<>();
    }

    public AIPossibleActionList(final IBoard board,
                                final IMediator mediator,
                                final IStoryGame storyGame,
                                final Map<Figure, Collection<IField>> realFigureActions,
                                final Map<Figure, Collection<IField>> potentialFigureAction,
                                final Map<Figure, Collection<IField>> barrierMap) {
        this(board, mediator, storyGame);

        for (Map.Entry<Figure, Collection<IField>> entry : realFigureActions.entrySet()) {
            this.realFigureActions.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Figure, Collection<IField>> entry : potentialFigureAction.entrySet()) {
            this.potentialFigureAction.put(entry.getKey(), entry.getValue());
        }
        /*
        for (Map.Entry<Figure, Collection<IField>> entry : barrierMap.entrySet()) {
            this.figureMoveBarrier.clear(entry.getKey());
            for (IField field : entry.getValue()) {
                this.figureMoveBarrier.put(entry.getKey(), field);
            }
        }

         */
    }

    @Override
    public void updateAll() {
        potentialFigureAction.clear();
        realFigureActions.clear();

        for (final Figure figure : mediator.getFigures()) {
            updatePotentialList(figure);

            realFigureActions.put(figure, new ArrayList<>());

            realFigureActions.get(figure).addAll(realPatterns.getRealMoveList(
                    figure,
                    potentialFigureAction.get(figure))
            );
        }
    }

    @Override
    public void update(final IMediator newMediator) {
        System.out.println("AIPossibleActionList.update");
       // ArrayList<IField> changedFieldsList = new ArrayList<>();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                final IField field = board.getField(i, j);

                if (mediator.getFigure(field) != newMediator.getFigure(field)) {
                  //  changedFieldsList.add(field);

                    if (newMediator.getFigure(field) == null) {
                        mediator.deleteConnection(field);
                    } else {
                        mediator.addNewConnection(field, newMediator.getFigure(field));
                    }
                }
            }
        }
        System.out.println("Updated");

        for (final Figure figure : mediator.getFigures()) {
            //System.out.println("Updated on " + figure.getColor() + " " + figure.getName());

            //  final ArrayList<IField> barrierFields = new ArrayList<>(figureMoveBarrier.get(figure));

            //for (final IField field : changedFieldsList) {
              //  if (barrierFields.contains(field)) {
                    updatePotentialList(figure);

                    realFigureActions.put(figure, new ArrayList<>());
                    realFigureActions
                            .get(figure)
                            .addAll(realPatterns.getRealMoveList(figure, potentialFigureAction.get(figure)));
              //  }
            //}
        }
    }

    public void updatePotentialList(final Figure figure) {
       // figureMoveBarrier.clear(figure);
        potentialFigureAction.put(figure, new ArrayList<>());

        potentialFigureAction.get(figure).addAll(intermediary.getList(figure));
    }

    @Override
    public Collection<IField> getList(final Figure figure) {
        return realFigureActions.get(figure);
    }

    @Override
    public void show() {
        System.out.println("\n\n\nHallu!^^");
        for (final Figure barrier : mediator.getFigures()) {
            System.out.format("Figure: %s %s\n", barrier.getColor(), barrier.getName());
            System.out.print("Potential: ");
            for (final IField field : potentialFigureAction.get(barrier)) {
                System.out.format("(%d;%d)", field.getXCoord(), field.getYCoord());
            }
            System.out.print("\n");
            System.out.print("Real: ");
            for (final IField field : realFigureActions.get(barrier)) {
                System.out.format("(%d;%d)", field.getXCoord(), field.getYCoord());
            }
            System.out.print("\n");
            System.out.print("Barrier: ");
           /* for (final IField field : figureMoveBarrier.get(barrier)) {
                System.out.format("(%d;%d)", field.getXCoord(), field.getYCoord());
            }

            */
            System.out.println("\n");
        }
    }

    @Override
    public Map<Figure, Collection<IField>> getRealList() {
        return realFigureActions;
    }

    @Override
    public Map<Figure, Collection<IField>> getPotentialList() {
        return potentialFigureAction;
    }

    @Override
    public Map<Figure, Collection<IField>> getBarrierList() {
        return null;
    }
}
