package neointernship.web.client.communication.data;

public class DataException extends Exception {
    private final DataErrorCode dataErrorCode;

    public DataException(final DataErrorCode dataErrorCode) {
        this.dataErrorCode = dataErrorCode;
    }

    public DataErrorCode getDataErrorCode() {
        return dataErrorCode;
    }
}
