package neointernship.chess.game.actions;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPossibleList {
    @Test
    public void testEquals() {
        IBoard board = new Board();
        IMediator mediator = new Mediator();
        mediator.addNewConnection(board.getField(0, 0), new Queen(Color.BLACK));

        IStoryGame storyGame = new StoryGame(mediator);

        IPossibleActionList possibleActionList =
                new PossibleActionList(board, mediator, storyGame);

        Mediator mediatorCopy = new Mediator(mediator);

        PossibleActionList possibleActionListCopy =
                new PossibleActionList(board, mediatorCopy, new StoryGame(mediatorCopy));

        assertEquals(possibleActionList, possibleActionListCopy);

        mediatorCopy.deleteConnection(board.getField(0, 0));

        possibleActionListCopy.updateRealLists();
        possibleActionList.updateRealLists();

        assertEquals(0, mediatorCopy.getFigures().size());
        assertNotEquals(possibleActionList, possibleActionListCopy);

    }
}
