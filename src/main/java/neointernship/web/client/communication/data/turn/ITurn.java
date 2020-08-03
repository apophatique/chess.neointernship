package neointernship.web.client.communication.data.turn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import neointernship.chess.game.model.answer.IAnswer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Turn.class, name = "Turn"),
        @JsonSubTypes.Type(value = TurnDto.class, name = "TurnDto"),
})
public interface ITurn {
    IAnswer getAnswer();
}
