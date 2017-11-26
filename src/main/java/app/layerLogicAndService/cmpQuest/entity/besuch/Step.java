package app.layerLogicAndService.cmpQuest.entity.besuch;

/**
 * @author Chris on 26.11.2017
 */
public class Step {

    private String uri;
    private Token token;

    public Step(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
