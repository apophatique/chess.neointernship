package neointernship.web.client.communication.data.initinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.Color;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("InitInfo")
public class InitInfo implements IInitInfo {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final Color color;

    public InitInfo(final String name, final Color color) {
        this.name = name;
        this.color = color;
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
