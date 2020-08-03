package neointernship.chess.game.moveactions;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;

public class TestHeadCommand {
    protected static IBoard board;
    protected static IMediator mediator;
    protected static IStoryGame storyGame;
    protected static IPossibleActionList possibleActionList;

    protected static void init() {
        board = new Board();
        mediator = new Mediator();
        storyGame = new StoryGame(mediator);
        possibleActionList = new PossibleActionList(board, mediator, storyGame);
    }

    protected void clear() {
        mediator.clear();
        possibleActionList.updateRealLists();
    }

    protected void addFigure(IField field, Figure figure) {
        mediator.addNewConnection(field, figure);
        possibleActionList.updateRealLists();
    }
}
