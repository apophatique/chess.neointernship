package neointernship.web.client.communication.data;

import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

public class BoardInfoContainer {
    public IMediator getMediator() {
        return mediator;
    }

    public IBoard getBoard() {
        return board;
    }

    private final IMediator mediator;
    private final IBoard board;

    public BoardInfoContainer(final IMediator mediator, final IBoard board) {
        this.mediator = mediator;
        this.board = board;
    }
}
