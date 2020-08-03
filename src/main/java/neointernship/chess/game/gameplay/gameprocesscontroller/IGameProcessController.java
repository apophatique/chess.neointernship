package neointernship.chess.game.gameplay.gameprocesscontroller;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.communication.message.TurnStatus;

public interface IGameProcessController {
    void makeTurn(final Color color, final IAnswer answer);
    TurnStatus getTurnStatus();
}
