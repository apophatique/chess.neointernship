package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.endgame.EndGameDto;
import neointernship.web.client.communication.data.endgame.IEndGame;

public class EndGameSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private EndGameSerializer() {
    }

    public static String serialize(final IEndGame EndGame) throws JsonProcessingException {
        return objectMapper.writeValueAsString(EndGame);
    }

    public static EndGameDto deserialize(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, EndGameDto.class);
    }
}
