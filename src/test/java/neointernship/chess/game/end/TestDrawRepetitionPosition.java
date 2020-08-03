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

public class TestDrawRepetitionPosition {
    private Map<IField, Figure> fieldFigureMap;
    private Map<Integer, IAnswer> answerMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
        answerMap = new HashMap<>();
    }

    @Test
    public void test() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(0, 0);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure rookW = new King(Color.WHITE);
        final IField fieldRookW = new Field(1, 1);
        fieldFigureMap.put(fieldRookW, rookW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 7);
        fieldFigureMap.put(fieldKingB, kingB);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answerW1 = new Answer(0, 0, 0, 1, 'Q');
        IAnswer answerW2 = new Answer(0, 1, 0, 0, 'Q');

        IAnswer answerB1 = new Answer(7, 7, 6, 6, 'Q');
        IAnswer answerB2 = new Answer(6, 6, 7, 7, 'Q');

        answerMap.put(1, answerW1);
        answerMap.put(2, answerB1);
        answerMap.put(3, answerW2);
        answerMap.put(4, answerB2);
        // позиция повторилась второй раз
        answerMap.put(5, answerW1);
        answerMap.put(6, answerB1);
        answerMap.put(7, answerW2);
        answerMap.put(8, answerB2);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doIterations(answerMap);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        // ход приводящий к 3х кратному повторению позиции
        testHeadEnd.doAllowIteration(answerW1);

        assertEquals(EnumGameState.DRAW_REPETITION_POSITION, testHeadEnd.getState().getValue());
        assertEquals(Color.BOTH, testHeadEnd.getState().getColor());
    }
    // todo написать проверку, что повторение не считается, если уже нет возможности для рокировки, а она была
    // то есть список ходов другой
}
