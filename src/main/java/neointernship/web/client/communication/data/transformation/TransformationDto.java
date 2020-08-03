package neointernship.web.client.communication.data.transformation;

import neointernship.web.client.communication.data.DataErrorCode;
import neointernship.web.client.communication.data.DataException;

public class TransformationDto implements ITransformation {
    Character figureChar;

    public void validate() throws DataException {
        if (figureChar == null){
            throw new DataException(DataErrorCode.NOT_FIGURE_CHAR);
        }
    }

    @Override
    public Character getFigureChar() {
        return figureChar;
    }
}
