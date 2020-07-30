package neointernship.chess.game.moveactions;

import neointernship.chess.game.end.TestHeadEnd;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestAisleTake extends TestAllowCommand {
    private Map<IField, Figure> fieldFigureMap;
    private TestHeadEnd testHeadEnd;

    @Before
    public void testBefore() {
        fieldFigureMap = new HashMap<>();
    }

    @Test
    public void testWhite() {
        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(3, 4);

        Figure figureB = new Pawn(Color.BLACK);
        IField fieldB = new Field(3, 3);

        addFigure(fieldW, figureW);
        addFigure(fieldB, figureB);

        assertEquals(2, mediator.getFigures().size());

        IAnswer answer = new Answer(3, 4, 2, 3, 'Q');

        TurnStatus result = allowMoveCommand.execute(figureW.getColor(), answer);

        assertEquals(TurnStatus.AISLE_TAKE, result);

        assertEquals(1, mediator.getFigures().size());

        IField fieldPawnWExpected = new Field(2, 3);
        assertEquals(fieldPawnWExpected, mediator.getField(figureW));

        assertNull(mediator.getField(figureB));
    }

    @Test
    public void testBlack() {
        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(4, 4);

        Figure figureB = new Pawn(Color.BLACK);
        IField fieldB = new Field(4, 3);

        addFigure(fieldW, figureW);
        addFigure(fieldB, figureB);

        assertEquals(2, mediator.getFigures().size());

        IAnswer answer = new Answer(4, 4, 5, 3, 'Q');

        TurnStatus result = allowMoveCommand.execute(figureW.getColor(), answer);

        assertEquals(TurnStatus.AISLE_TAKE, result);

        assertEquals(1, mediator.getFigures().size());

        IField fieldPawnWExpected = new Field(5, 3);
        assertEquals(fieldPawnWExpected, mediator.getField(figureW));

        assertNull(mediator.getField(figureB));
    }

    @Test
    public void testInvalidMove() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(3, 6);
        fieldFigureMap.put(fieldKingW, kingW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 1);
        fieldFigureMap.put(fieldKingB, kingB);

        final Figure rookB = new Rook(Color.BLACK);
        final IField fieldRookB = new Field(3, 0);
        fieldFigureMap.put(fieldRookB, rookB);

        final Figure pawnWhite = new Pawn(Color.WHITE);
        final IField fieldPawnWhite = new Field(3, 4);
        fieldFigureMap.put(fieldPawnWhite, pawnWhite);

        final Figure pawnBlack = new Pawn(Color.BLACK);
        final IField fieldPawnBlack = new Field(1, 5);
        fieldFigureMap.put(fieldPawnBlack, pawnBlack);

        testHeadEnd = new TestHeadEnd(fieldFigureMap);
        testHeadEnd.printBoard();

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());

        IAnswer answer0 = new Answer(3,6, 3,7, 'Q');
        testHeadEnd.doAllowIteration(answer0);
        testHeadEnd.printBoard();

        IAnswer answer1 = new Answer(1,5, 3,5, 'Q');
        testHeadEnd.doAllowIteration(answer1);
        testHeadEnd.printBoard();

        IAnswer answer2 = new Answer(3,4, 2,5, 'Q');
        testHeadEnd.doRestringIteration(answer2);
        testHeadEnd.printBoard();

        assertEquals(EnumGameState.ALIVE, testHeadEnd.getState().getValue());
    }
}
