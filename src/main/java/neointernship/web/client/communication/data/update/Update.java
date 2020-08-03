package neointernship.web.client.communication.data.update;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.message.TurnStatus;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Update implements IUpdate {
    private final IAnswer answer;
    private final TurnStatus turnStatus;

    public Update(@JsonProperty("answer") final IAnswer answer, final TurnStatus turnStatus) {
        this.answer = answer;
        this.turnStatus = turnStatus;
    }

    @Override
    public IAnswer getAnswer() {
        return answer;
    }

    @Override
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }
}
