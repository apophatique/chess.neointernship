package neointernship.web.client.player.bot.ai.extended.possibleactionlist.patterns.potential.move;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

public class Move {
    private final Figure movingFigure;
    private final IField fieldToMove;

    public Move(final Figure movingFigure, final IField fieldToMove) {
        this.fieldToMove = fieldToMove;
        this.movingFigure = movingFigure;
    }
    public Figure getMovingFigure() {
        return movingFigure;
    }

    public IField getFieldToMove() {
        return fieldToMove;
    }
}
