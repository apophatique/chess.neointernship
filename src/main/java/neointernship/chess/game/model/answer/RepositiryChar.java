package neointernship.chess.game.model.answer;

import java.util.HashMap;
import java.util.Map;

public class RepositiryChar {

    private static Map<Character, Integer> mapY;
    private static Map<Character, Integer> mapX;

    public RepositiryChar() {
        mapY = new HashMap<Character, Integer>() {{
            put('a', 0);
            put('b', 1);
            put('c', 2);
            put('d', 3);
            put('e', 4);
            put('f', 5);
            put('g', 6);
            put('h', 7);
        }};
        mapX = new HashMap<Character, Integer>() {{
            put('1', 7);
            put('2', 6);
            put('3', 5);
            put('4', 4);
            put('5', 3);
            put('6', 2);
            put('7', 1);
            put('8', 0);
        }};
    }

    public int getY(char c) {
        return mapY.get(c);
    }

    public char getY(int i) {
        for (Map.Entry<Character, Integer> set : mapY.entrySet()) {
            if (set.getValue() == i) return set.getKey();
        }
        return 0;
    }

    public int getX(char c) {
        return mapX.get(c);
    }

    public char getX(int i) {
        for (Map.Entry<Character, Integer> set : mapX.entrySet()) {
            if (set.getValue() == i) return set.getKey();
        }
        return 0;
    }
}
