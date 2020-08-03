package neointernship.web.client.controller;

import java.io.IOException;
import java.net.Socket;

public class Connection {
    private static final String IP = "127.0.0.1";//"localhost";
    private static final int PORT = 8081;

    private String ip;
    private int port;
    private Socket socket;

    public Connection(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
    }

    public Connection() {
        this.ip = IP;
        this.port = PORT;
    }

    public void connection() {
        do {
            try {
                socket = new Socket(ip, port);
               // System.out.println(String.format("Client started, ip: %s, port: %d", ip, port));
            } catch (final IOException e) {
//                System.err.println("Socket failed");
            }
        } while (socket == null);
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }
}
