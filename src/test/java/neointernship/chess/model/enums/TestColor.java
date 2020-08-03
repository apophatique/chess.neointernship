package neointernship.chess.model.enums;

import neointernship.chess.game.model.enums.Color;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TestColor {
    @Test
    public void testWSwapB() {
        Color color = Color.WHITE;
        Color result = Color.swapColor(color);
        Color expected = Color.BLACK;
        assertEquals(expected, result);
    }

    @Test
    public void testBSwapW() {
        Color color = Color.BLACK;
        Color result = Color.swapColor(color);
        Color expected = Color.WHITE;
        assertEquals(expected, result);
    }

    @Test
    public void equals() {
        Set<Color> colorSet = new HashSet<>();
        colorSet.add(Color.WHITE);
        colorSet.add(Color.BLACK);

        assertEquals(2, colorSet.size());
    }

}
