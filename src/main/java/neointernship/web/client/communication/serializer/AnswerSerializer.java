package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.turn.ITurn;
import neointernship.web.client.communication.data.turn.TurnDto;

public class AnswerSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private AnswerSerializer() {
    }

    public static String serialize(final ITurn turn) throws JsonProcessingException {
        return objectMapper.writeValueAsString(turn);
    }

    public static TurnDto deserialize(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, TurnDto.class);
    }
}
