package app.layerLogicAndService.cmpBlackboard.service;

import app.layerLogicAndService.cmpBlackboard.Exception.NotAuthenticatedException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.WhoamiDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.LoginTokenDTO;

/**
 * @author Christian G. on 01.11.2017
 */
public interface IBlackboardService {

    RegisterUserDTO registerUser(String name, String password) throws ErrorCodeException;
    LoginTokenDTO login(String name, String password) throws ErrorCodeException;
    WhoamiDTO checkLogin(String Token) throws ErrorCodeException, NotAuthenticatedException;

}
