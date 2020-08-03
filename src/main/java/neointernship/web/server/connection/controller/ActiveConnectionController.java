package neointernship.web.server.connection.controller;

import neointernship.chess.game.gameplay.activecolorcontroller.IColorControllerSubscriber;
import neointernship.web.server.connection.userconnection.UserConnection;

import java.util.ArrayList;

public class ActiveConnectionController {
    private final UserConnection firstUserConnection;
    private final UserConnection secondUserConnection;

    private final ArrayList<UserConnection> userList;


    private final IColorControllerSubscriber activeColorController;
    private UserConnection currentConnection;

    public ActiveConnectionController(final UserConnection firstUserConnection,
                                      final UserConnection secondUserConnection,
                                      final IColorControllerSubscriber activeColorController) {
        this.firstUserConnection = firstUserConnection;
        this.secondUserConnection = secondUserConnection;

        userList = new ArrayList<UserConnection>() {{
            add(firstUserConnection);
            add(secondUserConnection);
        }};

        this.activeColorController = activeColorController;
    }

    public void update() {
        currentConnection = (currentConnection != firstUserConnection) ? firstUserConnection : secondUserConnection;
        activeColorController.update();
    }

    public UserConnection getCurrentConnection() {
        return currentConnection;
    }

    public ArrayList<UserConnection> getConnections() {
        return userList;
    }
}
