package neointernship.chess.game.model.enums;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public enum Color {
    WHITE("белые"),
    BLACK("черные"),
    BOTH("любой");

    private String message;

    Color(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static Color swapColor(Color color){
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    @JsonCreator
    public static Color parseColor(final String color) {
        switch (color) {
            case "BLACK":
                return BLACK;
            case "WHITE":
                return WHITE;
            case "BOTH":
                return BOTH;
        }
        return null;
    }
}
