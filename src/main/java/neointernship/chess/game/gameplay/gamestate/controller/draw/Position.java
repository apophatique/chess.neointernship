package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.story.StoryGame;

import java.util.Objects;

public class Position {
    private final IMediator mediator;
    private final IPossibleActionList possibleActionList;

    public Position(IMediator mediator) {
        this.mediator = new Mediator(mediator);
        this.possibleActionList = new PossibleActionList(new Board(),this.mediator,new StoryGame(this.mediator));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return this.mediator.equals(position.mediator) &&
                this.possibleActionList.equals(position.possibleActionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediator,possibleActionList);
    }
}
