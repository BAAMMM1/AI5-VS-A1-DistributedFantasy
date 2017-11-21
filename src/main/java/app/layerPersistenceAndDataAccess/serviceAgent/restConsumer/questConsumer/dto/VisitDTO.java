package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

/**
 * @author Chris on 19.11.2017
 */
public class VisitDTO {

    private String message;

    public VisitDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "VisitDTO{" +
                "\nmessage='" + message + '\'' +
                '}';
    }
}
