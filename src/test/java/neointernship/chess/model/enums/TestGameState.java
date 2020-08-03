package neointernship.chess.model.enums;

import neointernship.chess.game.model.enums.EnumGameState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGameState {
    @Test
    public void testGetMessage(){
        assertEquals("Игра продолжается",EnumGameState.ALIVE.getMessage());
    }
}
