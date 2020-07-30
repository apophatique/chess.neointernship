package neointernship.chess.game.moveactions;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMove extends TestAllowCommand {

    @Test
    public void testMove() {

        Figure figure = new King(Color.WHITE);
        IField field = new Field(0, 0);

        addFigure(field, figure);

        IAnswer answer = new Answer(0, 0, 1, 1, 'Q');

        TurnStatus result = allowMoveCommand.execute(answer);

        assertEquals(TurnStatus.MOVE, result);

        IField fieldKingExpected = new Field(1, 1);

        assertEquals(fieldKingExpected, mediator.getField(figure));
    }
}
