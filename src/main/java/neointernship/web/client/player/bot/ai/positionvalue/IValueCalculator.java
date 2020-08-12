package neointernship.web.client.player.bot.ai.positionvalue;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.story.IStoryGame;

public interface IValueCalculator {
    int calculate(final IBoard board,
                  final IMediator mediator,
                  final IStoryGame storyGame,
                  final Color activeColor);
}
