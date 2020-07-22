package neointernship.chess.game.gameplay.moveaction.commands;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.logger.IGameLogger;

public interface IMoveCommand {
    boolean execute(final Color color, final IAnswer answer, final IGameLogger gameLogger);
}
