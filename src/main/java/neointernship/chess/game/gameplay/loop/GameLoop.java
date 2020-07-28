package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.gameplay.activecolorcontroller.IActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gameprocesscontroller.GameProcessController;
import neointernship.chess.game.gameplay.gameprocesscontroller.IGameProcessController;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.IGameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.DrawStateController;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.gameplay.kingstate.subscriber.IKingStateSubscriber;
import neointernship.chess.game.story.IStoryGame;
import neointernship.web.client.communication.message.TurnStatus;

import java.util.Collection;
import java.util.HashSet;

/**
 * Класс, реализующий основное ядро игры (игровой цикл)
 */
public class GameLoop implements IGameLoop {
    private final IActiveColorController activeColorController;

    private final IGameStateController gameStateController;

    private final IGameProcessController gameProcessController;

    private Color activeColor;

    public GameLoop(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IActiveColorController activeColorController,
                    final IStoryGame storyGame) {

        this.activeColorController = activeColorController;

        gameStateController = new GameStateController(possibleActionList, mediator,storyGame);

        gameProcessController = new GameProcessController(mediator, possibleActionList, board,storyGame);
    }

    /**
     * Активация главного игрового цикла.
     */
    @Override
    public TurnStatus doIteration(final IAnswer answer) {
        activeColor = activeColorController.getCurrentColor();

        gameProcessController.makeTurn(activeColor, answer);

        final TurnStatus turnStatus = gameProcessController.getTurnStatus();

        if(chessCodes != ChessCodes.ERROR) {
            if (chessCodes != ChessCodes.TRANSFORMATION_BEFORE) activeColorController.update();
        if(turnStatus != TurnStatus.ERROR) {
            activeColorController.update();
            activeColor = activeColorController.getCurrentColor();

            gameStateController.update(activeColor);
        }
        return turnStatus;
    }

    @Override
    public boolean isAlive() {
        return gameStateController.isMatchAlive();
    }

    @Override
    public IGameState getMatchResult() {
        return gameStateController.getState();
    }
}
