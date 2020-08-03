package neointernship.chess.game.gameplay.moveaction.commands.allow;


import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.web.client.communication.message.TurnStatus;

public abstract class AbstractCommand implements IAllowCommand {

    protected final IMediator mediator;
    protected final IBoard board;
    protected TurnStatus turnStatus;

    public AbstractCommand(IBoard board, IMediator mediator) {
        this.mediator = mediator;
        this.board = board;
    }

    @Override
    public boolean isCorrect(final Color colorFigure, final IPossibleActionList possibleActionList) {
        KingIsAttackedComputation kingIsAttackedComputation =
                new KingIsAttackedComputation(possibleActionList, mediator);

        return !kingIsAttackedComputation.kingIsAttacked(colorFigure);
    }

    @Override
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }
}
