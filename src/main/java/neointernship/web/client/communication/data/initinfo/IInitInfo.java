package neointernship.web.client.communication.data.initinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import neointernship.chess.game.model.enums.Color;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = InitInfo.class, name = "Name"),
        @JsonSubTypes.Type(value = InitInfoDto.class, name = "NameDto"),
})
public interface IInitInfo {
    String getName();
    Color getColor();
}
