package neointernship.web.client.player.bot.ai.possibleactionlist.patterns.real;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public interface IRealBasicPatternsAI {
    Collection<IField> getRealMoveList(final Figure figure, final Collection<IField> potentialMoveList);
}
