package neointernship.chess.game.gameplay.kingstate.controller;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.kingstate.subscriber.IKingStateSubscriber;
import neointernship.chess.game.gameplay.kingstate.update.KingIsAttackedComputation;
import neointernship.chess.game.gameplay.kingstate.update.KingStateDefineLogic;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.KingState;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.ArrayList;
import java.util.HashMap;

public class KingsStateController implements IKingStateController {

    private final ArrayList<IKingStateSubscriber> subscribersList;

    private final HashMap<Color, KingState> kingStateMap;
    private final KingIsAttackedComputation kingIsAttackedComputation;
    private final KingStateDefineLogic kingStateDefineLogic;

    public KingsStateController(final IPossibleActionList possibleActionList,
                                final IMediator mediator) {
        kingStateMap = new HashMap<Color, KingState>() {{
            put(Color.WHITE, KingState.FREE);
            put(Color.BLACK, KingState.FREE);
        }};

        subscribersList = new ArrayList<>();

        kingIsAttackedComputation = new KingIsAttackedComputation(possibleActionList, mediator);
        kingStateDefineLogic = new KingStateDefineLogic();
    }

    @Override
    public void addToSubscriber(IKingStateSubscriber subscriber) {
        subscribersList.add(subscriber);
    }

    @Override
    public KingState getKingState(Color color) {
        return kingStateMap.get(color);
    }

    @Override
    public void update(final Color activeColor) {
        boolean kingIsAttacked = kingIsAttackedComputation.kingIsAttacked(activeColor);

        KingState newState = kingStateDefineLogic.getState(kingIsAttacked);

        kingStateMap.replace(activeColor, newState);
    }
}
