package neointernship.web.client.player.bot.ai;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.web.client.GUI.Input.IInput;
import neointernship.web.client.GUI.board.view.BoardView;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.TurnStatus;
import neointernship.web.client.player.APlayer;
import neointernship.web.client.player.bot.ai.decisiontree.DecisionTreeCreator;
import neointernship.web.client.player.bot.ai.decisiontree.base.Node;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArtificialIntelligenceBot extends APlayer {
    private BoardView boardView;
    private final IInput input;

    private final Color color;
    private final String name;
    private final int recursionDepth;
    private final DecisionTreeCreator decisionTreeCreator;
    private IPossibleActionList possibleActionList;


    public ArtificialIntelligenceBot(final Color color,
                                     final String name,
                                     final int recursionDepth, final IInput input) {
        super(color, name);
        this.input = input;

        this.color = color;
        this.name = name;
        this.recursionDepth = recursionDepth;

        decisionTreeCreator = new DecisionTreeCreator(color);
    }

    @Override
    public void init(final IMediator mediator, final IBoard board, final Color color) {
        super.init(mediator, board, color);
        this.possibleActionList = new PossibleActionList(board, mediator, storyGame);

        this.boardView = new BoardView(mediator, board);
        if (!input.isVoid()) boardView.display();
    }


    @Override
    public char getTransformation() throws InterruptedException {
        return 'Q';
    }

    @Override
    public ClientCodes getHandShakeAnswer() throws InterruptedException {
        return ClientCodes.YES;
    }

    @Override
    public void endGame(EnumGameState enumGameState, Color color) throws InterruptedException {
        input.endGame(enumGameState, color);
        boardView.dispose();
    }

    @Override
    public void updateMediator(final IAnswer answer, final TurnStatus turnStatus) throws InterruptedException {
        super.updateMediator(answer, turnStatus);
        if (!input.isVoid()) {
            boardView.update();
            boardView.display();
        }
    }

    @Override
    public String getAnswer() {
        System.out.println("Get Answer");
        final List<Character> integers = Arrays.asList('8', '7', '6', '5', '4', '3', '2', '1');
        final List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');

        decisionTreeCreator.createNewTree(mediator, board, storyGame, recursionDepth);
        final Node node = decisionTreeCreator.getTree().getRoot().getChild();
        final IAnswer answer = node.getAnswerToGet();

        String turn = "";
        turn += turn + chars.get(answer.getStartY()) + integers.get(answer.getStartX()) + "-" +
                chars.get(answer.getFinalY()) + integers.get(answer.getFinalX());

        return turn;
    }
}