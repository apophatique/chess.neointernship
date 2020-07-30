package neointernship.web.client.player;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.web.client.GUI.Input.Input;
import neointernship.web.client.GUI.board.view.BoardView;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.TurnStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Player extends APlayer {
    private BoardView boardView;
    final Scanner scanner;
    private final Input input;

    public Player(final Color color, final String name, final Input input) {
        super(color, name);
        scanner = new Scanner(System.in);

        this.input = input;
    }

    @Override
    public void init(final IMediator mediator, final IBoard board, final Color color) {
        super.init(mediator, board, color);

        this.boardView = new BoardView(mediator, board);
    }

    @Override
    public String getAnswer() throws InterruptedException {
        String answer;

        do {
            System.out.print("Ваш ход: ");
            answer = input.getMoveAnswer().trim().toLowerCase();
        } while (!Pattern.matches("[a-h]+[1-8]+[-|–|—]+[a-h]+[1-8]", answer) && !answer.equals("gg"));

        return answer;
    }

    @Override
    public void updateMediator(final IAnswer answer, final TurnStatus turnStatus) throws InterruptedException {
        super.updateMediator(answer, turnStatus);

        boardView.update();
        boardView.display();
    }

    @Override
    public char getTransformation() {
        final List<Character> figureList = Arrays.asList('Q', 'N', 'B', 'R');
        char figure;

        do {
            System.out.println("В какую фигуру обратить пешку:\nQ - ферзь\nB - слон\nN - конь\nR - ладья");
            final String string = scanner.nextLine().trim().toUpperCase();
            figure = string.charAt(0);
        } while (!figureList.contains(figure));

        return figure;
    }

    @Override
    public ClientCodes getHandShakeAnswer() {
        String answer = "";

        for (int i = 0; i < 3 && !answer.equals("да") && !answer.equals("нет"); i++) {
            System.out.println("Оппонент найден.\nВы готовы? (да/нет):");
            answer = scanner.nextLine().trim().toLowerCase();
        }

        if (answer.equals("да")) {
            return ClientCodes.YES;
        }else {
            return ClientCodes.NO;
        }
    }


}
