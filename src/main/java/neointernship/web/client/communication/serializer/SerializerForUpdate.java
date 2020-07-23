package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.update.IUpdate;
import neointernship.web.client.communication.data.update.UpdateDto;

public class SerializerForUpdate {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private SerializerForUpdate(){}

    public static String serializer(final IUpdate update) throws JsonProcessingException {
        return objectMapper.writeValueAsString(update);
    }

    public static UpdateDto deserializer(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, UpdateDto.class);
    }
}
