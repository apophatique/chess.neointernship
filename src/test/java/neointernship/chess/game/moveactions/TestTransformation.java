package neointernship.chess.game.moveactions;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Knight;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestTransformation extends TestAllowCommand {
    @Test
    public void testMove() {
        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(1, 0);

        addFigure(fieldW, figureW);

        assertEquals(1, mediator.getFigures().size());

        IAnswer answer = new Answer(1, 0, 0, 0, '0');

        TurnStatus result = allowMoveCommand.execute(answer);

        assertEquals(TurnStatus.TRANSFORMATION_BEFORE, result);

        assertEquals(1, mediator.getFigures().size());

        IField fieldExpected = new Field(0, 0);
        assertEquals(fieldExpected, mediator.getField(figureW));

    }

    @Test
    public void testAttack() {
        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(1, 0);

        Figure figureB = new Bishop(Color.BLACK);
        IField fieldB = new Field(0, 1);

        addFigure(fieldW, figureW);
        addFigure(fieldB, figureB);

        assertEquals(2, mediator.getFigures().size());

        IAnswer answer = new Answer(1, 0, 0, 1, '0');

        TurnStatus result = allowMoveCommand.execute(answer);

        assertEquals(TurnStatus.TRANSFORMATION_BEFORE, result);

        assertEquals(1, mediator.getFigures().size());

        IField fieldExpected = new Field(0, 1);
        assertEquals(fieldExpected, mediator.getField(figureW));

    }

    @Test
    public void testTrans() {
        Figure figureW = new Pawn(Color.WHITE);
        IField fieldW = new Field(0, 0);

        addFigure(fieldW, figureW);

        assertEquals(1, mediator.getFigures().size());

        IAnswer answer = new Answer(0, 0, 0, 0, 'N');

        TurnStatus result = allowMoveCommand.execute(answer);

        assertEquals(TurnStatus.TRANSFORMATION_AFTER, result);

        assertEquals(1, mediator.getFigures().size());

        assertNull(mediator.getField(figureW));
        assertEquals(Knight.class, mediator.getFigure(fieldW).getClass());

    }
}
