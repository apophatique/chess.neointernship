package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.activecolorcontroller.IActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gameprocesscontroller.GameProcessController;
import neointernship.chess.game.gameplay.gameprocesscontroller.IGameProcessController;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.IGameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.DrawController;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.subscriber.ISubscriber;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.logger.IGameLogger;
import neointernship.web.client.communication.message.ChessCodes;

/**
 * Класс, реализующий основное ядро игры (игровой цикл)
 */
public class GameLoop implements IGameLoop {
    private final IActiveColorController activeColorController;
    private final IGameStateController gameStateController;
    private final IGameProcessController gameProcessController;
    private final IKingStateController kingStateController;
    private final IConsoleBoardWriter consoleBoardWriter;
    private final DrawController drawController; // todo сделать его gameStateController

    private Color activeColor;

    public GameLoop(final IMediator mediator,
                    final IPossibleActionList possibleActionList,
                    final IBoard board,
                    final IActiveColorController activeColorController,
                    final IStoryGame storyGame) {

        this.activeColorController = activeColorController;
        gameStateController = new GameStateController(possibleActionList, mediator);
        gameProcessController = new GameProcessController(mediator, possibleActionList, board,storyGame);
        kingStateController = new KingsStateController(possibleActionList, mediator, Color.WHITE);
        drawController = new DrawController(mediator,storyGame);

        consoleBoardWriter = new ConsoleBoardWriter(mediator, board);
        kingStateController.addToSubscriber((ISubscriber) gameStateController);
    }

    /**
     * Активация главного игрового цикла.
     */
    @Override
    public ChessCodes doIteration(final IAnswer answer) {
        activeColor = activeColorController.getCurrentColor();

        gameProcessController.makeTurn(activeColor, answer);

        final ChessCodes chessCodes = gameProcessController.getChessCode();

        if(chessCodes != ChessCodes.ERROR) {
            kingStateController.setActiveColor(activeColor);
            kingStateController.updateState();

            consoleBoardWriter.printBoard();
        }
        return chessCodes;
    }

    @Override
    public boolean isAlive() {
        return gameStateController.isMatchAlive() && !drawController.isDraw();
    }

    @Override
    public IGameState getMatchResult() {
        return gameStateController.getState();
    }
}
