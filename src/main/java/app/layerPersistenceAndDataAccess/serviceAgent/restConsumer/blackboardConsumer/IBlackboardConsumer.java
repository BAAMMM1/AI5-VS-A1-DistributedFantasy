package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.LoginTokenDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.WhoamiDTO;

/**
 * @author Christian G. on 02.11.2017
 */
public interface IBlackboardConsumer {

    RegisterUserDTO registerUser(String name, String password) throws ErrorCodeException;
    LoginTokenDTO getAuthenticationToken(String name, String password) throws ErrorCodeException;

    // TODO - Muss der Token hier als String übergeben werden, oder besser direkt aus dem Blackboard lesen?
    WhoamiDTO checkLogin(String Token) throws ErrorCodeException;

}
