package neointernship.web.server;

import neointernship.chess.game.model.enums.ChessType;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.logger.ErrorLoggerServer;
import neointernship.web.client.communication.data.initinfo.InitInfoDto;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.MessageDto;
import neointernship.web.client.communication.serializer.InfoSerializer;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.server.connection.userconnection.UserConnection;
import neointernship.web.server.lobby.Lobby;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static neointernship.chess.game.model.enums.Color.BLACK;
import static neointernship.chess.game.model.enums.Color.WHITE;
import static neointernship.web.server.send.SendMessage.send;

/**
 * Консольный многопользовательский чат.
 * Сервер
 */
public class Server implements IServer {
    static final int PORT = 8081;
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final int COUNT_EVENTS_IN_HISTORY = 20;

    private final Queue<UserConnection> blackSideWaitingConnectionList;
    private final Queue<UserConnection> whiteSideWaitingConnectionList;
    private UserConnection universalColorConnection;

    private final Queue<Socket> socketList;
    private final Queue<Lobby> lobbyList;
    private int lobbyNum = 0;

    public Server() throws IOException {
        blackSideWaitingConnectionList = new ConcurrentLinkedQueue<>();
        whiteSideWaitingConnectionList = new ConcurrentLinkedQueue<>();

        socketList = new ConcurrentLinkedQueue<>();
        lobbyList = new ConcurrentLinkedQueue<>();
    }

    public void deleteLobby(final Lobby lobby) {
        lobbyList.remove(lobby);
    }

    private ClientCodes checkPlayers(final UserConnection userConnection) throws Exception {
        final Message messageHandShake = new Message(ClientCodes.HAND_SHAKE);
        final String msg = MessageSerializer.serialize(messageHandShake);

        final String answer;
        final MessageDto message;

        send(userConnection.getOut(), msg);

        answer = userConnection.getIn().readLine();
        message = MessageSerializer.deserialize(answer);
        message.validate();

        return message.getClientCodes();
    }

    private void createNewLobby() throws Exception {
        if (whiteSideWaitingConnectionList.size() > 0 && blackSideWaitingConnectionList.size() > 0) {
            UserConnection whiteSideConnection;
            UserConnection blackSideConnection;
            ClientCodes clientCodes;

            do {
                whiteSideConnection = whiteSideWaitingConnectionList.poll();
                clientCodes = checkPlayers(whiteSideConnection);
            } while (whiteSideWaitingConnectionList.size() > 0 && clientCodes != ClientCodes.YES);
            if (clientCodes == ClientCodes.NO) return;

            do {
                blackSideConnection = blackSideWaitingConnectionList.poll();
                clientCodes = checkPlayers(blackSideConnection);
            } while (blackSideWaitingConnectionList.size() > 0 && clientCodes != ClientCodes.YES);

            if (clientCodes == ClientCodes.NO) {
                whiteSideWaitingConnectionList.add(whiteSideConnection);
                return;
            }

            final Lobby lobby = new Lobby(whiteSideConnection, blackSideConnection, lobbyNum++, this, ChessType.CLASSIC);
            lobbyList.add(lobby);
            lobby.start();
        }
    }

    private void createNewConnection() throws IOException {
        final Socket socket = socketList.poll();

        assert socket != null;
        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        final String name;
        final Color color;

        /*
        TODO с Андреем: принятие цвета,
         приведение цвета к Color
         */
        try {
            final IMessage message = new Message(ClientCodes.INIT_INFO);
            send(out, MessageSerializer.serialize(message));

            final String msg = in.readLine();
            final InitInfoDto info = InfoSerializer.deserialize(msg);
            info.validate();

            color = info.getColor();
            name = info.getName();

            final UserConnection connection = new UserConnection(in, out, socket, color, name);

            setColorInQueue(connection); // добавляет в нужную очередь

        } catch (final Exception e) {
            ErrorLoggerServer.logException(e);
        }
    }

    public void setColorInQueue(final UserConnection connection) {
        switch (connection.getColor()) {
            case WHITE:
                whiteSideWaitingConnectionList.add(connection);
                if (universalColorConnection != null) {
                    universalColorConnection.setColor(BLACK);
                    blackSideWaitingConnectionList.add(universalColorConnection);
                    universalColorConnection = null;
                }
                break;
            case BLACK:
                blackSideWaitingConnectionList.add(connection);
                if (universalColorConnection != null) {
                    universalColorConnection.setColor(WHITE);
                    whiteSideWaitingConnectionList.add(universalColorConnection);
                    universalColorConnection = null;
                }
                break;
            case BOTH:
                final boolean whiteIsEmpty = whiteSideWaitingConnectionList.isEmpty();
                final boolean blackIsEmpty = blackSideWaitingConnectionList.isEmpty();
                if (whiteIsEmpty && blackIsEmpty) {
                    if (universalColorConnection == null) {
                        universalColorConnection = connection;
                    } else {
                        universalColorConnection.setColor(WHITE);
                        whiteSideWaitingConnectionList.add(universalColorConnection);
                        universalColorConnection = null;
                        connection.setColor(BLACK);
                        blackSideWaitingConnectionList.add(connection);
                    }

                } else {
                    if (whiteIsEmpty) {
                        connection.setColor(WHITE);
                        whiteSideWaitingConnectionList.add(connection);
                    }
                    if (blackIsEmpty) {
                        connection.setColor(BLACK);
                        blackSideWaitingConnectionList.add(connection);
                    }
                }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void startServer() {
        System.out.println(String.format("Server started, port: %d", PORT));

        while (true) {
            try (final ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (true) {
                    final Socket socket = serverSocket.accept();
                    socketList.add(socket);

                    createNewConnection();
                    createNewLobby();
                }
            } catch (final BindException e) {
                ErrorLoggerServer.logException(e);
            } catch (final Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void main(final String[] args) throws IOException {
        final Server server = new Server();
        server.startServer();
    }
}
