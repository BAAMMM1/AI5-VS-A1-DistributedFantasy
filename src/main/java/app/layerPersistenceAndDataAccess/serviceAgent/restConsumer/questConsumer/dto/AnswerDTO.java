package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

/**
 * @author Chris on 20.11.2017
 */
public class AnswerDTO {

    String message;
    String token;

    public AnswerDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "\nmessage='" + message + '\'' +
                ", \ntoken='" + token + '\'' +
                '}';
    }
}
