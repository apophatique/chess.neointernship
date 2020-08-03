package neointernship.web.client.communication.data.endgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EndGame.class, name = "EndGame"),
        @JsonSubTypes.Type(value = EndGameDto.class, name = "EndGameDto"),
})
public interface IEndGame {
    EnumGameState getEnumGameState();

    Color getColor();
}
