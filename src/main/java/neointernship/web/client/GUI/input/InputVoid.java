package neointernship.web.client.GUI.input;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.web.client.player.PlayerType;

public class InputVoid implements IInput {

    @Override
    public String getUserName() throws InterruptedException {
        return "random bot";
    }

    @Override
    public Color getColor() throws InterruptedException {
        return Color.BOTH;
    }

    @Override
    public String getHandShakeAnswer() throws InterruptedException {
        return "да";
    }

    @Override
    public void invise() {

    }

    @Override
    public String getMoveAnswer() throws InterruptedException {
        return null;
    }

    @Override
    public String getTransformAnswer() throws InterruptedException {
        return null;
    }

    @Override
    public PlayerType getPlayerType() throws InterruptedException {
        return null;
    }

    @Override
    public void endGame(EnumGameState enumGameState, Color color) {

    }

    @Override
    public boolean isVoid() {
        return true;
    }
}
