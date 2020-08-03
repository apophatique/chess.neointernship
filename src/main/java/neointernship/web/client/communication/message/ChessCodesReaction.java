package neointernship.web.client.communication.message;

import neointernship.chess.game.gameplay.moveaction.commands.allow.*;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

import java.util.HashMap;

public class ChessCodesReaction {
    private final HashMap<TurnStatus, IAllowCommand> chessCodesReaction;

    public ChessCodesReaction(final IBoard board, final IMediator mediator) {
        this.chessCodesReaction = new HashMap<>();
        initChessCodesReaction(chessCodesReaction, board, mediator);
    }

    private void initChessCodesReaction(final HashMap<TurnStatus, IAllowCommand> chessCodesReaction,
                                        final IBoard board,
                                        final IMediator mediator) {
        chessCodesReaction.put(TurnStatus.MOVE, new MoveCommand(board, mediator));
        chessCodesReaction.put(TurnStatus.ATTACK, new AttackCommand(board, mediator));
        chessCodesReaction.put(TurnStatus.CASTLING, new CastlingCommand(board, mediator));
        chessCodesReaction.put(TurnStatus.AISLE_TAKE, new AisleTakeCommand(board, mediator));
        chessCodesReaction.put(TurnStatus.TRANSFORMATION_AFTER, new TransformationAfterCommand(board, mediator));
        chessCodesReaction.put(TurnStatus.TRANSFORMATION_BEFORE, new TransformationBeforeCommand(board, mediator));
    }

    public IAllowCommand get(final TurnStatus turnStatus){
        return chessCodesReaction.get(turnStatus);
    }
}
