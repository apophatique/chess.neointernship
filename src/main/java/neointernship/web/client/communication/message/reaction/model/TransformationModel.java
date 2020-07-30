package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.transformation.Transformation;
import neointernship.web.client.communication.serializer.TransformationSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class TransformationModel implements IMessageCodeModel {

    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws Exception {
        final Transformation transformation = new Transformation(player.getTransformation());

        out.write(TransformationSerializer.serialize(transformation) + "\n");
        out.flush();
    }
}
