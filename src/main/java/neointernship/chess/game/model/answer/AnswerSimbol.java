package neointernship.chess.game.model.answer;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class AnswerSimbol implements IAnswer {
    @JsonProperty
    final int startX;
    @JsonProperty
    final int startY;
    @JsonProperty
    final int finalX;
    @JsonProperty
    final int finalY;
    @JsonProperty
    char simbol;

    private static final RepositiryChar repositiryChar = new RepositiryChar();

    public AnswerSimbol(final int startX, final int startY, final int finalX, final int finalY, final char simbol) {
        this.startX = startX;
        this.startY = startY;
        this.finalX = finalX;
        this.finalY = finalY;
        this.simbol = simbol;
    }

    @JsonCreator
    public AnswerSimbol(final String string) {
        final String[] params = string.split("/");
        final String[] coord = params[0].split(":");
        this.startX = Integer.parseInt(coord[0]);
        this.startY = Integer.parseInt(coord[1]);

        this.finalX = Integer.parseInt(coord[2]);
        this.finalY = Integer.parseInt(coord[3]);

        this.simbol = params[1].charAt(0);
    }

    public AnswerSimbol(final char startC, final char startI, final char finishC, final char finishI) {
        this.startX = repositiryChar.getX(startI);
        this.startY = repositiryChar.getY(startC);

        this.finalX = repositiryChar.getX(finishI);
        this.finalY = repositiryChar.getY(finishC);
    }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    @Override
    public int getFinalX() {
        return finalX;
    }

    @Override
    public int getFinalY() {
        return finalY;
    }

    @Override
    public char getSimbol() {
        return simbol;
    }

    @Override
    @JsonValue
    public String toString() {
        return startX + ":" + startY + ":" + finalX + ":" + finalY + "/" + simbol;
    }
}
