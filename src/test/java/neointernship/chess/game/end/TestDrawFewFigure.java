package neointernship.chess.game.end;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Knight;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestDrawFewFigure {
    private Map<IField, Figure> fieldFigureMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
    }

    @Test
    public void testKingKnight() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(0, 0);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure knightW = new Knight(Color.WHITE);
        final IField fieldKnightW = new Field(7, 0);
        fieldFigureMap.put(fieldKnightW, knightW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 7);
        fieldFigureMap.put(fieldKingB, kingB);

        final Figure knightB = new Knight(Color.BLACK);
        final IField fieldKnightB = new Field(6, 2);
        fieldFigureMap.put(fieldKnightB, knightB);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answer = new Answer(7, 0, 6, 2, 'Q');

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.printBoard();
        testHeadEnd.doAllowIteration(answer);
        testHeadEnd.printBoard();

        assertEquals(EnumGameState.DRAW_FEW_FIGURE, testHeadEnd.getState().getValue());
        assertEquals(Color.BOTH, testHeadEnd.getState().getColor());
    }

    @Test
    public void testKingBishopGoodField() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(0, 0);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure bishopW = new Bishop(Color.WHITE);
        final IField fieldBishopW = new Field(1, 1);
        fieldFigureMap.put(fieldBishopW, bishopW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 7);
        fieldFigureMap.put(fieldKingB, kingB);

        final Figure bishopB = new Bishop(Color.BLACK);
        final IField fieldBishopB = new Field(5, 5);
        fieldFigureMap.put(fieldBishopB, bishopB);

        assertEquals(fieldBishopB.getColor(), fieldBishopW.getColor());

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answer = new Answer(1, 1, 2, 2, 'Q');

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.printBoard();
        testHeadEnd.doAllowIteration(answer);
        testHeadEnd.printBoard();

        assertEquals(EnumGameState.DRAW_FEW_FIGURE, testHeadEnd.getState().getValue());
        assertEquals(Color.BOTH, testHeadEnd.getState().getColor());
    }

    @Test
    public void testKingBishopBadField() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(0, 0);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure bishopW = new Bishop(Color.WHITE);
        final IField fieldBishopW = new Field(1, 0);
        assertEquals(Color.BLACK, fieldBishopW.getColor());
        fieldFigureMap.put(fieldBishopW, bishopW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 7);
        fieldFigureMap.put(fieldKingB, kingB);

        final Figure bishopB = new Bishop(Color.BLACK);
        final IField fieldBishopB = new Field(3, 5);
        assertEquals(Color.WHITE, fieldBishopB.getColor());
        fieldFigureMap.put(fieldBishopB, bishopB);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answer = new Answer(1, 0, 2, 1, 'Q');

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.printBoard();
        testHeadEnd.doAllowIteration(answer);
        testHeadEnd.printBoard();

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());
        assertEquals(Color.BOTH, testHeadEnd.getState().getColor());
    }
}
