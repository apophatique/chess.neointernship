package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.enums.Color;

public interface IGameStateController {
    boolean isMatchAlive();

    IGameState getState();

    void update(final Color color);
}
