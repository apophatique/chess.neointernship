package neointernship.web.client.player;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.web.client.communication.message.TurnStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player extends APlayer {
    private ConsoleBoardWriter consoleBoardWriter;

    public Player(final Color color, final String name) {
        super(color, name);
    }

    @Override
    public void init(final IMediator mediator, final IBoard board, final Color color) {
        super.init(mediator, board, color);

        this.consoleBoardWriter = new ConsoleBoardWriter(mediator, board);
        consoleBoardWriter.printBoard();
    }

    @Override
    public String getAnswer() {
        final Scanner scanner = new Scanner(System.in);
        String[] strArr;
        String input;

        boolean flag = false;

        final List<Character> integers = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8');
        final List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');

        do {
            System.out.print("Ваш ход: ");
            input = scanner.nextLine();

            if (input.trim().toLowerCase().equals("gg")) {
                return input;
            }

            try {
                if (input.length() != 5) throw new Exception();
                strArr = input.split("-");
                if (!chars.contains(strArr[0].charAt(0)) || !chars.contains(strArr[1].charAt(0))) throw new Exception();
                if (!integers.contains(strArr[0].charAt(1)) || !integers.contains(strArr[1].charAt(1))) throw new Exception();
                flag = true;
            } catch (final Exception ignore) { }
        } while (!flag);

        return input;
    }

    @Override
    public void updateMediator(final IAnswer answer, final TurnStatus turnStatus) {
        super.updateMediator(answer, turnStatus);

        consoleBoardWriter.printBoard();
    }
}
