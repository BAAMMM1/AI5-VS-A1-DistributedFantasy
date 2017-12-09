package app.layerLogicAndService.cmpBlackboard.service;

import app.layerLogicAndService.cmpBlackboard.entity.User;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

/**
 * @author Christian G. on 01.11.2017
 */
public interface IBlackboardService {

    User registerUser(String name, String password) throws UnexpectedResponseCodeException;
    User login(String name, String password) throws UnexpectedResponseCodeException;
    User checkLogin(String Token) throws UnexpectedResponseCodeException;

}
