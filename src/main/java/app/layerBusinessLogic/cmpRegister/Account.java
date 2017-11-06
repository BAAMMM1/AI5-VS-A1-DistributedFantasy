package app.layerBusinessLogic.cmpRegister;

/**
 * @author Christian G. on 02.11.2017
 */
public class Account {

    private String name;
    private String password;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account() {

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
