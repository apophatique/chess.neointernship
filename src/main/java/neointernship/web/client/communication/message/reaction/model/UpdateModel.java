package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.update.UpdateDto;
import neointernship.web.client.communication.serializer.UpdateSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class UpdateModel implements IMessageCodeModel {
    @Override
    public void execute(final APlayer player,
                        final BufferedReader in,
                        final BufferedWriter out) throws Exception {
        final String updateString = in.readLine();
        final UpdateDto updateDto = UpdateSerializer.deserialize(updateString);
        updateDto.validate();
        player.updateMediator(updateDto.getAnswer(), updateDto.getTurnStatus());
    }
}
