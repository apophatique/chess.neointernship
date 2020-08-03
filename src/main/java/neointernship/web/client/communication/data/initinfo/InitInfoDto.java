package neointernship.web.client.communication.data.initinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.communication.data.DataErrorCode;
import neointernship.web.client.communication.data.DataException;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("InitInfoDto")
public class InitInfoDto implements IInitInfo {
    @JsonProperty
    private String name;
    @JsonProperty
    private Color color;

    public boolean validate() throws Exception {
        if (name == null) {
            throw new DataException(DataErrorCode.NOT_NAME);
        }
        if (color == null) {
            throw new DataException(DataErrorCode.NOT_COLOR);
        }
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
