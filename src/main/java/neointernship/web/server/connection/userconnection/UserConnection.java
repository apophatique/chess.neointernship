package neointernship.web.server.connection.userconnection;

import neointernship.chess.game.model.enums.Color;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class UserConnection {
    private final BufferedReader in;
    private final BufferedWriter out;
    private final Socket socket;

    private Color color;
    private final String name;

    public UserConnection(final BufferedReader in,
                          final BufferedWriter out,
                          final Socket socket,
                          final Color color,
                          final String name) {
        this.in = in;
        this.out = out;
        this.socket = socket;
        this.color = color;
        this.name = name;
    }

    public BufferedReader getIn() {
        return in;
    }

    public BufferedWriter getOut() {
        return out;
    }

    public Socket getSocket() {
        return socket;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(final Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }
}
