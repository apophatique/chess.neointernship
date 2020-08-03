package neointernship.web.client.communication.data.turn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.answer.IAnswer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("Turn")
public class Turn implements ITurn {
    @JsonProperty
    private final IAnswer answer;

    public Turn(@JsonProperty("answer") final IAnswer answer) {
        this.answer = answer;
    }

    @Override
    public IAnswer getAnswer() {
        return answer;
    }
}
