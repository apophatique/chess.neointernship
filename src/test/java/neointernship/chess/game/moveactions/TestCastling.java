package neointernship.chess.game.moveactions;

import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * класс проверяющий правильную реализацию рокировки при классических шахматах.
 */
public class TestCastling extends TestAllowCommand {
    @Test
    public void testCastlingWhiteRight() {
        check(false, Color.WHITE);
    }

    @Test
    public void testCastlingWhiteLeft() {
        check(true, Color.WHITE);
    }

    @Test
    public void testCastlingBlackRight() {
        check(false, Color.BLACK);
    }

    @Test
    public void testCastlingBlackLeft() {
        check(true, Color.BLACK);
    }

    public void check(final boolean isLeft, final Color color) {
        Figure king = new King(color);

        int startXCoordKing = color == Color.WHITE ? 7 : 0;
        IField fieldKing = new Field(startXCoordKing, 4);

        int startYCoordRook = 7;
        int offset = 2;
        if (isLeft) {
            startYCoordRook = 0;
            offset = -2;
        }

        Figure rook = new Rook(king.getColor());
        IField fieldRook = new Field(fieldKing.getXCoord(), startYCoordRook);

        addFigure(fieldKing, king);
        addFigure(fieldRook, rook);

        IAnswer answer = new Answer(
                fieldKing.getXCoord(),
                fieldKing.getYCoord(),
                fieldKing.getXCoord(),
                fieldKing.getYCoord() + offset,
                'Q');

        TurnStatus result = allowMoveCommand.execute(answer);

        assertEquals(TurnStatus.CASTLING, result);

        IField fieldKingExpected = new Field(answer.getFinalX(), answer.getFinalY());
        offset /= 2;
        IField fieldRookExpected = new Field(answer.getFinalX(), answer.getStartY() + offset);

        assertEquals(fieldKingExpected, mediator.getField(king));
        assertEquals(fieldRookExpected, mediator.getField(rook));
    }
}
