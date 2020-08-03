package neointernship.chess.game.gameplay.moveaction.commands.allow;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Pawn;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.TurnStatus;

public class TransformationAfterCommand extends AbstractCommand implements IAllowCommand {

    public TransformationAfterCommand(IBoard board, IMediator mediator) {
        super(board, mediator);
        turnStatus = TurnStatus.TRANSFORMATION_AFTER;
    }

    @Override
    public void execute(IAnswer answer) {
        final IField startField = board.getField(answer.getStartX(), answer.getStartY());

        final Figure startFigure = mediator.getFigure(startField);

        final Figure newFigure = new Factory().createFigure(answer.getSimbol(), startFigure.getColor());

        mediator.deleteConnection(startField);

        mediator.addNewConnection(startField, newFigure);
    }

    @Override
    public boolean check(final IField startField,final IField finishField) {
        final Figure startFigure = mediator.getFigure(startField);
        return startFigure.getClass() == Pawn.class && (
                startField.getXCoord() == board.getSize() - 1 ||
                        startField.getXCoord() == 0);
    }
}
