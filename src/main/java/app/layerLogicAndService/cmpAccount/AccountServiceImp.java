package app.layerLogicAndService.cmpAccount;

import app.layerLogicAndService.Exception.NotAuthenticatedException;
import app.layerLogicAndService.cmpBlackboard.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.LoginTokenDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.IAccountConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.WhoamiDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;

/**
 * @author Christian G. on 02.11.2017
 */
public class AccountServiceImp implements IAccountService {

    private IAccountConsumer registerConsumer;

    public AccountServiceImp(IAccountConsumer registerConsumer) {
        this.registerConsumer = registerConsumer;
    }

    @Override
    public RegisterUserDTO registerUser(String name, String password) throws ErrorCodeException {

        RegisterUserDTO dto = this.registerConsumer.registerUser(name, password);

        return dto;

    }

    @Override
    public LoginTokenDTO getAuthenticationToken(String name, String password) throws ErrorCodeException {

        LoginTokenDTO dto = this.registerConsumer.getAuthenticationToken(name, password);

        // Username, Password und Token im System hinterlegen

        // TODO - IlegalArgument, falls dto variablen null
        Blackboard.getInstance().setName(name);
        Blackboard.getInstance().setPassword(password);
        Blackboard.getInstance().setToken(dto.getToken());
        Blackboard.getInstance().setValid(dto.getValid_till());

        return dto;


    }

    @Override
    public WhoamiDTO checkLogin(String Token) throws ErrorCodeException, NotAuthenticatedException {

        // 200 mit eingeloggt
        WhoamiDTO dto = null;

        try {
            dto = this.registerConsumer.checkLogin(Token);

            if (dto.getMessage().equals("You are authenticated")) {
                // TODO - Hinterlegen der Deliveries

            } else {

                // TODO - Warum 200 als Antwort obwohl man nicht eingeloggt ist?
            /*
            200 - Wenn nicht eingeloggt :/
            WhoamiDTO{message='You are not authenticated / logged in', deliverables_done='null', delivered='null', ip='null', location='null', name='null'}
            */

                Blackboard.getInstance().setName(null);
                Blackboard.getInstance().setPassword(null);
                Blackboard.getInstance().setToken(null);
                Blackboard.getInstance().setValid(null);

                throw new NotAuthenticatedException(dto.getMessage());

            }

        } catch (ErrorCodeException e) {
            Blackboard.getInstance().setName(null);
            Blackboard.getInstance().setPassword(null);
            Blackboard.getInstance().setToken(null);
            Blackboard.getInstance().setValid(null);

            throw new ErrorCodeException(e.getErrorDTO());

        }


        return dto;
    }
}
