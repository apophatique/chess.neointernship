package neointernship.chess.game.model.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonSubTypes({
        @JsonSubTypes.Type(value = Answer.class, name = "Answer"),
        @JsonSubTypes.Type(value = AnswerSimbol.class, name = "AnswerSimbol"),
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface IAnswer {
    int getStartX();

    int getStartY();

    int getFinalX();

    int getFinalY();

    char getSimbol();
}