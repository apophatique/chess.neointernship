package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.update.IUpdate;
import neointernship.web.client.communication.data.update.UpdateDto;

public class UpdateSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UpdateSerializer() {
    }

    public static String serialize(final IUpdate update) throws JsonProcessingException {
        return objectMapper.writeValueAsString(update);
    }

    public static UpdateDto deserialize(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, UpdateDto.class);
    }
}
