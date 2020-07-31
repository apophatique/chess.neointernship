package neointernship.web.client.communication.data.endgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.web.client.communication.data.DataErrorCode;
import neointernship.web.client.communication.data.DataException;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("EndGameDto")
public class EndGameDto implements IEndGame {
    @JsonProperty
    private EnumGameState gameState;

    @JsonProperty
    private Color color;

    public boolean validate() throws Exception {
        if (gameState == null){
            throw new DataException(DataErrorCode.NOT_END_GAME);
        }
        if (color == null){
            throw new DataException(DataErrorCode.NOT_COLOR);
        }
        return true;
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
