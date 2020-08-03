package neointernship.web.client.GUI.board.labels.labelsgetter.repositories;

import java.util.HashMap;

public class BlackFiguresLabelsRepositories implements ILabelsRepository {
    private final HashMap<Character, String> chessViewMap;

    public BlackFiguresLabelsRepositories() {
        chessViewMap = new HashMap<Character, String>() {
            {
                put('P', "\u265F");
                put('R', "\u265C");
                put('N', "\u265E");
                put('B', "\u265D");
                put('K', "\u265A");
                put('Q', "\u265B");
            }
        };
    }

    public String getLabel(final Character figureCharacter) {
        return chessViewMap.getOrDefault(figureCharacter, " ");
    }

}
