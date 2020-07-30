package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;

public class AttackCommand extends AbstractCommand implements IAllowCommand{

    public AttackCommand(final IBoard board, final IMediator mediator) {
        super(board, mediator);
    }

    @Override
    public void execute(final IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());

        final Figure startFigure = mediator.getFigure(startField);

        mediator.deleteConnection(startField);
        mediator.deleteConnection(finalField);
        mediator.addNewConnection(finalField, startFigure);
    }

    @Override
    public boolean check(IAnswer answer) {
        IField finalField = board.getField(answer.getFinalX(),answer.getFinalY());
        Figure finalFigure = mediator.getFigure(finalField);
        return finalFigure != null;
    }

    @Override
    public TurnStatus getTurnStatus() {
        return TurnStatus.ATTACK;
    }

}
