package app.layerLogicAndService.cmpService.service;

import app.layerLogicAndService.cmpService.entity.blackboard.User;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.net.UnknownHostException;

/**
 * @author Christian G. on 01.11.2017
 */
public interface IBlackboardService {

    User registerUser(String name, String password) throws UnexpectedResponseCodeException, UnknownHostException;
    User login(String name, String password) throws UnexpectedResponseCodeException, UnknownHostException;
    User checkLogin(String Token) throws UnexpectedResponseCodeException;

}
