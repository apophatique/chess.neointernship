package neointernship.web.client.player.bot.ai.positionanalyze.PST;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.HashMap;

public class PSTable {
    private static final int[][] PAWN_TABLE = new int[][] {
            {50,  50, 50,  50, 50, 50, 50, 50},
            {40,  40, 40,  40, 40, 40, 40, 40},
            {10,  10, 20,  20, 20, 20, 10, 10},
            {5,   5,  10,  11, 11, 10,  5,  5},
            {5,   3,  10 ,  11, 11 , 10,  3,  5},
            {5,   6,  5 ,   5,  5 , 5,  6,  5},
            {5,   10, 10, -20, -20, 10, 10, 5},
            {0,   0,  0,   0,   0,  0,  0,  0}
    };
    private static final int[][] KNIGHT_TABLE = new int[][] {
            {-50, -40,-30, -30, -30, -30, -40, -50},
            {-40, -20,  5,  0,   0,   5,  -20, -40},
            {-30,  10, 10 , 15,  15,  10,  10, -30},
            {-30,  5,  15 , 20 , 20 , 15 ,  5, -30},
            {-30,  0 , 5 ,  20 , 20 ,  5  , 0, -30},
            {-30,  5 , 20 , 15 , 15 , 20  , 5 , -30},
            {-40, -20 , 0 ,  8 ,  8 ,  0, -20 , -40},
            {-50, -40 ,-30, -30, -30,-30, -40, -50}
    };
    private static final int[][] BISHOP_TABLE = new int[][] {
            {-20, -10, -10,-10, -10, -10, -10, -20},
            {-10,  0 ,  0 ,  0 , 0 , 0 , 0 ,-10},
            {-10, 0 , 5 , 10 , 10 , 5 , 0 ,-10},
            {-10, 20 , 5 , 10 , 10 , 5 , 5 ,-10},
            {-10, 10, 15 ,10 , 10 , 10 , 0 ,-10},
            {-10, 10, 15 ,10 , 10 , 10 , 10 ,-10},
            {-10, 5,  0 , 0 , 5 , 0 , 5, -10},
            {-20,-10,-10,-10,-10,-10,-10,-20}
    };
    private static final int[][] ROOK_TABLE = new int[][] {
            {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0} ,
            {5 , 10 , 10 , 10 , 10 , 10 , 10 , 5} ,
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {  0 , 0 , 0 , 5 , 5 , 0 , 0 , 0}
    };
    private static final int[][] QUEEN_TABLE = new int[][] {
            {-20, -10, -10, -5, -5, -10,-10, -20},
            {-10,  0 ,  0 ,  0 , 0 , 0 , 0 , -10},
            {-10,  0 ,  5 ,  5 , 5 , 5 , 0 , -10},
            { -5,  0 ,  5 ,  5 , 5 , 5 , 0 , -5},
            {  0 , 0 ,  5 ,  5 , 5 , 5 , 0 , -5},
            {-10 ,  5 ,  5 , 5 , 5 , 5 , 0 ,-10},
            {-10 ,  0 ,  5 , 0 , 0 , 0 , 0 ,-10},
            {-20, -10, -10, -5 , -5,-10,-10,-20}
    };
    private static final int[][] DEBUT_KING_TABLE = new int[][] {
            { -30,-40,-40,-50,-50,-40,-40,-30},
            { -30,-40,-40,-50,-50,-40,-40,-30},
            { -30,-40,-40,-50,-50,-40,-40,-30},
            { -30,-40,-40,-50,-50,-40,-40,-30},
            { -20,-30,-30,-40,-40,-30,-30,-20},
            { -10,-20,-20,-20,-20,-20,-20,-10},
            {  0 , 0 , 0 , -20 , -20 , 0 , 0 , 0 },
            {  0 , 15, 40, 0 , 0 , 10, 30, 10}
    };
    private static final int[][]  ENDGAME_KING_TABLE = new int[][] {
            {-50, -40, -30, -20, -20, -30, -40, -50},
            {-30, -20, -10 ,  0 ,  0, -10, -20, -30},
            {-30, -10,  20 , 30 , 30 , 20 ,-10 ,-30} ,
            {-30, -10,  30 , 40 , 40 , 30 ,-10 ,-30} ,
            {-30, -10,  30 , 40 , 40 , 30 ,-10 ,-30} ,
            {-30, -10,  20 , 30 , 30 , 20 ,-10 ,-30} ,
            {-30, -30,  0 ,  0 ,  0 ,  0 , -30 ,-30} ,
            {-50, -30, -30, -30, -30, -30, -30, -50}
    };
    private static final HashMap<Character, int[][]> costMap = new HashMap<Character, int[][]>() {
        {
            put('P', PAWN_TABLE);
            put('B', BISHOP_TABLE);
            put('N', KNIGHT_TABLE);
            put('R', ROOK_TABLE);
            put('Q', QUEEN_TABLE);
        }
    };

    public static double getMoveCost(final Figure figure,
                                     final IField field,
                                     final double phase) {
        int xCoord = field.getXCoord();
        int yCoord = field.getYCoord();

        if (figure.getColor() == Color.BLACK) {
            xCoord = 7 - xCoord;
        }

        if (figure.getGameSymbol() == 'K') {
            return phase * DEBUT_KING_TABLE[xCoord][yCoord]
                    + (1 - phase) * ENDGAME_KING_TABLE[xCoord][yCoord];
        }

        return costMap.get(figure.getGameSymbol())[xCoord][yCoord];
    }
}
