package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.HashMap;
import java.util.Map;

public class DrawRepetitionPosition implements IDrawController {

    private static final Integer MAX_REPETITION = 3;
    private static final Integer START_REPETITION = 1;
    private Map<Position,Integer> mapPosition;
    private int currentSizeMediator;

    public DrawRepetitionPosition() {
        mapPosition = new HashMap<>();
    }
    /**
     * проверка на 3х кратное повторении позиции
     * @param mediator
     * @return
     */
    @Override
    public boolean isDraw(IMediator mediator) {
        final int newSize = mediator.getFigures().size();
        if(currentSizeMediator != newSize){
            currentSizeMediator = newSize;
            mapPosition.clear();
        }
        Position newPosition = new Position(mediator);

        Integer count = mapPosition.get(newPosition);

        if(count == null){
            mapPosition.put(newPosition,START_REPETITION);
        }else{
            count++;
            mapPosition.replace(newPosition,count);
        }
        for(Integer i : mapPosition.values()){
            if(i >= MAX_REPETITION) return true;
        }
        return false;
    }

    @Override
    public EnumGameState getState() {
        return EnumGameState.DRAW_REPETITION_POSITION;
    }
}
