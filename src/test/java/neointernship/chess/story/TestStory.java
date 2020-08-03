package neointernship.chess.story;

import neointernship.chess.game.end.TestHeadEnd;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestStory {
    private Map<IField, Figure> fieldFigureMap;
    private Map<Integer, IAnswer> answerMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
        answerMap = new HashMap<>();
    }
    @Test
    public void testGet() {
        IMediator mediator = new Mediator();
        IStoryGame storyGame = new StoryGame(mediator);

        Figure king = new King(Color.WHITE);
        IField field = new Field(0, 0);

        mediator.addNewConnection(field, king);

        storyGame.update(king);

        IField result = storyGame.getLastField();
        IField expectedField = new Field(0, 0);

        assertNotNull(result);
        assertEquals(expectedField, result);
        assertEquals(king, storyGame.getLastFigureMove());
        assertTrue(storyGame.isMove(king));
    }

    @Test
    public void testUpdate(){

        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(7, 4);
        fieldFigureMap.put(fieldKingW, kingW);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answer = new Answer(7, 4, 7, 5, 'Q');

        IStoryGame result = testHeadEnd.getStoryGame();

        assertFalse(result.isMove(kingW));

        testHeadEnd.doAllowIteration(answer);

        assertTrue(result.isMove(kingW));
        assertEquals(kingW,result.getLastFigureMove());
        assertEquals(fieldKingW,result.getLastField());
    }

    @Test
    public void testCastling(){
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(7,4);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure rookW = new Rook(Color.WHITE);
        final IField fieldRookW = new Field(7,7);
        fieldFigureMap.put(fieldRookW, rookW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(0,0);
        fieldFigureMap.put(fieldKingB, kingB);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);

        IAnswer answer1 = new Answer(7,4, 6,4, 'Q');
        IAnswer answer2 = new Answer(0, 0, 0, 1, 'Q');

        IAnswer answer3 = new Answer(6,4, 7,4, 'Q');
        IAnswer answer4 = new Answer(0, 1, 0, 0, 'Q');

        IAnswer answerMain = new Answer(7,4, 7, 6, 'Q');

        answerMap.put(1, answer1);
        answerMap.put(2, answer2);
        answerMap.put(3, answer3);
        answerMap.put(4, answer4);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        testHeadEnd.doIterations(answerMap);

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        // ход приводящий к 3х кратному повторению позиции
        testHeadEnd.doRestringIteration(answerMain);
    }

}
