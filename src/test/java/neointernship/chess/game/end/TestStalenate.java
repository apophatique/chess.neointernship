package neointernship.chess.game.end;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestStalenate {
    private Map<IField, Figure> fieldFigureMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
    }

    @Test
    public void test() {

        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(5, 7);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 7);
        fieldFigureMap.put(fieldKingB, kingB);

        final Figure rook1 = new Rook(Color.WHITE);
        final IField fieldRook1 = new Field(0, 6);
        fieldFigureMap.put(fieldRook1, rook1);


        testHeadEnd = new TestHeadEnd(fieldFigureMap);


        IAnswer answer = new Answer(0, 6, 6, 6, 'Q');

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doAllowIteration(answer);

        assertEquals(EnumGameState.STALEMATE, testHeadEnd.getState().getValue());
        assertEquals(Color.BLACK, testHeadEnd.getState().getColor());
    }
}
