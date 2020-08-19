package neointernship.web.client.player.bot.ai.positionvalue;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.story.IStoryGame;

public class PositionValueEstimator {
    public static int calculate(final IBoard board,
                         final IMediator mediator,
                         final IStoryGame storyGame,
                         final Color activeColor) {
        int value = 0;

        for (final Figure figure : mediator.getFigures(activeColor)) {
            value += figure.getPrice();
        }

        for (final Figure figure : mediator.getFigures(Color.swapColor(activeColor))) {
            value -= figure.getPrice();
        }

        System.out.println(value);
        return value;
    }
}
