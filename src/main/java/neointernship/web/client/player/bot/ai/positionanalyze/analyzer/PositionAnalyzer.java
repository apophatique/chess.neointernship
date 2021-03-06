package neointernship.web.client.player.bot.ai.positionanalyze.analyzer;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.web.client.player.bot.ai.extended.possibleactionlist.IAIPossibleActionList;
import neointernship.web.client.player.bot.ai.positionanalyze.PST.PSTable;


public class PositionAnalyzer {
    public PositionAnalyzer(final Color rootColor) {
    }

    public double getMatchPhase(final IMediator mediator,
                                 final Color activeColor) {
        final double startPieceEstimation = 8 * 100 + 2 * 320 + 2 * 330 + 2 * 500 + 900; // start side value: 8 pawns, 2 knighs, 2 bishops, 2 rooks and queen
        double activePlayerFiguresValue = 0;

        for (final Figure figure : mediator.getFigures(activeColor)) {
            activePlayerFiguresValue += figure.getPrice();
        }

        return activePlayerFiguresValue/startPieceEstimation;
    }

    public int estimateBoard(final IMediator mediator,
                               final Color activeColor) {
        int positionEstimation = 0;
        final double gamePhase = getMatchPhase(mediator, activeColor);

        for (final Figure figure : mediator.getFigures()) {

            double value = figure.getPrice() + PSTable.getMoveCost(
                        figure,
                        mediator.getField(figure),
                        gamePhase
            );

            if (figure.getColor() == activeColor) {
                positionEstimation += value;
            } else {
                positionEstimation -= value;
            }
        }
        return positionEstimation;
    }
}
