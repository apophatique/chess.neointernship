package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.gameplay.kingstate.subscriber.IKingStateSubscriber;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;

public interface IKingStateController {
    void update(final Color color);

    void addToSubscriber(IKingStateSubscriber subscriber);

    KingState getKingState(final Color color);
}
