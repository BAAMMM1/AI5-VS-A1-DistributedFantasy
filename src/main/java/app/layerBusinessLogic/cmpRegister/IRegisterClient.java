package app.layerBusinessLogic.cmpRegister;

/**
 * @author Christian G. on 01.11.2017
 */
public interface IRegisterClient {

    void registerUser(String name, String password);
    void getAuthenticationToken(String name, String password);
    void checkLogin(String Token);

}
