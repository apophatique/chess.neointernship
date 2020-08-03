package neointernship.chess.game.gameplay.kingstate.subscriber;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;

public interface IKingStateSubscriber {
    void update(final Color color, final KingState kingState);
}
