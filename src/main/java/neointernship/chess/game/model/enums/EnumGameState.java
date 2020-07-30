package neointernship.chess.game.model.enums;

public enum EnumGameState {
    MATE("Мат"),
    STALEMATE("Пат"),
    ALIVE("Игра продолжается"),
    DRAW_FIFTY_STEP("Ничья по правилу 50 ходов"),
    DRAW_ONLY_KING("Ничья: на доске осталось 2 короля"),
    DRAW_FEW_FIGURE("Ничья: мало фигур для мата"),
    DRAW_REPETITION_POSITION("Ничья: трехкратное повторение позиции"),
    RESIGNATION("Игрок сдался");

    private String message;

    EnumGameState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
