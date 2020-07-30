package neointernship.web.client.controller;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.logger.ErrorLoggerClient;
import neointernship.web.client.GUI.Input.Input;
import neointernship.web.client.GUI.Viewer;
import neointernship.web.client.GUI.board.view.BoardView;
import neointernship.web.client.communication.data.BoardInfoContainer;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.MessageDto;
import neointernship.web.client.communication.message.ModelMessageReaction;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.APlayer;
import neointernship.web.client.player.Bot;
import neointernship.web.client.player.Player;
import neointernship.web.client.player.PlayerType;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {
    private Input input;
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Socket socket;
    private Connection connection;
    private ModelMessageReaction modelMessageReaction;
    private APlayer player;
    private boolean endGame = false;

    public void start() throws InterruptedException {
        input = new Input();
        modelMessageReaction = new ModelMessageReaction(socket);

        startConnection();
        initPlayer();

        while (!endGame) {
            try {
                final String jsonMessage = in.readLine();
                final MessageDto messageDto = MessageSerializer.deserialize(jsonMessage);
                messageDto.validate();
                modelMessageReaction.get(messageDto.getClientCodes()).execute(player, in, out);

                if (messageDto.getClientCodes() == ClientCodes.END_GAME) {
                    endGame = true;
                }
            } catch (final Exception e) {
                ErrorLoggerClient.getLogger(player.getName()).logException(e);
            }
        }
    }

    private void initPlayer() throws InterruptedException {
        final HashMap<PlayerType, APlayer> players = new HashMap<>();


        final PlayerType playerType = input.getPlayerType();
        final String name = input.getUserName().trim();
        final Color color = input.getColor();

        players.put(PlayerType.HUMAN, new Player(color, name, input));
        players.put(PlayerType.BOT, new Bot(color, name));

        player = players.get(playerType);
        ErrorLoggerClient.addLogger(name);
    }

    private void startConnection() {
        connection = new Connection();
        connection.connection();

        socket = connection.getSocket();

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (final IOException e) {
            ErrorLoggerClient.getLogger(player.getName()).logException(e);
        }
    }
}
