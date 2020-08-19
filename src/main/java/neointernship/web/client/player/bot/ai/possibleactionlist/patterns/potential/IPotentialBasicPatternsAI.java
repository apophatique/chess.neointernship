package neointernship.web.client.player.bot.ai.possibleactionlist.patterns.potential;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.ArrayList;

public interface IPotentialBasicPatternsAI {
    ArrayList<IField> getDiagonalFields(final Figure figure);

    ArrayList<IField> getHorizonVerticalFields(final Figure figure);

    ArrayList<IField> getKnightFields(final Figure knight);

    ArrayList<IField> getPawnFields(final Figure pawn);

    ArrayList<IField> getKingFields(final Figure king);
}
