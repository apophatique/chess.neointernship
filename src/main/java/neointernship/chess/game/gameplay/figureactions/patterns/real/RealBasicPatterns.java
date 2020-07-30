package neointernship.chess.game.gameplay.figureactions.patterns.real;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AttackCommand;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;

import java.util.ArrayList;
import java.util.Collection;

public class RealBasicPatterns implements IRealBasicPatterns {
    private final IMediator mediator;
    private final IBoard board;
    private KingIsAttackedComputation kingIsAttackedComputation;
    private final IStoryGame storyGame;

    public RealBasicPatterns(final IMediator mediator,
                             final IBoard board,
                             final IStoryGame storyGame) {
        this.mediator = mediator;
        this.board = board;
        this.storyGame = storyGame;
    }

    // todo избавится от всех этих костылей
    @Override
    public Collection<IField> getRealMoveList(Figure figure, Collection<IField> potentialMoveList) {

        final ArrayList<IField> realList = new ArrayList<>();
        final IField startField = mediator.getField(figure);

        for (IField finalField : potentialMoveList) {

            IMediator newMediator = new Mediator(mediator);
            IStoryGame newStoryGame = new StoryGame((StoryGame) storyGame);
            IPossibleActionList newPossibleActionList = new PossibleActionList(board, newMediator, newStoryGame);

            // если это рокировка
            if (isCastling(figure, startField, finalField)) {
                if (isCorrectCastling(figure, startField, finalField, newMediator, newPossibleActionList)) {
                    realList.add(finalField);
                }
            } else {
                // взятие на проходе
                if(isAisleTake(figure,startField,finalField)) {

                    final IField fieldAttackPawn = board.getField(startField.getXCoord(), finalField.getYCoord());

                    newMediator.deleteConnection(startField);
                    newMediator.addNewConnection(finalField, figure);
                    newMediator.deleteConnection(fieldAttackPawn);

                    newStoryGame.update(mediator.getFigure(startField));
                    newPossibleActionList.updatePotentialLists();

                    kingIsAttackedComputation = new KingIsAttackedComputation(newPossibleActionList, newMediator);

                    if (!kingIsAttackedComputation.kingIsAttacked(figure.getColor())) {
                        realList.add(finalField);
                    }

                }else {

                    Figure finalFigure = newMediator.getFigure(finalField);

                    newMediator.deleteConnection(startField);
                    if (finalFigure != null) {
                        newMediator.deleteConnection(finalField);
                    }
                    newMediator.addNewConnection(finalField, figure);
                    newStoryGame.update(mediator.getFigure(startField));
                    newPossibleActionList.updatePotentialLists();

                    kingIsAttackedComputation = new KingIsAttackedComputation(newPossibleActionList, newMediator);

                    if (!kingIsAttackedComputation.kingIsAttacked(figure.getColor())) {
                        realList.add(finalField);
                    }
                }
            }
        }
        return realList;
    }

    private boolean isCastling(final Figure figure, IField startField, IField finishField) {
        return figure.getClass() == King.class &&
                Math.abs(startField.getYCoord() - finishField.getYCoord()) == 2;
    }

    private boolean isCorrectCastling(final Figure figure,
                                      final IField startField,
                                      final IField finishField,
                                      final IMediator mediator,
                                      final IPossibleActionList possibleActionList) {
        possibleActionList.updatePotentialLists();
        Collection<IField> forCastling = new ArrayList<>();

        int dif = startField.getYCoord() < finishField.getYCoord() ? 1 : -1;

        forCastling.add(startField);
        forCastling.add(board.getField(startField.getXCoord(), startField.getYCoord() + dif));
        forCastling.add(finishField);

        kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
        for (IField tempField : forCastling) {
            if (kingIsAttackedComputation.fieldIsAttacked(tempField, figure.getColor())) return false;
        }
        return true;
    }

    private boolean isAisleTake(final Figure figure,IField startField,IField finishField){

        return figure.getClass() == Pawn.class &&
                Math.abs(startField.getYCoord() - finishField.getYCoord()) == 1 &&
                mediator.getFigure(finishField) == null;

    }
}
