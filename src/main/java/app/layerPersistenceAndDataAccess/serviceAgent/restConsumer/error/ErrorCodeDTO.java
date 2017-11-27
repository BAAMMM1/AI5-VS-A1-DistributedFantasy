package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error;

/**
 * @author Christian G. on 02.11.2017
 */
public class ErrorCodeDTO {

    private String error;
    private String message;

    public ErrorCodeDTO(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorCodeDTO{" +
                "\nerror='" + error + '\'' +
                ", \nmessage='" + message + '\'' +
                '}';
    }
}
