package neointernship.web.client.GUI.Input;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.web.client.player.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface IInput {

    public String getUserName() throws InterruptedException;

    public Color getColor() throws InterruptedException;

    public String getHandShakeAnswer() throws InterruptedException;

    public void invise();

    public String getMoveAnswer() throws InterruptedException;

    public String getTransformAnswer() throws InterruptedException;

    public PlayerType getPlayerType() throws InterruptedException;

    public void endGame(final EnumGameState enumGameState, final Color color) throws InterruptedException;

    boolean isVoid();
}
