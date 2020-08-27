package neointernship.web.client.player.bot.ai.positionanalyze.PST;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.model.util.Pair;

import java.util.HashMap;

public class PSTable {
    private static final int[][] PAWN_MATRIX_DEBUT = new int[][] {
            {25,  25,  25,  25,  25, 25, 25, 25},
            {15,  15,  15,  15,  15, 15, 15, 15},
            {10,  10,  10,  20,  20, 10, 10, 10},
            {5,    5,  10,  15,  15, 15,  5,  7},
            {5,    5,   5,  12,  15,  5,  5,  7},
            {7,    7,  10,  10,  10,  5,  7,  8},
            {-5, -10, -10, -50, -50, -10, -10, -5},
            {0,    0,   0,   0,   0,  0,  0,  0}
    };
    private static final int[][] PAWN_MATRIX_ENDGAME = new int[][] {
            {100,  100,  100,  100,  100,  100, 100, 100},
            {85,    85,   85,   85,   85,   85,  85,  85},
            {70,    70,   70,   70,   70,   70,  70,  70},
            {40,    40,   40,   40,   40,   40,  40,  40},
            {0,     0,    0,   0,   0,    0,   0,   0},
            {-10,     -10,    -10,   -10,   -10,    -10,   -10,   -10},
            {-20,   -20,  -20,  -50,  -50,  -20,  0, 0},
            {0,      0,    0,    0,    0,    0,    0,  0}
    };
    private static final int[][] KNIGHT_MATRIX_DEBUT = new int[][] {
            {-50, -40,-30, -30, -30, -30, -40, -50},
            {-40, -20,  15,  0,   0,   15,  -20, -40},
            {-30,  10, 10 , 15,  15,  10,  10, -30},
            {-30,  5,  5 , 5 , 5 , 5 ,  5, -30},
            {-30,  0 , 5 ,  5 , 5 ,  5  , 0, -30},
            {-30,  5 , 10 , 5 , 15 , 15  , 5 , -30},
            {-40, -20 , 0 ,  5 ,  8 ,  0, -20 , -40},
            {-50, -20 ,-30, -30, -30,-30, -20, -50}
    };
    private static final int[][] KNIGHT_MATRIX_ENDGAME = new int[][] {
            { -25 ,  -10 ,  -10 ,  -10 ,  -10 ,  -10 ,  -10 ,  -25 },
            { -15 ,  30 ,  50 ,  50 ,  50 ,  50 ,  3 ,  -15 },
            { 10 ,  40 ,  40 ,  40 ,  40 ,  40 ,  40 ,  10 },
            {  -10 ,  30 ,  30 ,  35 ,  35 ,  30 ,  30 , -10 },
            {  -10 ,  20 ,   20,   25 ,  25 ,  20 ,  2 ,  -10 },
            {-50 ,  -20 ,  -5 ,  -5 ,   -5 ,  -5 ,  - 20 , -50 },
            {-50 , -50 , -50 , -50 , -50 , -50 , -50 , -50 },
            {-60 , -60 , -60 , -60 , -60 , -60 ,  -60, -40 }
    };
    private static final int[][] BISHOP_MATRIX_DEBUT = new int[][] {
            {-20, -10, -10, -10, -10, -10,- 10, -20},
            {-10,  0 ,  0 ,   0,   0,  0 ,  0 , -10},
            {-10,  0 ,  5 ,  10,  10,  5 ,  0 , -10},
            {-10,  15,  5 ,   0,   0,  5 , 10 , -10},
            {-10,  10,  15,   0,   0,  10,  0 , -10},
            {-10,  10,  15,  -5,  -5,  10,  10, -10},
            {-10,   5,  0 ,   5,   5,  0 ,   5, -10},
            {-20,- 20,- 10, -10, -10,- 10, -20, -20}
    };
    private static final int[][] BISHOP_MATRIX_ENDGAME = new int[][] {
            {-10 , -10 , -10 , -10 , -10 , -10 , -10, -10},
            {-15 ,  15 ,  15 ,  15 ,  15 ,  15 ,  15, -15},
            {-10 ,  10 ,  10 ,  10 ,  10 ,  10 ,  10, -10},
            {-5 ,   15 ,  15 ,  15 ,  15 ,  15 ,  15,  -5},
            {-5 ,   15 ,  15 ,  15 ,  15 ,  15 ,  15,  -5},
            { 5 ,    5 ,   5 ,  10 ,  10 ,  10 ,  10,   5},
            {-5 ,   -5 ,  -5 ,  -5 ,  -5 , -5  ,  -5,  -5},
            {-50 , -50 , -50 , -50 , -50 , -50 , -50, -50}
    };
    private static final int[][] ROOK_MATRIX_DEBUT = new int[][] {
            {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0} ,
            {10, 0,  0 , 0 , 0 , 0 , 0 , 10} ,
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5 , 0 , 0 , 5 , 5 , 5 , 0, -5}
    };
    private static final int[][] ROOK_MATRIX_ENDGAME = new int[][] {
            {0 , 10 , 10 , 10 , 10 , 10 , 10 , 0} ,
            {5 , 50, 50, 50, 50, 50, 50, 0} ,
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {-5, 0 , 0 , 0 , 0 , 0 , 0 , -5},
            {10 , 10 , 10 , 5 , 5 , 5 , 10, 10}
    };
    private static final int[][] QUEEN_MATRIX_DEBUT = new int[][] {
            {10 ,  10 ,  10 ,  10 ,  10 , 10 , 10 ,  10},
            {-10,  10 ,  0 ,  0 , 0 , 0 , 0 , -10},
            {-10,  0 ,  5 ,  5 , 5 , 5 , 0 , -10},
            { -5,  0 ,  5 ,  5 , 5 , 5 , 5 ,  -5},
            {  0 , 0 ,  5 ,  5 , 5 , 5 , 5 ,  -5},
            {-10 ,  10 ,  10 , 5 , 5 , 5 , 10 , -10},
            {-10 ,  0 ,  5 , 5 , 0 , 0 , 0 , -10},
            {-20, -10, -10, -10 ,-5, -10,-10, -20}
    };
    private static final int[][] QUEEN_MATRIX_ENDGAME = new int[][] {
            { 10 ,  10 ,  10 ,  10 ,  10 , 10 , 10 ,  10 },
            { 15 ,  30 ,  50 ,  50 ,  50 , 50 , 30 ,  15 },
            { 10 ,   0 ,  40 ,  40 ,  40 , 40 ,  0 ,  10 },
            {  5 ,  30 ,  40 ,  40 ,  40 , 40 , 30 ,   5 },
            {  0 ,  25 ,  40 ,  40 ,  40 , 40 , 25 ,  -5 },
            {-10 ,  10 ,  10 ,   5 ,   5 ,  10 , 10 , -10 },
            {-10 ,   0 ,   0 ,   0 ,   0 ,  0 ,  0 , -10 },
            {-40 , -40 , -40 , -40 , -40, -40 , -40, -40 }
    };
    private static final int[][] KING_MATRIX_DEBUT = new int[][] {
            { -30, -40, -40,- 50, -50,  -40 , -40 , -30},
            { -30, -40, -40, -50, -50,  -40 , -40 , -30},
            { -30, -40, -40, -50, -50 , -40 , -40 , -30},
            { -30, -40, -40, -50, -50,  -40 , -40 , -30},
            { -20, -30, -30, -40, -40,  -30 , -30 , -20},
            { -10, -20, -20, -20, -20,  -20 , -20 , -10},
            {  0 ,   0 , 0 , -60, -60,    0 ,   0 ,   0},
            {  0 ,  15,  40,  0 ,   0 , -60 ,  40 ,   0}
    };
    private static final int[][] KING_MATRIX_ENDGAME = new int[][] {
            {-50, 0, -30, -20, -20, -30, -40, -50},
            {-30, 0, -10 ,  0 ,  0, -10, -20, -30},
            {-30, 0,  10 , 10 , 10 ,  10,-10 ,-30} ,
            {-30, 0,  10 , 15 , 15 , 10 ,-10 ,-30} ,
            {-30, 0,  10 , 15 , 15 , 10 ,-10 ,-30} ,
            {-30, 0,  10 , 15 , 15 , 10 ,-10 ,-30} ,
            {-30, 0,  0 ,  0 ,  0 ,  0 , -30 ,-30} ,
            {-50, 0, -30, -30, -30, -30, -30, -50}
    };
    private static final HashMap<Character, Pair<int[][], int[][]>> costMap
            = new HashMap<Character, Pair<int[][], int[][]>>() {
        {
            put('P', new Pair<>(PAWN_MATRIX_DEBUT, PAWN_MATRIX_ENDGAME));
            put('B', new Pair<>(BISHOP_MATRIX_DEBUT, BISHOP_MATRIX_ENDGAME));
            put('N', new Pair<>(KNIGHT_MATRIX_DEBUT, KNIGHT_MATRIX_ENDGAME));
            put('R', new Pair<>(ROOK_MATRIX_DEBUT, ROOK_MATRIX_ENDGAME));
            put('Q', new Pair<>(QUEEN_MATRIX_DEBUT, QUEEN_MATRIX_ENDGAME));
            put('K', new Pair<>(KING_MATRIX_DEBUT, KING_MATRIX_ENDGAME));
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

        final Pair<int[][], int[][]> costPair = costMap.get(figure.getGameSymbol());

        return phase * costPair.getFirst()[xCoord][yCoord]
                + (1 - phase) * costPair.getSecond()[xCoord][yCoord];
    }
}
