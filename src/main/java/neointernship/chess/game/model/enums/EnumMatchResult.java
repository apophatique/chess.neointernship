package neointernship.chess.game.model.enums;

public enum EnumMatchResult {
    WIN,
    DRAW,
    LOSE;

    public static EnumMatchResult getEnumMatchResult(final EnumGameState gameState) {
        switch (gameState) {
            case MATE:
                return WIN;
            case RESIGNATION:
                return LOSE;
            default:
                return DRAW;
        }
    }

    public static EnumMatchResult swapEnumMatchResult(final EnumMatchResult matchResult) throws Exception {
        if (matchResult == WIN) return LOSE;
        if (matchResult == LOSE) return WIN;
        if (matchResult == DRAW) return DRAW;
        throw new Exception();
    }
}
