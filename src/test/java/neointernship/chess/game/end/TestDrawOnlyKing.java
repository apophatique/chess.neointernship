package neointernship.chess.game.end;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestDrawOnlyKing {
    private Map<IField, Figure> fieldFigureMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
    }

    @Test
    public void test() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(7, 4);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(0, 4);
        fieldFigureMap.put(fieldKingB, kingB);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answer = new Answer(7, 4, 6, 4, 'Q');

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doAllowIteration(answer);

        assertEquals(EnumGameState.DRAW_ONLY_KING, testHeadEnd.getState().getValue());
        assertEquals(Color.BOTH, testHeadEnd.getState().getColor());
    }


}
