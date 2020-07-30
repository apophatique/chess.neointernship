package neointernship.chess.game.console;

import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;

public class ConsoleBoardWriter implements IConsoleBoardWriter {
    final int boardSize = 8;
    final IMediator mediator;
    final IBoard board;

    public ConsoleBoardWriter(final IMediator mediator, final IBoard board) {
        this.mediator = mediator;
        this.board = board;
    }

    @Override
    public void printBoard() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_WHITE = "\u001B[37m";

        System.out.format("%7c%5c%5c%5c%5c%5c%5c%5c\n", 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
        for (int i = 0; i < 46; i++) {
            System.out.print('-');
        }
        System.out.println();

        for (int i = 0; i < boardSize; i++) {
            System.out.format("%d|", boardSize - i);
            for (int j = 0; j < boardSize; j++) {
                final IField currentField = board.getField(i, j);
                if (mediator.getFigure(currentField) == null) {
                    System.out.format("%5s", ".");
                } else {
                    switch (mediator.getFigure(currentField).getColor()) {
                        case WHITE:
                            System.out.print(ANSI_WHITE);
                            System.out.format("%5s", mediator.getFigure(currentField).getGameSymbol());
                            System.out.print(ANSI_RESET);
                            break;
                        case BLACK:
                            System.out.print(ANSI_BLACK);
                            System.out.format("%5s", mediator.getFigure(currentField).getGameSymbol());
                            System.out.print(ANSI_RESET);
                            break;
                    }
                }
            }
            System.out.format("   |%d", boardSize - i);
            System.out.print("\n\n");
        }
        for (int i = 0; i < 46; i++) {
            System.out.print('-');
        }
        System.out.println();
        System.out.format("%7c%5c%5c%5c%5c%5c%5c%5c\n", 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
    }
}
