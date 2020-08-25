package neointernship.web.client.player.bot.ai.positionanalyze.analyzer;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.web.client.player.bot.ai.positionanalyze.PST.PSTable;

public class PositionAnalyzer {
    private final IMediator mediator;
    private final Color activeColor;

    private int positionEstimation;
    private double gamePhase;

    public PositionAnalyzer(final IMediator mediator,
                            final Color activeColor) {
        this.mediator = mediator;
        this.activeColor = activeColor;

        estimateGamePhase();
        estimateBoard();
    }

    private void estimateGamePhase() {
        final double startPieceEstimation = 8 * 100 + 2 * 320 + 2 * 330 + 2 * 500 + 900; // start side value: 8 pawns, 2 knighs, 2 bishops, 2 rooks and queen
        double activePlayerFiguresValue = 0;

        for (final Figure figure : mediator.getFigures(activeColor)) {
            activePlayerFiguresValue += figure.getPrice();
        }

        gamePhase = activePlayerFiguresValue/startPieceEstimation;
    }

    private void estimateBoard() {
        positionEstimation = 0;

        for (final Figure figure : mediator.getFigures()) {
            if (figure.getColor() == activeColor) { 
                positionEstimation += figure.getPrice() + PSTable.getMoveCost(
                        figure,
                        mediator.getField(figure),
                        getGamePhase()
                );
            } else {
                positionEstimation -= (figure.getPrice() + PSTable.getMoveCost(
                        figure,
                        mediator.getField(figure),
                        getGamePhase()
                ));
            }
        }
    }

    public int getEstimation() {
        return positionEstimation;
    }

    public double getGamePhase() {
        return gamePhase;
    }
}
