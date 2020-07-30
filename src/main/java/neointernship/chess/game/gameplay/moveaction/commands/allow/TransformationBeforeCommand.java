package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;

public class TransformationBeforeCommand extends AbstractCommand implements IAllowCommand {

    public TransformationBeforeCommand(final IBoard board, final IMediator mediator) {
        super(board, mediator);
    }

    @Override
    public void execute(final IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());

        final Figure startFigure = mediator.getFigure(startField);
        final Figure finalFigure = mediator.getFigure(finalField);

        mediator.deleteConnection(startField);
        if (finalFigure != null) {
            mediator.deleteConnection(finalField);
        }
        mediator.addNewConnection(finalField, startFigure);
    }

    @Override
    public boolean check(IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());
        final Figure startFigure = mediator.getFigure(startField);
        final IField finalField = board.getField(answer.getFinalX(), answer.getFinalY());

        return startFigure.getClass() == Pawn.class && (
                finalField.getXCoord() == board.getSize() - 1 ||
                        finalField.getXCoord() == 0);
    }

    @Override
    public TurnStatus getTurnStatus() {
        return TurnStatus.TRANSFORMATION_BEFORE;
    }
}
