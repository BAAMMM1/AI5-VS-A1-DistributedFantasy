package app.layerLogicAndService.cmpBlackboard.service;

import app.layerLogicAndService.cmpBlackboard.entity.User;
import app.layerLogicAndService.cmpBlackboard.entity.Login;
import app.layerLogicAndService.cmpBlackboard.entity.Register;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

/**
 * @author Christian G. on 01.11.2017
 */
public interface IBlackboardService {

    Register registerUser(String name, String password) throws ErrorCodeException;
    User login(String name, String password) throws ErrorCodeException;
    User checkLogin(String Token) throws ErrorCodeException;

}
