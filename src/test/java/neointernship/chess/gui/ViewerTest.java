package neointernship.chess.gui;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.GUI.Viewer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewerTest {
    Viewer viewer;
    Mediator mediator;
    Board board;

    @Before
    public void set() {
        mediator = new Mediator();
        board = new Board();
        Factory figureFactory = new Factory();

        IField field1 = board.getField(0, 0);
        mediator.addNewConnection(field1, figureFactory.createFigure('B', Color.WHITE));

        IField field2 = board.getField(3, 4);
        mediator.addNewConnection(field2, figureFactory.createFigure('Q', Color.BLACK));

        viewer = new Viewer(board, mediator);
    }

    @Test
    public void getNameTest() throws InterruptedException {
        assertEquals("zxc", viewer.getPlayerName());
    }

    @Test
    public void displayBoardTest() throws InterruptedException {
        viewer.displayBoard();
    }
}
