package neointernship.web.client.communication.data.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.message.TurnStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Update.class, name = "Update"),
        @JsonSubTypes.Type(value = UpdateDto.class, name = "UpdateDto"),
})
public interface IUpdate {
    IAnswer getAnswer();
    TurnStatus getTurnStatus();
}
