package neointernship.web.client.communication.data.update;

import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.data.DataErrorCode;
import neointernship.web.client.communication.data.DataException;
import neointernship.web.client.communication.message.TurnStatus;

public class UpdateDto implements IUpdate {
    private IAnswer answer;
    private TurnStatus turnStatus;

    public boolean validate() throws Exception {
        if (answer == null) {
            throw new DataException(DataErrorCode.NOT_ANSWER);
        }
        if (turnStatus == null) {
            throw new DataException(DataErrorCode.NOT_CHESS_CODE);
        }
        return true;
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
