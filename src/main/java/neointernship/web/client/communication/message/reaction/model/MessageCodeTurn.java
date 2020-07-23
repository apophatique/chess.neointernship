package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.turn.ITurn;
import neointernship.web.client.communication.exchanger.ExchangerForMessage;
import neointernship.web.client.communication.exchanger.ExchangerForTurn;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.message.MessageCode;
import neointernship.web.client.communication.serializer.SerializerForMessage;
import neointernship.web.client.communication.serializer.SerializerForTurn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MessageCodeTurn implements IMessageCode {
    @Override
    public void execute(final IMessage message, final BufferedReader in, final BufferedWriter out) throws InterruptedException, IOException {
        ExchangerForMessage.exchange(message);
        final IMessage mes = new Message(MessageCode.TURN);

        out.write(SerializerForMessage.serializer(mes) + "\n");
        out.flush();

        final ITurn turn = ExchangerForTurn.exchange(null);
        out.write(SerializerForTurn.serializer(turn));
        out.flush();
    }
}
