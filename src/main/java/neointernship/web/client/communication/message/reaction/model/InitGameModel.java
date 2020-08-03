package neointernship.web.client.communication.message.reaction.model;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.web.client.communication.data.initgame.InitGameDto;
import neointernship.web.client.communication.serializer.InitGameSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class InitGameModel implements IMessageCodeModel {

    @Override
    public void execute(final APlayer player,
                        final BufferedReader in,
                        final BufferedWriter out) throws Exception {
        final String initGameString = in.readLine();
        final InitGameDto initGameDto = InitGameSerializer.deserialize(initGameString);
        initGameDto.validate();
        final IMediator mediator = initGameDto.getMediator();
        final IBoard board = initGameDto.getBoard();
        final Color color = initGameDto.getColor();
        player.init(mediator, board, color);
    }
}
