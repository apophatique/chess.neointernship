package neointernship.web.client.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.communication.message.ClientCodes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Bot extends APlayer {
    private IPossibleActionList possibleActionList;
    private final Random random;


    public Bot(final Color color, final String name) {
        super(color, name);
        this.random = new Random();
    }

    public void init(final IMediator mediator, final IBoard board, final Color color) {
        super.init(mediator, board, color);
        this.possibleActionList = new PossibleActionList(board, mediator, storyGame);
    }

    @Override
    public String getAnswer() {
        final List<Figure> figures = (List<Figure>) mediator.getFigures(getColor());
        List<IField> fields;
        Figure figure;
        int index;
        String turn = "";

        final List<Character> integers = Arrays.asList('8', '7', '6', '5', '4', '3', '2', '1');
        final List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');

        possibleActionList.updateRealLists();

        do {
            index = random.nextInt(figures.size());
            figure = figures.get(index);
            fields = (List<IField>) possibleActionList.getRealList(figure);
        } while (fields.isEmpty());

        index = random.nextInt(fields.size());
        final IField finalField = fields.get(index);

        final IField startField = mediator.getField(figure);

        turn += turn + chars.get(startField.getYCoord()) + integers.get(startField.getXCoord()) +  "-" +
                chars.get(finalField.getYCoord()) + integers.get(finalField.getXCoord());

        return turn;
    }

    @Override
    public char getTransformation() {
        return 'Q';
    }

    @Override
    public ClientCodes getHandShakeAnswer() {
        return ClientCodes.YES;
    }


}
