package neointernship.web.client.GUI.board.labels.labelsgetter;

import neointernship.chess.game.model.enums.Color;
import neointernship.web.client.GUI.board.labels.labelsgetter.repositories.BlackFiguresLabelsRepositories;
import neointernship.web.client.GUI.board.labels.labelsgetter.repositories.ILabelsRepository;
import neointernship.web.client.GUI.board.labels.labelsgetter.repositories.WhiteFiguresLabelsRepository;

import java.util.HashMap;

public class LabelsRepository {
    private final HashMap<Color, ILabelsRepository> repositoryHashMap;

    public LabelsRepository() {
        repositoryHashMap = new HashMap<Color, ILabelsRepository>() {
            {
                put(Color.WHITE, new WhiteFiguresLabelsRepository());
                put(Color.BLACK, new BlackFiguresLabelsRepositories());
            }
        };
    }

    public String getLabel(final Color color, final Character character) {
        return repositoryHashMap.get(color).getLabel(character);
    }
}
