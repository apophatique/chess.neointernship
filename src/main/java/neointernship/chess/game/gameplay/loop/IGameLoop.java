package neointernship.chess.game.gameplay.loop;

import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.message.TurnStatus;

public interface IGameLoop {
    TurnStatus doIteration(final IAnswer answer);
    boolean isAlive();
    IGameState getMatchResult();
}
