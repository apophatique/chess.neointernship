package neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential;

import neointernship.chess.game.model.figure.piece.Figure;

public interface IPotentialBasicPatternsAI {
    void getDiagonalFields(final Figure figure);

    void getHorizonVerticalFields(final Figure figure);

    void getKnightFields(final Figure knight);

    void getPawnFields(final Figure pawn);

    void getKingFields(final Figure king);
}
