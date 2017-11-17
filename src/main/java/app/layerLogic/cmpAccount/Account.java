package app.layerLogic.cmpAccount;

/**
 * @author Christian G. on 02.11.2017
 */
public class Account {

    private String name;
    private String password;
    private String token;

    private static Account instance;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private Account() {

    }

    public static Account getInstance () {

        if (Account.instance == null) {
            Account.instance = new Account ();

        }

        return Account.instance;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
