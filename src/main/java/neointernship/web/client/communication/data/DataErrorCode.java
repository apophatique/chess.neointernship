package neointernship.web.client.communication.data;

public enum  DataErrorCode {
    NOT_MEDIATOR("Отсутствует медиатор"),
    NOT_BOARD("Отсутствует доска"),
    NOT_COLOR("Отсутствует цвет"),
    NOT_NAME("Отсутствует имя"),
    NOT_ANSWER("Отсутствует ответ"),
    NOT_CHESS_CODE("Отсутствует код шахмат"),
    NOT_END_GAME("Отсутствует конец игры"),
    NOT_FIGURE_CHAR("Отсутствует символ фигуры");

    private final String errorString;

    DataErrorCode(final String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
