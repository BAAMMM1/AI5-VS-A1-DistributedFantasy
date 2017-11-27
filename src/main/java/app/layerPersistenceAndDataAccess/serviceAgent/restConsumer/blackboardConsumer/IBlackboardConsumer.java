package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.User;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
import app.layerLogicAndService.cmpBlackboard.entity.Register;
import app.layerLogicAndService.cmpBlackboard.entity.Login;

/**
 * @author Christian G. on 02.11.2017
 */
public interface IBlackboardConsumer {

    Register registerUser(String name, String password) throws ErrorCodeException;
    Login getAuthenticationToken(String name, String password) throws ErrorCodeException;

    // TODO - Muss der Token hier als String übergeben werden, oder besser direkt aus dem Blackboard lesen?
    User checkLogin(String Token) throws ErrorCodeException;

}
