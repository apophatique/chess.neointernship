package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.message.TurnStatus;

public interface IAllowCommand {
    void execute(final IAnswer answer);
    TurnStatus getChessCode();
}