package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.message.TurnStatus;

public interface IMoveCommand {
    TurnStatus execute(final IAnswer answer);
}
