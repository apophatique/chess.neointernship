package neointernship.chess.game.end;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestMate {

    private Map<IField, Figure> fieldFigureMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
    }

    @Test
    public void testLinerMate() {

        final Figure kingW = new King(Color.BLACK);
        final IField fieldKingW = new Field(7, 4);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure kingB = new King(Color.WHITE);
        final IField fieldKingB = new Field(0, 4);
        fieldFigureMap.put(fieldKingB, kingB);

        final Figure rook1 = new Rook(Color.WHITE);
        final IField fieldRook1 = new Field(6, 7);
        fieldFigureMap.put(fieldRook1, rook1);

        final Figure rook2 = new Rook(Color.WHITE);
        final IField fieldRook2 = new Field(6, 0);
        fieldFigureMap.put(fieldRook2, rook2);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answer = new Answer(6, 0, 7, 0, 'Q');

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doAllowIteration(answer);

        assertEquals(EnumGameState.MATE, testHeadEnd.getState().getValue());
        assertEquals(Color.BLACK, testHeadEnd.getState().getColor());
    }

    @Test
    public void testQueenMate() {
        final Figure kingW = new King(Color.BLACK);
        final IField fieldKingW = new Field(7, 4);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure kingB = new King(Color.WHITE);
        final IField fieldKingB = new Field(5, 4);
        fieldFigureMap.put(fieldKingB, kingB);

        final Figure queen = new Queen(Color.WHITE);
        final IField fieldQ = new Field(6, 0);
        fieldFigureMap.put(fieldQ, queen);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answer = new Answer(6, 0, 6, 4, 'Q');

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doAllowIteration(answer);

        assertEquals(EnumGameState.MATE, testHeadEnd.getState().getValue());
        assertEquals(Color.BLACK, testHeadEnd.getState().getColor());
    }
}
