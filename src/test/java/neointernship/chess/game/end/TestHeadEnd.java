package neointernship.chess.game.end;

import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.activecolorcontroller.ActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.loop.IGameLoop;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.web.client.communication.message.TurnStatus;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestHeadEnd {
    private final IBoard board;
    private final IMediator mediator;
    private final IStoryGame storyGame;

    private final IPossibleActionList possibleActionList;
    private final ActiveColorController activeColorController;
    private final IGameLoop gameLoop;

    private final IConsoleBoardWriter printer;

    public TestHeadEnd(final Map<IField, Figure> figureMap) {
        board = new Board();
        mediator = new Mediator();
        initMediator(figureMap);

        storyGame = new StoryGame(mediator);

        possibleActionList = new PossibleActionList(board, mediator, storyGame);
        possibleActionList.updateRealLists();

        activeColorController = new ActiveColorController();
        gameLoop = new GameLoop(mediator, possibleActionList, board, activeColorController, storyGame);

        printer = new ConsoleBoardWriter(mediator, board);
        activeColorController.update();
    }
    public void updateList(){
        possibleActionList.updateRealLists();
    }

    private void initMediator(final Map<IField, Figure> figureMap) {
        for (IField field : figureMap.keySet()) {
            mediator.addNewConnection(field, figureMap.get(field));
        }
    }

    public void doAllowIteration(final IAnswer answer) {
        TurnStatus turnStatus = gameLoop.doIteration(answer);
        assertNotEquals(TurnStatus.ERROR, turnStatus);
        activeColorController.update();
    }

    public void doRestringIteration(final IAnswer answer) {
        TurnStatus turnStatus = gameLoop.doIteration(answer);
        assertEquals(TurnStatus.ERROR, turnStatus);
        activeColorController.update();
    }

    public IGameState getState() {
        return gameLoop.getMatchResult();
    }

    public void doIterations(final Map<Integer, IAnswer> map) {
        for (Integer i : map.keySet()) {
            final IAnswer answer = map.get(i);
            TurnStatus turnStatus = gameLoop.doIteration(answer);
            assertEquals(EnumGameState.ALIVE, getState().getValue());
            assertNotEquals(TurnStatus.ERROR, turnStatus);
            activeColorController.update();
        }
    }

    public IStoryGame getStoryGame(){
        return storyGame;
    }

    public void printBoard() {
        printer.printBoard();
    }
}
