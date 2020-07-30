package neointernship.web.client.communication.message.reaction.model;

import neointernship.web.client.communication.data.transformation.Transformation;
import neointernship.web.client.communication.serializer.TransformationSerializer;
import neointernship.web.client.player.APlayer;
import neointernship.web.client.player.Bot;
import neointernship.web.client.player.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TransformationModel implements IMessageCodeModel {

    @Override
    public void execute(final APlayer player, final BufferedReader in, final BufferedWriter out) throws Exception {
        Transformation transformation = null;

        if (player.getClass() == Bot.class){
            transformation = new Transformation('Q');
        }

        if (player.getClass() == Player.class) {
            final Scanner scanner = new Scanner(System.in);

            final List<Character> figureList = Arrays.asList('Q', 'N', 'B', 'R');
            char figure;

            do {
                System.out.println("В какую фигуру обратить пешку:\n1.Q - ферзь\n2.B - слон\n3.N - конь\n4.R - ладья");
                final String string = scanner.nextLine().trim().toUpperCase();
                figure = string.charAt(0);
            } while (!figureList.contains(figure));

            transformation = new Transformation(figure);
        }

        out.write(TransformationSerializer.serialize(transformation) + "\n");
        out.flush();
    }
}
