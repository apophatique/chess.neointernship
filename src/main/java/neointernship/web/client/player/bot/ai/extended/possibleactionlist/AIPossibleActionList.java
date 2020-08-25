package neointernship.web.client.player.bot.ai.extended.possibleactionlist;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.player.bot.ai.extended.mediator.MediatorExtended;
import neointernship.web.client.player.bot.ai.positionanalyze.PST.PSTable;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.intermediary.AIIntermediary;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.AIPotentialBasicPatterns;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move.Move;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.real.AIRealBasicPatterns;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.real.IRealBasicPatternsAI;

import java.util.*;
import java.util.stream.Collectors;

public class AIPossibleActionList implements IAIPossibleActionList {
    private final IMediator mediator;
    private final AIPotentialBasicPatterns potentialPatterns;
    private final IRealBasicPatternsAI realPatterns;

    private final ArrayList<Move> potentialAttackFields;
    private final ArrayList<Move> realAttackFields;
    private final ArrayList<Move> potentialMoveFields;
    private ArrayList<Move> realMoveFields;

    private final ArrayList<Move> unitedActionList;

    private final AIIntermediary intermediary;

    public AIPossibleActionList(final IBoard board,
                                final IMediator mediator,
                                final IStoryGame storyGame) {
        this.mediator = new MediatorExtended(mediator);

        this.potentialAttackFields = new ArrayList<>();
        this.realAttackFields = new ArrayList<>();
        this.potentialMoveFields = new ArrayList<>();
        this.realMoveFields = new ArrayList<>();
        this.unitedActionList = new ArrayList<>();

        this.potentialPatterns = new AIPotentialBasicPatterns(
                this.mediator,
                board,
                storyGame,
                potentialAttackFields,
                potentialMoveFields
        );
        this.realPatterns = new AIRealBasicPatterns(
                this.mediator,
                board,
                storyGame
        );
        this.intermediary = new AIIntermediary(potentialPatterns);
    }

    @Override
    public void update(final Color color, final double gamePhase, final int recursionDepth) {
        for (final Figure figure : mediator.getFigures(color)) {
            potentialAttackFields.clear();
            potentialMoveFields.clear();
            intermediary.updateActionLists(figure);

            realAttackFields.addAll(
                    realPatterns.getRealMoveList(
                            figure,
                            potentialAttackFields
                    )
            );
            realMoveFields.addAll(
                    realPatterns.getRealMoveList(
                            figure,
                            potentialMoveFields
                    )
            );
        }

        unitedActionList.addAll(
                realAttackFields.stream().sorted((o1, o2) -> {
                    final Figure firstFinalFigure = mediator.getFigure(o1.getFieldToMove());
                    final Figure secondFinalFigure = mediator.getFigure(o2.getFieldToMove());

                    if (secondFinalFigure.getPrice() > firstFinalFigure.getPrice()) {
                        return 1;
                    } else if (secondFinalFigure.getPrice() == firstFinalFigure.getPrice()) {
                        return Short.compare(o1.getMovingFigure().getPrice(), o2.getMovingFigure().getPrice());
                    }

                    return -1;
                }).collect(Collectors.toCollection(ArrayList::new))
        );
            unitedActionList.addAll(realMoveFields
                    .stream()
                    .sorted((o1, o2) -> Double.compare(
                            PSTable.getMoveCost(
                                    o2.getMovingFigure(),
                                    o2.getFieldToMove(),
                                    gamePhase
                            ),
                            PSTable.getMoveCost(
                                    o1.getMovingFigure(),
                                    o1.getFieldToMove(),
                                    gamePhase
                            )))
                    .collect(Collectors.toCollection(ArrayList::new))
            );

    }

    @Override
    public Collection<Move> getList() {
        return unitedActionList;
    }
}
