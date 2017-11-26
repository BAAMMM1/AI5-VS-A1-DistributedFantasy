package app.layerLogicAndService.cmpQuest.entity.besuch;

/**
 * @author Chris on 26.11.2017
 */
public class Token {

    private String name;
    private String token;

    public Token(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
