package neointernship.chess.game.gameplay.gamestate.controller;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.DrawStateController;
import neointernship.chess.game.gameplay.gamestate.state.GameState;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.gamestate.update.FiguresHaveMovesComputation;
import neointernship.chess.game.gameplay.gamestate.update.GameStateDefineLogic;
import neointernship.chess.game.gameplay.kingstate.controller.IKingStateController;
import neointernship.chess.game.gameplay.kingstate.controller.KingsStateController;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

public class GameStateController implements IGameStateController {
    private IGameState currentState;
    private final IPossibleActionList possibleActionList;

    private final FiguresHaveMovesComputation figuresHaveMovesComputation;
    private final GameStateDefineLogic gameStateDefineLogic;

    private final DrawStateController drawStateController;
    private final IKingStateController kingStateController;

    public GameStateController(final IPossibleActionList possibleActionList,
                               final IMediator mediator,
                               final IStoryGame storyGame) {
        this.possibleActionList = possibleActionList;

        currentState = new GameState(EnumGameState.ALIVE, Color.BOTH);
        figuresHaveMovesComputation = new FiguresHaveMovesComputation(possibleActionList, mediator);
        gameStateDefineLogic = new GameStateDefineLogic();

        drawStateController = new DrawStateController(mediator, storyGame);
        kingStateController = new KingsStateController(possibleActionList, mediator);

    }

    @Override
    public boolean isMatchAlive() {
        return currentState.getValue() == EnumGameState.ALIVE;
    }

    @Override
    public IGameState getState() {
        return currentState;
    }

    @Override
    public void update(Color color) {
        possibleActionList.updateRealLists();

        kingStateController.update(color);

        final KingState kingState = kingStateController.getKingState(color);

        boolean figuresHaveMoves = figuresHaveMovesComputation.check(color);

        currentState = new GameState(gameStateDefineLogic.getState(kingState, figuresHaveMoves), color);

        if (currentState.getValue() == EnumGameState.ALIVE) {
            drawStateController.update();
            currentState = drawStateController.getState();
        }
    }

}

