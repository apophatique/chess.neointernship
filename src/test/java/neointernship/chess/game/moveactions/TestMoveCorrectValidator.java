package neointernship.chess.game.moveactions;

import neointernship.chess.game.gameplay.moveaction.MoveCorrectnessValidator;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.MoveState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMoveCorrectValidator extends TestHeadCommand {

    private static MoveCorrectnessValidator validator;

    @BeforeClass
    public static void init() {
        TestHeadCommand.init();
        validator = new MoveCorrectnessValidator(mediator, possibleActionList, board);
    }

    @After
    public void clear() {
        super.clear();
    }

    @Test
    public void testAllowed() {

        final Figure kingW = new King(Color.BLACK);
        final IField fieldKingW = new Field(7, 4);

        addFigure(fieldKingW, kingW);

        IAnswer answer = new Answer(7, 4, 7, 5, 'Q');

        MoveState result = validator.check(Color.BLACK, answer);

        assertEquals(result, MoveState.ALLOWED);
    }

    @Test
    public void testRestrict() {
        final Figure kingW = new King(Color.BLACK);
        final IField fieldKingW = new Field(7, 4);
        addFigure(fieldKingW, kingW);

        IAnswer answer = new Answer(7, 4, 0, 5, 'Q');

        MoveState result = validator.check(Color.BLACK, answer);

        assertEquals(result, MoveState.RESTRICT);
    }

    /**
     * Проверка, что нельзя сделать ход фигурой другого цвета.
     */
    @Test
    public void testMoveOutOfTurn() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(0, 0);
        addFigure(fieldKingW, kingW);

        final Figure kingB = new King(Color.BLACK);
        final IField fieldKingB = new Field(7, 4);
        addFigure(fieldKingB, kingB);

        IAnswer answer = new Answer(7, 4, 7, 5, 'Q');

        MoveState result = validator.check(Color.WHITE, answer);

        assertEquals(result, MoveState.RESTRICT);
    }

    /**
     * попытка сделать ход из клетки, на которой нет фигуры
     */
    @Test
    public void testEmptyField() {
        final Figure kingW = new King(Color.WHITE);
        final IField fieldKingW = new Field(0, 0);
        addFigure(fieldKingW, kingW);

        IAnswer answer = new Answer(7, 4, 7, 5, 'Q');

        MoveState result = validator.check(Color.WHITE, answer);

        assertEquals(result, MoveState.RESTRICT);
    }
}
