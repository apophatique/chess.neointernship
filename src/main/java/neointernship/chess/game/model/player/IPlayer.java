package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;

public interface IPlayer {
    //    IAnswer getAnswer(final IMediator mediator, final IPossibleActionList list);
    Color getColor();

    String getName();

    IAnswer getAnswer(IMediator mediator, IPossibleActionList possibleActionList);
}