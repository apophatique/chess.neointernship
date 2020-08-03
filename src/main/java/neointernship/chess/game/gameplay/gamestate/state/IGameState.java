package neointernship.chess.game.gameplay.gamestate.state;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;

public interface IGameState {
    EnumGameState getValue();

    Color getColor();

    void updateValue(EnumGameState gameState, Color color); // todo нигде не вызывается !!!
}
