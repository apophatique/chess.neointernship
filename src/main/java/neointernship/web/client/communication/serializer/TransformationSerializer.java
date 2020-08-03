package neointernship.web.client.communication.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.web.client.communication.data.transformation.ITransformation;
import neointernship.web.client.communication.data.transformation.TransformationDto;

public class TransformationSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private TransformationSerializer(){}

    public static String serialize(final ITransformation transformation) throws JsonProcessingException {
        return objectMapper.writeValueAsString(transformation);
    }

    public static TransformationDto deserialize(final String string) throws JsonProcessingException {
        return objectMapper.readValue(string, TransformationDto.class);
    }
}
