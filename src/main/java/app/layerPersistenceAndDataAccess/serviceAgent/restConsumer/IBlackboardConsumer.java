package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpService.entity.blackboard.User;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.layerLogicAndService.cmpService.entity.blackboard.Register;
import app.layerLogicAndService.cmpService.entity.blackboard.Login;

/**
 * @author Christian G. on 02.11.2017
 */
public interface IBlackboardConsumer {

    Register registerUser(String name, String password) throws UnexpectedResponseCodeException;
    Login getAuthenticationToken(String name, String password) throws UnexpectedResponseCodeException;

    // TODO - Muss der Token hier als String übergeben werden, oder besser direkt aus dem Blackboard lesen?
    User checkLogin(String Token) throws UnexpectedResponseCodeException;

}
