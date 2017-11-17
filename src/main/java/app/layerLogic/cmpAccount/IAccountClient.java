package app.layerLogic.cmpAccount;

import app.layerPersistence.restConsumer.Exception.NotOKValueException;

/**
 * @author Christian G. on 01.11.2017
 */
public interface IAccountClient {

    void registerUser(String name, String password);
    void getAuthenticationToken(String name, String password) throws NotOKValueException;
    void checkLogin(String Token);

}
