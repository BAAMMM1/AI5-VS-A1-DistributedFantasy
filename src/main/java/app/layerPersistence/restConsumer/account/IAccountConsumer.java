package app.layerPersistence.restConsumer.account;

import app.layerPersistence.restConsumer.Exception.NotOKValueException;
import app.layerPersistence.restConsumer.account.dto.RegisterUserDTO;
import app.layerPersistence.restConsumer.account.dto.UserTokenDTO;
import app.layerPersistence.restConsumer.account.dto.WhoamiDTO;

import java.io.IOException;

/**
 * @author Christian G. on 02.11.2017
 */
public interface IAccountConsumer {

    RegisterUserDTO registerUser(String name, String password) throws IOException;
    UserTokenDTO getAuthenticationToken(String name, String password) throws NotOKValueException;
    WhoamiDTO checkLogin(String Token) throws IOException;

}
