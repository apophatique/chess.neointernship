package neointernship.chess.client.message.reaction;

import neointernship.chess.client.message.Message;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IMessageCode {
    void execute(final Message message, final BufferedWriter out) throws InterruptedException, IOException;
}
