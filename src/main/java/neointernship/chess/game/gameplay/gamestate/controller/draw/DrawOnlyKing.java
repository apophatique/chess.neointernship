package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.mediator.IMediator;

public class DrawOnlyKing implements IDrawController {

    @Override
    public boolean isDraw(IMediator mediator) {
        return mediator.getFigures().size() == 2;
    }

    @Override
    public EnumGameState getState() {
        return EnumGameState.DRAW_ONLY_KING;
    }
}
