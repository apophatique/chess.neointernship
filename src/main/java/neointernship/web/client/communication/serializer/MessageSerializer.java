package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.message.IMessage;
import neointernship.web.client.communication.message.MessageDto;
;

public final class MessageSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private MessageSerializer(){}

    public static String serialize(final IMessage message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(message);
    }

    public static MessageDto deserialize(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, MessageDto.class);
    }
}
