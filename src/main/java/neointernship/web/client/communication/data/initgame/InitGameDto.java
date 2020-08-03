package neointernship.web.client.communication.data.initgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.web.client.communication.data.DataErrorCode;
import neointernship.web.client.communication.data.DataException;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("InitGameDto")
public class InitGameDto implements IInitGame {
    @JsonProperty
    private IMediator mediator;
    @JsonProperty
    private IBoard board;
    @JsonProperty
    private Color color;

    public boolean validate() throws Exception {
        if (mediator == null) {
            throw new DataException(DataErrorCode.NOT_MEDIATOR);
        }
        if (board == null) {
            throw new DataException(DataErrorCode.NOT_BOARD);
        }
        if (color == null) {
            throw new DataException(DataErrorCode.NOT_COLOR);
        }
        return true;
    }

    @Override
    public IMediator getMediator() {
        return mediator;
    }

    @Override
    public IBoard getBoard() {
        return board;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
