package neointernship.web.client.player.bot.ai.possibleactionlist.patterns.potential;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.player.bot.ai.possibleactionlist.barier.FigureMoveBarrier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AIPotentialBasicPatterns implements IPotentialBasicPatternsAI {
    private final int boardSize;
    private final IMediator mediator;
    private final IBoard board;
    private final IStoryGame storyGame;
    private final FigureMoveBarrier figureMoveBarrier;

    public AIPotentialBasicPatterns(final FigureMoveBarrier figureMoveBarrier,
                                    final IMediator mediator,
                                    final IBoard board,
                                    final IStoryGame storyGame) {
        this.mediator = mediator;
        this.board = board;
        boardSize = board.getSize();
        this.storyGame = storyGame;
        this.figureMoveBarrier = figureMoveBarrier;
    }

    public ArrayList<IField> getDiagonalFields(final Figure figure) {
        //System.out.println("AIPotentialBasicPatterns.getDiagonalFields");
        final ArrayList<IField> attackList = new ArrayList<>();
        final ArrayList<IField> moveList = new ArrayList<>();
        final IField currentField = mediator.getField(figure);

        boolean keepRightDownMove = true;
        boolean keepLeftUpMove = true;
        for (int i = 1; i < boardSize; i++) {
            if (keepRightDownMove && currentField.getXCoord() < boardSize && currentField.getYCoord() < boardSize) {
                keepRightDownMove = actionToAdd(
                        figure,
                        currentField.getXCoord() + i,
                        currentField.getYCoord() + i,
                        attackList,
                        moveList);
            }

            if (keepLeftUpMove && currentField.getXCoord() > 0 && currentField.getYCoord() > 0) {
                keepLeftUpMove = actionToAdd(
                        figure,
                        currentField.getXCoord() - i,
                        currentField.getYCoord() - i,
                        attackList,
                        moveList);
            }
        }

        boolean keepRightUpMove = true;
        boolean keepLeftDownMove = true;
        for (int i = 1; i < boardSize; i++) {
            if (keepRightUpMove && currentField.getXCoord() > 0 && currentField.getYCoord() < boardSize) {
                keepRightUpMove = actionToAdd(
                        figure,
                        currentField.getXCoord() - i,
                        currentField.getYCoord() + i,
                        attackList,
                        moveList);
            }

            if (keepLeftDownMove && currentField.getXCoord() < boardSize && currentField.getYCoord() > 0) {
                keepLeftDownMove = actionToAdd(
                        figure,
                        currentField.getXCoord() + i,
                        currentField.getYCoord() - i,
                        attackList,
                        moveList);
            }
        }
       // System.out.format("%d, %d", attackList.size(), moveList.size());

        return new ArrayList<IField>() {
            {
                addAll(attackList.stream()
                        .sorted(Comparator.comparingInt(o -> mediator.getFigure(o).getPrice()))
                        .collect(Collectors.toList()));
                addAll(moveList);
            }
        };
    }

    public ArrayList<IField> getHorizonVerticalFields(final Figure figure) {
 //       System.out.println("AIPotentialBasicPatterns.getHorizonVerticalFields");

        final ArrayList<IField> moveList = new ArrayList<>();
        final ArrayList<IField> attackList = new ArrayList<>();

        final IField currentField = mediator.getField(figure);

        boolean keepRightMove = true;
        boolean keepLeftMove = true;
        for (int i = 1; i < boardSize; i++) {
            if (keepRightMove) {
                keepRightMove = actionToAdd(
                        figure,
                        currentField.getXCoord(),
                        currentField.getYCoord() + i,
                        attackList,
                        moveList
                );
            }
            if (keepLeftMove) {
                keepLeftMove = actionToAdd(
                        figure,
                        currentField.getXCoord(),
                        currentField.getYCoord() - i,
                        attackList,
                        moveList
                );
            }
        }

        boolean keepUpMove = true;
        boolean keepDownMove = true;
        for (int i = 1; i < boardSize; i++) {
            if (keepUpMove) {
                keepUpMove = actionToAdd(
                        figure,
                        currentField.getXCoord() - i,
                        currentField.getYCoord(),
                        attackList,
                        moveList);
            }
            if (keepDownMove) {
                keepDownMove = actionToAdd(
                        figure,
                        currentField.getXCoord() + i,
                        currentField.getYCoord(),
                        attackList,
                        moveList);
            }
        }
     //   System.out.format("%d, %d", attackList.size(), moveList.size());

        return new ArrayList<IField>() {
            {
                addAll(attackList.stream()
                        .sorted(Comparator.comparingInt(o -> mediator.getFigure(o).getPrice()))
                        .collect(Collectors.toList()));
                addAll(moveList);
            }
        };
    }

    public ArrayList<IField> getKnightFields(final Figure figure) {
        final ArrayList<IField> attackList = new ArrayList<>();
        final ArrayList<IField> moveList = new ArrayList<>();

        final IField currentField = mediator.getField(figure);

        final int[] onesList = new int[]{1, -1};
        final int[] twosList = new int[]{2, -2};

        for (final int one : onesList) {
            for (final int two : twosList) {
                actionToAdd(figure,
                        currentField.getXCoord() + one,
                        currentField.getYCoord() + two,
                        attackList,
                        moveList);
                actionToAdd(figure,
                        currentField.getXCoord() + two,
                        currentField.getYCoord() + one,
                        attackList,
                        moveList);
            }
        }
      //  System.out.println("AIPotentialBasicPatterns.getKnightFields");
      //  System.out.format("%d, %d", attackList.size(), moveList.size());
        return new ArrayList<IField>() {
            {
                addAll(attackList.stream()
                        .sorted(Comparator.comparingInt(o -> mediator.getFigure(o).getPrice()))
                        .collect(Collectors.toList()));
                addAll(moveList);
            }
        };
    }

    public ArrayList<IField> getPawnFields(final Figure figure) {
     //   System.out.println("AIPotentialBasicPatterns.getPawnFields");

        final ArrayList<IField> possibleAttackFields = new ArrayList<>();
        final IField currentField = mediator.getField(figure);

        int offset = (figure.getColor() == Color.BLACK) ? 1 : -1;

        final int[] onesList = new int[]{1, -1};
        for (final int one : onesList) {
            addAttackField(
                    figure,
                    currentField.getXCoord() + offset,
                    currentField.getYCoord() + one,
                    possibleAttackFields
            );
        }

        boolean isFreePath = addMoveField(
                currentField.getXCoord() + offset,
                currentField.getYCoord(),
                possibleAttackFields
        );

        addIfAisleTake(figure, possibleAttackFields);

        if (isFreePath) {
            if (currentField.getXCoord() == 1 || currentField.getXCoord() == 6) {
                offset *= 2;
                addMoveField(
                        currentField.getXCoord() + offset,
                        currentField.getYCoord(),
                        possibleAttackFields
                );
            }
        }

        // для превращения ( только для изменения из пешки в другую фигуру)
        if (currentField.getXCoord() == 0 || currentField.getXCoord() == 7) {
            IField field = board.getField(currentField.getXCoord(), currentField.getYCoord());
            possibleAttackFields.add(field);
        }
      //  System.out.format("%d", possibleAttackFields.size());


        return possibleAttackFields;
    }

    private void addIfAisleTake(final Figure pawn, final Collection<IField> possibleAttackFields) {
        final IField currentField = mediator.getField(pawn);
        final Color color = pawn.getColor();
        final Figure lastFigure = storyGame.getLastFigureMove();

        int startXCoord = 3;
        int move = -1;
        if (color == Color.BLACK) {
            startXCoord = 4;
            move = 1;
        }

        final IField lastFieldLastFigure = storyGame.getLastField();
        final IField realFieldLastFigure = mediator.getField(lastFigure);

        if (realFieldLastFigure != null && lastFieldLastFigure != null) { // может быть null если было превращение
            if (currentField.getXCoord() == startXCoord && lastFigure.getColor() == Color.swapColor(color)) {
                if (lastFigure.getClass() == Pawn.class) {
                    if (Math.abs(realFieldLastFigure.getXCoord() - lastFieldLastFigure.getXCoord()) == 2) {
                        if (Math.abs(lastFieldLastFigure.getYCoord() - currentField.getYCoord()) == 1) {
                            possibleAttackFields.add(new Field(startXCoord + move, lastFieldLastFigure.getYCoord()));
                        }
                    }
                }
            }
        }
    }

    @Override
    public ArrayList<IField> getKingFields(final Figure king) {
      //  System.out.println("AIPotentialBasicPatterns.getKingFields");

        final ArrayList<IField> moveList = new ArrayList<>();
        final ArrayList<IField> attackList = new ArrayList<>();

        final IField fieldKing = mediator.getField(king);

        final int[] onesList = new int[]{1, -1};

        for (final int one : onesList) {
            for (final int two : onesList) {

                actionToAdd(king,
                        fieldKing.getXCoord() + one,
                        fieldKing.getYCoord() + two,
                        attackList,
                        moveList);

            }
        }

        for (final int one : onesList) {
            actionToAdd(
                    king,
                    fieldKing.getXCoord() + one,
                    fieldKing.getYCoord(),
                    attackList,
                    moveList
            );

            actionToAdd(
                    king,
                    fieldKing.getXCoord(),
                    fieldKing.getYCoord() + one,
                    attackList,
                    moveList
            );
        }

        addIfCastling(king, fieldKing, moveList);
       // System.out.format("%d", moveList.size());
        return new ArrayList<IField>() {
            {
                addAll(attackList.stream()
                                .sorted(Comparator.comparingInt(o -> mediator.getFigure(o).getPrice()))
                                .collect(Collectors.toList()));
                addAll(moveList);
            }
        };
    }

    private void addIfCastling(final Figure king, final IField fieldKing, final Collection<IField> possibleAttackFields) {
        // если король не ходил
        if (!storyGame.isMove(king)) {

            for (final Figure rook : mediator.getFigures(king.getColor())) {
                // если есть ладья которой не ходил
                if (rook.getClass() == Rook.class && !storyGame.isMove(rook) && mediator.getField(rook).getXCoord() == fieldKing.getXCoord()) {
                    // если между ними нет других фигур
                    boolean haveFigure = false;
                    final IField fieldRook = mediator.getField(rook);
                    final int dif = fieldKing.getYCoord() < fieldRook.getYCoord() ? 1 : -1;

                    IField fieldTemp = board.getField(fieldKing.getXCoord(), fieldKing.getYCoord() + dif);

                    while (fieldTemp.getYCoord() != fieldRook.getYCoord()) {
                        if (mediator.getFigure(fieldTemp) != null) {
                            haveFigure = true;
                        }
                        fieldTemp = board.getField(fieldKing.getXCoord(), fieldTemp.getYCoord() + dif);
                    }
                    //если фигур нет
                    if (!haveFigure) {
                        final IField finalField = board.getField(fieldKing.getXCoord(), fieldKing.getYCoord() + (2 * dif));
                        possibleAttackFields.add(finalField);
                    }
                }
            }
        }
    }

    /**
     * This method checks if it needed to add a field to the piece's attack list.
     * It checks if the field coordinates are within the dimensions of the Board.
     * If the fields contains some piece that has opposite color, the field added to the list.
     *
     * @param newFieldYCoord   {@link Integer} column coordinate of the field being checked
     * @return boolean value if moving through direction is possible (not covered with another piece).
     */
    private boolean actionToAdd(final Figure startFigure,
                                final int newFieldXCoord,
                                final int newFieldYCoord,
                                final ArrayList<IField> attackList,
                                final ArrayList<IField> moveList) {
        if (invalidCoordinates(newFieldXCoord, newFieldYCoord)) {
            return false;
        }

        final IField field = board.getField(newFieldXCoord, newFieldYCoord);
        final Figure figure = mediator.getFigure(field);

        addMoveField(newFieldXCoord, newFieldYCoord, moveList);
        addAttackField(startFigure, newFieldXCoord, newFieldYCoord, attackList);
        //addBarrierFields(startFigure, newFieldXCoord, newFieldYCoord);

        return figure == null;
    }

    private boolean addMoveField(
            final int newFieldXCoord,
            final int newFieldYCoord,
            final ArrayList<IField> moveList) {
        if (invalidCoordinates(newFieldXCoord, newFieldYCoord)) {
            return false;
        }
        final IField field = board.getField(newFieldXCoord, newFieldYCoord);
        final Figure figure = mediator.getFigure(field);

        if (figure == null) {
            moveList.add(field);
        }
        return false;
    }

    private void addAttackField(
            final Figure startFigure,
            final int newFieldXCoord,
            final int newFieldYCoord,
            final ArrayList<IField> attackList) {
        if (invalidCoordinates(newFieldXCoord, newFieldYCoord)) {
            return;
        }

        final IField field = board.getField(newFieldXCoord, newFieldYCoord);
        final Figure figure = mediator.getFigure(field);

        if (figure != null && figure.getColor() != startFigure.getColor()) {
            attackList.add(field);
        }
    }

    private void addBarrierFields(final Figure startFigure,
                                  final int newFieldXCoord,
                                  final int newFieldYCoord) {
        if (invalidCoordinates(newFieldXCoord, newFieldYCoord)) {
            return;
        }

        final IField field = board.getField(newFieldXCoord, newFieldYCoord);
        final Figure barrier = mediator.getFigure(field);

        if (barrier != null) {
            figureMoveBarrier.put(startFigure, field);
        }
    }

    private boolean invalidCoordinates(final int newFieldXCoord, final int newFieldYCoord) {
        return newFieldXCoord < 0
                || newFieldXCoord >= boardSize
                || newFieldYCoord < 0
                || newFieldYCoord >= boardSize;
    }
}
