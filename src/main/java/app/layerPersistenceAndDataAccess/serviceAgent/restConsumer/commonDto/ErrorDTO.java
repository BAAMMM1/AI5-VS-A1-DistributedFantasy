package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonDto;

/**
 * @author Christian G. on 02.11.2017
 */
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "message='" + message + '\'' +
                '}';
    }
}
