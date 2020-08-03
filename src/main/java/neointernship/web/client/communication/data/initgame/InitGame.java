package neointernship.web.client.communication.data.initgame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("InitGame")
public class InitGame implements IInitGame{
    @JsonProperty
    private final IMediator mediator;
    @JsonProperty
    private final IBoard board;
    @JsonProperty
    private final Color color;

    public InitGame(@JsonProperty("mediator") final IMediator mediator,
                    @JsonProperty("board") final IBoard board,
                    @JsonProperty("color") final Color color) {
        this.mediator = mediator;
        this.board = board;
        this.color = color;
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
