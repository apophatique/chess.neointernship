package neointernship.web.client.communication.data.transformation;

public class Transformation implements ITransformation {
    Character figureChar;

    public Transformation(final char figureChar) {
        this.figureChar = figureChar;
    }

    @Override
    public Character getFigureChar() {
        return figureChar;
    }
}
