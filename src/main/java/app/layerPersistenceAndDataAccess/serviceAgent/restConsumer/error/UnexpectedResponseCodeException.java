package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error;

/**
 * @author Christian G. on 17.11.2017
 */
public class UnexpectedResponseCodeException extends Exception {

    private int code;

    public UnexpectedResponseCodeException(int code, String message) {
        super(message);

    }

    public int getCode() {
        return code;
    }
}
