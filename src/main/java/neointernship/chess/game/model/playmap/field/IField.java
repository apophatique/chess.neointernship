package neointernship.chess.game.model.playmap.field;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import neointernship.chess.game.model.enums.Color;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Field.class, name = "Field"),
})
public interface IField {
    int getXCoord();

    int getYCoord();

    Color getColor();

    String showField();
}