package neointernship.chess.game.model.playmap.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class Board implements IBoard {

    @JsonProperty
    private static final short BOARD_SIZE = 8;
    @JsonProperty
    private IField[][] fieldMatrix;

    @JsonCreator
    public Board() {
        initializeBoard();
    }

    /**
     * инициализируем матрицу состоящую из клеток
     */
    private void initializeBoard() {
        fieldMatrix = new IField[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE ; j++) {
                fieldMatrix[i][j] = new Field(i,j);
            }
        }
    }

    @Override
    public IField getField(final int x, final int y) {
        return fieldMatrix[x][y];
    }

    @Override
    public short getSize() {
        return BOARD_SIZE;
    }
}
