package app.layerDataAccess.restConsumer.registerConsume;

import app.layerDataAccess.restConsumer.registerConsume.DTO.RegisterUserDTO;
import app.layerDataAccess.restConsumer.registerConsume.DTO.UserTokenDTO;
import app.layerDataAccess.restConsumer.registerConsume.DTO.WhoamiDTO;

import java.io.IOException;

/**
 * @author Christian G. on 02.11.2017
 */
public interface IRegisterConsumer {

    RegisterUserDTO registerUser(String name, String password) throws IOException;
    UserTokenDTO getAuthenticationToken(String name, String password) throws IOException;
    WhoamiDTO checkLogin(String Token) throws IOException;

}
