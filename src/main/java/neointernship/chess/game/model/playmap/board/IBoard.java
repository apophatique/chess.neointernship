package neointernship.chess.game.model.playmap.board;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import neointernship.chess.game.model.playmap.field.IField;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface IBoard {
    IField getField(final int x, final int y);
    short getSize();
}