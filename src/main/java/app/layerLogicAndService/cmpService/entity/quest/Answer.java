package app.layerLogicAndService.cmpService.entity.quest;

/**
 * @author Chris on 20.11.2017
 */
public class Answer {

    String message;
    String token;
    String token_name;

    public Answer(String message, String token, String token_name) {
        this.message = message;
        this.token = token;
        this.token_name = token_name;
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

    public String getToken_name() {
        return token_name;
    }

    public void setToken_name(String token_name) {
        this.token_name = token_name;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "\nmessage='" + message + '\'' +
                ", \ntoken='" + token + '\'' +
                ", \ntoken_name='" + token_name + '\'' +
                '}';
    }
}
