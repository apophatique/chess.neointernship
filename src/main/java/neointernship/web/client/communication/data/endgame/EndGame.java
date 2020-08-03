package neointernship.web.client.communication.data.endgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("EndGame")
public class EndGame implements IEndGame {
    @JsonProperty
    private final EnumGameState gameState;

    @JsonProperty
    private final Color color;

    public EndGame(@JsonProperty("gameState") final EnumGameState gameState,
                   @JsonProperty("color") final Color color) {
        this.gameState = gameState;
        this.color = color;
    }

    @Override
    public EnumGameState getEnumGameState() {
        return gameState;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
