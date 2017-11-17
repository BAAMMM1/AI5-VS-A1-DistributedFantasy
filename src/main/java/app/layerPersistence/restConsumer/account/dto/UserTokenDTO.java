package app.layerPersistence.restConsumer.account.dto;

/**
 * @author Christian G. on 02.11.2017
 */
public class UserTokenDTO {

    private String message;
    private String token;

    // TODO - private Date?
    private String valid_till;

    public UserTokenDTO(String message, String token, String valid_till) {
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
        return "UserTokenDTO{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", valid_till='" + valid_till + '\'' +
                '}';
    }
}
