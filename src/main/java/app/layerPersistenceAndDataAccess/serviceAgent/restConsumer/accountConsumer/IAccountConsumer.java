package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.LoginTokenDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.WhoamiDTO;

/**
 * @author Christian G. on 02.11.2017
 */
public interface IAccountConsumer {

    RegisterUserDTO registerUser(String name, String password) throws ErrorCodeException;
    LoginTokenDTO getAuthenticationToken(String name, String password) throws ErrorCodeException;

    // TODO - Muss der Token hier als String übergeben werden, oder besser direkt aus dem Blackboard lesen?
    WhoamiDTO checkLogin(String Token) throws ErrorCodeException;

}
