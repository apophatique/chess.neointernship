package neointernship.chess.game.gameplay.activecolorcontroller;

import neointernship.chess.game.model.enums.Color;

public class ActiveColorController implements IActiveColorController, IColorControllerSubscriber {
    private final Color white;
    private final Color black;

    private Color activeColor;

    public ActiveColorController() {
        white = Color.WHITE;
        black = Color.BLACK;

        activeColor = black;
    }

    @Override
    public void update() {
        activeColor = (activeColor != white) ? white : black;
    }

    @Override
    public Color getCurrentColor() {
        return activeColor;
    }
}
