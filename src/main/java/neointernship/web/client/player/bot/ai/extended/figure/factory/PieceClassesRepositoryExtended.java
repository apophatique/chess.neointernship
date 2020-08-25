package neointernship.web.client.player.bot.ai.extended.figure.factory;

import neointernship.chess.game.model.figure.piece.*;
import neointernship.web.client.player.bot.ai.extended.figure.figures.*;

import java.util.HashMap;
import java.util.Map;

public class PieceClassesRepositoryExtended {
    private Map<Character, Class<? extends Figure>> pieceMap;
    private static final Character KING_PIECE_SYMBOL = 'K';
    private static final Character QUEEN_PIECE_SYMBOL = 'Q';
    private static final Character BISHOP_PIECE_SYMBOL = 'B';
    private static final Character KNIGHT_PIECE_SYMBOL = 'N';
    private static final Character ROOK_PIECE_SYMBOL = 'R';
    private static final Character PAWN_PIECE_SYMBOL = 'P';

    public PieceClassesRepositoryExtended() {
        pieceMap = new HashMap<>();
        pieceMap.put(KING_PIECE_SYMBOL, KingExtended.class);
        pieceMap.put(QUEEN_PIECE_SYMBOL, QueenExtended.class);
        pieceMap.put(BISHOP_PIECE_SYMBOL, BishopExtended.class);
        pieceMap.put(KNIGHT_PIECE_SYMBOL, KnightExtended.class);
        pieceMap.put(ROOK_PIECE_SYMBOL, RookExtended.class);
        pieceMap.put(PAWN_PIECE_SYMBOL, PawnExtended.class);
    }

    /**
     * Get IPiece class by symbol key.
     *
     * @param pieceName IPiece implementation.
     * @return Extended IPiece class
     */
    public Class<? extends Figure> getPieceClass(final Character pieceName) {
        return pieceMap.get(pieceName);
    }
}
