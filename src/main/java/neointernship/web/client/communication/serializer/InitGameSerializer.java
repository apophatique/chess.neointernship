package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.initgame.IInitGame;
import neointernship.web.client.communication.data.initgame.InitGameDto;

public class InitGameSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private InitGameSerializer(){}

    public static String serialize(final IInitGame initGame) throws JsonProcessingException {
        return objectMapper.writeValueAsString(initGame);
    }

    public static InitGameDto deserialize(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, InitGameDto.class);
    }
}
