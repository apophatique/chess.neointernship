package neointernship.web.client.communication.message.reaction.model;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.web.client.communication.data.endgame.EndGameDto;
import neointernship.web.client.communication.serializer.EndGameSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class EndGameModel implements IMessageCodeModel {
    private final Socket socket;

    public EndGameModel(final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws Exception {
        final String endGameString = in.readLine();

        final EndGameDto endGameDto = EndGameSerializer.deserialize(endGameString);

        endGameDto.validate();

        final Color color = endGameDto.getColor();
        final EnumGameState enumGameState = endGameDto.getEnumGameState();

        player.endGame(enumGameState, color);

        if (in != null) in.close();
        if (out != null) out.close();
        if (socket != null) socket.close();
    }
}
