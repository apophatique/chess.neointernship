package neointernship.web.client.communication.message;


import neointernship.web.client.communication.message.reaction.model.*;

import java.net.Socket;
import java.util.HashMap;

public class ModelMessageReaction {
    private final HashMap<ClientCodes, IMessageCodeModel> messageReaction;

    public ModelMessageReaction(final Socket socket) {
        this.messageReaction = new HashMap<>();
        initMessageReaction(messageReaction, socket);
    }

    private void initMessageReaction(final HashMap<ClientCodes, IMessageCodeModel> messageReaction, final Socket socket) {
        messageReaction.put(ClientCodes.TURN, new TurnModel());
        messageReaction.put(ClientCodes.UPDATE, new UpdateModel());
        messageReaction.put(ClientCodes.INIT_INFO, new InfoModel());
        messageReaction.put(ClientCodes.INIT_GAME, new InitGameModel());
        messageReaction.put(ClientCodes.HAND_SHAKE, new HandShakeModel());
        messageReaction.put(ClientCodes.END_GAME, new EndGameModel(socket));
        messageReaction.put(ClientCodes.TRANSFORMATION, new TransformationModel());
    }

    public IMessageCodeModel get(final ClientCodes clientCodes) {
        return messageReaction.get(clientCodes);
    }
}
