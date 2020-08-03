package neointernship.web.client.GUI.board.labels.labelsgetter.repositories;

import java.util.HashMap;

public class WhiteFiguresLabelsRepository implements ILabelsRepository {
    private final HashMap<Character, String> chessViewMap;

    public WhiteFiguresLabelsRepository() {
        chessViewMap = new HashMap<Character, String>() {
            {
                put('P', "\u2659");
                put('R', "\u2656");
                put('N', "\u2658");
                put('B', "\u2657");
                put('K', "\u2654");
                put('Q', "\u2655");
            }
        };
    }

    public String getLabel(final Character figureCharacter) {
        return chessViewMap.getOrDefault(figureCharacter, " ");
    }
}
