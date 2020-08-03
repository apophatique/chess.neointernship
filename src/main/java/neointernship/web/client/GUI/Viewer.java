package neointernship.web.client.GUI;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.web.client.GUI.Input.Input;
import neointernship.web.client.GUI.board.view.BoardView;


public class Viewer {
    private final Input input;
    private final BoardView boardView;

    public Viewer(final IBoard board, final IMediator mediator) {
        input = new Input();
        boardView = new BoardView(mediator, board);
    }

    public Color getPlayerColor() throws InterruptedException {
        return input.getColor();
    }

    public String getPlayerName() throws InterruptedException {
        return input.getUserName();
    }

    public String getMoveAnswer() throws InterruptedException {
        return input.getMoveAnswer();
    }

    public void updateBoardView() {
        boardView.update();
    }

    public void displayBoard() throws InterruptedException {
        boardView.display();
    }
}
