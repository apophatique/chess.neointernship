package neointernship.web.client.communication.message.reaction.model;

import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.web.client.communication.data.turn.Turn;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.Message;
import neointernship.web.client.communication.serializer.AnswerSerializer;
import neointernship.web.client.communication.serializer.MessageSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TurnModel implements IMessageCodeModel {
    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws IOException, InterruptedException {
        final String turnString = player.getAnswer();
        final Message message;
        if (turnString.equals("gg")) {
            message = new Message(ClientCodes.END_GAME);
            out.write(MessageSerializer.serialize(message) + "\n");
            out.flush();
        } else {
            final String[] strArr = turnString.split("-");
            final IAnswer answer = new AnswerSimbol(strArr[0].charAt(0), strArr[0].charAt(1),
                    strArr[1].charAt(0), strArr[1].charAt(1));
            final Turn turn = new Turn(answer);

            message = new Message(ClientCodes.TURN);
            out.write(MessageSerializer.serialize(message) + "\n");
            out.flush();

            out.write(AnswerSerializer.serialize(turn) + "\n");
            out.flush();
        }
    }
}
