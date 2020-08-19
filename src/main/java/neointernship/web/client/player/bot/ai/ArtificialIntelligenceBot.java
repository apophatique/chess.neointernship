package neointernship.web.client.player.bot.ai;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.GUI.Input.IInput;
import neointernship.web.client.GUI.board.view.BoardView;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.TurnStatus;
import neointernship.web.client.player.APlayer;
import neointernship.web.client.player.bot.ai.decisiontree.DecisionTreeCreator;
import neointernship.web.client.player.bot.ai.mediator.MediatorExtended;

import java.util.Arrays;
import java.util.List;

public class ArtificialIntelligenceBot extends APlayer {
    private BoardView boardView;
    private final IInput input;

    private final int RECURSION_DEPTH = 4;
    private final DecisionTreeCreator decisionTreeCreator;

    public ArtificialIntelligenceBot(final Color color,
                                     final String name,
                                     final IInput input) {
        super(color, name);
        this.input = input;

        decisionTreeCreator = new DecisionTreeCreator(color);
    }

    @Override
    public void init(final IMediator mediator, final IBoard board, final Color color) {
        super.init(mediator, board, color);


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
        final List<Character> integers = Arrays.asList('8', '7', '6', '5', '4', '3', '2', '1');
        final List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');

        final IAnswer answer = decisionTreeCreator.getDecision(
                mediator,
                board,
                storyGame,
                RECURSION_DEPTH
        );

        String turn = "";
        turn += turn + chars.get(answer.getStartY()) + integers.get(answer.getStartX()) + "-" +
                chars.get(answer.getFinalY()) + integers.get(answer.getFinalX());

        return turn;
    }
}