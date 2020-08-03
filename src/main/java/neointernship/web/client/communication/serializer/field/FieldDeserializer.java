package neointernship.web.client.communication.serializer.field;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import neointernship.chess.game.model.playmap.field.IField;

import java.io.IOException;

public class FieldDeserializer extends KeyDeserializer {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public IField deserializeKey(final String s, final DeserializationContext deserializationContext) throws IOException {
        return mapper.readValue(s, IField.class);
    }
}
