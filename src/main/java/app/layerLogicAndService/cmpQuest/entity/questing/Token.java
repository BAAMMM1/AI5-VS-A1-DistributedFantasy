package app.layerLogicAndService.cmpQuest.entity.questing;

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

    @Override
    public String toString() {
        return "Token{" +
                "\nname='" + name + '\'' +
                ", \ntoken='" + token + '\'' +
                '}';
    }
}
