package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.initinfo.IInitInfo;
import neointernship.web.client.communication.data.initinfo.InitInfoDto;

public class InfoSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private InfoSerializer() {
    }

    public static String serialize(final IInitInfo name) throws JsonProcessingException {
        return objectMapper.writeValueAsString(name);
    }

    public static InitInfoDto deserialize(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, InitInfoDto.class);
    }
}
