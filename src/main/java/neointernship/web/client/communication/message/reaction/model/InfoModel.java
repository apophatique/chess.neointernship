package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.initinfo.IInitInfo;
import neointernship.web.client.communication.data.initinfo.InitInfo;
import neointernship.web.client.communication.serializer.InfoSerializer;
import neointernship.web.client.player.APlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class InfoModel implements IMessageCodeModel {
    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws Exception {
        final IInitInfo initInfo = new InitInfo(player.getName(), player.getColor());
        out.write(InfoSerializer.serialize(initInfo) + "\n");
        out.flush();
    }
}
