package app.layerLogicAndService.cmpAccount;

import app.layerLogicAndService.Exception.NotAuthenticatedException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.WhoamiDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.LoginTokenDTO;

/**
 * @author Christian G. on 01.11.2017
 */
public interface IAccountService {

    RegisterUserDTO registerUser(String name, String password) throws ErrorCodeException;
    LoginTokenDTO getAuthenticationToken(String name, String password) throws ErrorCodeException;
    WhoamiDTO checkLogin(String Token) throws ErrorCodeException, NotAuthenticatedException;

}
