package app.layerLogicAndService.cmpService.entity.blackboard;

/**
 * @author Christian G. on 02.11.2017
 */
public class Login {

    private String message;
    private String token;

    // TODO - private Date? Was ist das für ein Dateformat?
    private String valid_till;

    public Login(String message, String token, String valid_till) {
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
        return "Login{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", valid_till='" + valid_till + '\'' +
                '}';
    }
}
