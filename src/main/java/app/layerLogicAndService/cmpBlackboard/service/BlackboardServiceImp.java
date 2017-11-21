package app.layerLogicAndService.cmpBlackboard.service;

import app.layerLogicAndService.cmpBlackboard.Exception.NotAuthenticatedException;
import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.LoginTokenDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.IBlackboardConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.WhoamiDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;

/**
 * @author Christian G. on 02.11.2017
 */
public class BlackboardServiceImp implements IBlackboardService {

    private IBlackboardConsumer registerConsumer;

    public BlackboardServiceImp(IBlackboardConsumer registerConsumer) {
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
        Blackboard.getInstance().setUserName(name);
        Blackboard.getInstance().setUserPassword(password);
        Blackboard.getInstance().setUserToken(dto.getToken());
        Blackboard.getInstance().setUserTokenValidTime(dto.getValid_till());

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

                Blackboard.getInstance().setUserName(null);
                Blackboard.getInstance().setUserPassword(null);
                Blackboard.getInstance().setUserToken(null);
                Blackboard.getInstance().setUserTokenValidTime(null);

                throw new NotAuthenticatedException(dto.getMessage());

            }

        } catch (ErrorCodeException e) {
            Blackboard.getInstance().setUserName(null);
            Blackboard.getInstance().setUserPassword(null);
            Blackboard.getInstance().setUserToken(null);
            Blackboard.getInstance().setUserTokenValidTime(null);

            throw new ErrorCodeException(e.getErrorDTO());

        }


        return dto;
    }
}
