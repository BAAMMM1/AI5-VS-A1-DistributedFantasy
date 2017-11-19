package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto;

/**
 * @author Christian G. on 02.11.2017
 */
public class LoginTokenDTO {

    private String message;
    private String token;

    // TODO - private Date? Was ist das für ein Dateformat?
    private String valid_till;

    public LoginTokenDTO(String message, String token, String valid_till) {
        this.message = message;
        this.token = token;
        this.valid_till = valid_till;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getValid_till() {
        return valid_till;
    }

    @Override
    public String toString() {
        return "LoginTokenDTO{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", valid_till='" + valid_till + '\'' +
                '}';
    }
}
