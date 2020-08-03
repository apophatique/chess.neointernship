package neointernship.web.client.communication.serializer.field;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import neointernship.chess.game.model.playmap.field.IField;

import java.io.IOException;
import java.io.StringWriter;

public class FieldSerializer extends JsonSerializer<IField> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(final IField value, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        final StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        jsonGenerator.writeFieldName(writer.toString());
    }
}
