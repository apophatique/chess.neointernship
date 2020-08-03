package neointernship.chess.game.moveactions;

import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import org.junit.After;
import org.junit.BeforeClass;

public class TestAllowCommand extends TestHeadCommand {

    protected static AllowMoveCommand allowMoveCommand;

    @BeforeClass
    public static void init() {
        TestHeadCommand.init();
        allowMoveCommand = new AllowMoveCommand(mediator, possibleActionList, board, storyGame);
    }

    @After
    public void clear() {
        super.clear();
    }
}
