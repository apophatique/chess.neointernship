package neointernship.web.client.player.bot.ai.possibleactionlist;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.Map;

public interface IAIPossibleActionList {
    void updateAll();
    void update(final IMediator mediator);
    Collection<IField> getList(final Figure figure);
    void show();
    Map<Figure, Collection<IField>> getRealList();
    Map<Figure, Collection<IField>> getPotentialList();
    Map<Figure, Collection<IField>> getBarrierList();
}
