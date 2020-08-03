package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

public class DrawFiftyStep implements IDrawController {

    private final static Integer MAX_COUNT_STEP = 50;
    private final IStoryGame storyGame;
    private int countStep;
    private int lastSizeMediator;

    public DrawFiftyStep(IStoryGame storyGame) {
        this.storyGame = storyGame;
        countStep = 0;
        lastSizeMediator = 32;
    }

    /**
     * Правило 50 ходов. Прошло 50 ходов подряд, без вязтия фигуры или хода першки.
     *
     * @param mediator
     * @return
     */
    @Override
    public boolean isDraw(IMediator mediator) {
        final int newSizeMediator = mediator.getFigures().size();

        final Figure figure = storyGame.getLastFigureMove();
        if (figure == null) return false;

        if (lastSizeMediator == newSizeMediator
                && figure.getClass() != Pawn.class) {
            countStep++;
        } else {
            countStep = 0;
            lastSizeMediator = newSizeMediator;
        }
        return countStep >= MAX_COUNT_STEP;
    }

    @Override
    public EnumGameState getState() {
        return EnumGameState.DRAW_FIFTY_STEP;
    }
}
