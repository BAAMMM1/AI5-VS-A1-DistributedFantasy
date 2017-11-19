package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.UserTokenDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.WhoamiDTO;

import java.io.IOException;

/**
 * @author Christian G. on 02.11.2017
 */
public interface IAccountConsumer {

    RegisterUserDTO registerUser(String name, String password) throws ErrorCodeException;
    UserTokenDTO getAuthenticationToken(String name, String password) throws ErrorCodeException;
    WhoamiDTO checkLogin(String Token) throws ErrorCodeException;

}
