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
    public LoginTokenDTO login(String name, String password) throws ErrorCodeException {

        LoginTokenDTO dto = this.registerConsumer.getAuthenticationToken(name, password);

        // Username, Password und Token im System hinterlegen

        // TODO - IlegalArgument, falls dto variablen null
        Blackboard.getInstance().setUser(name, dto.getToken(),dto.getValid_till());

        System.out.println(Blackboard.getInstance().getUser().toString());

        return dto;


    }

    @Override
    public WhoamiDTO checkLogin(String Token) throws ErrorCodeException, NotAuthenticatedException {

        // 200 mit eingeloggt
        WhoamiDTO dto = null;

        try {
            dto = this.registerConsumer.checkLogin(Token);

            Blackboard.getInstance().getUser().set_links(dto.getUser().get_links());
            Blackboard.getInstance().getUser().setDeliverables_done(dto.getUser().getDeliverables_done());
            Blackboard.getInstance().getUser().setDelivered(dto.getUser().getDelivered());
            Blackboard.getInstance().getUser().setIp(dto.getUser().getIp());
            Blackboard.getInstance().getUser().setLocation(dto.getUser().getLocation());

            System.out.println(Blackboard.getInstance().getUser().toString());

            if (dto.getMessage().equals("You are authenticated")) {
                // TODO - Hinterlegen der Deliveries

            } else {

                // TODO - Warum 200 als Antwort obwohl man nicht eingeloggt ist?
            /*
            200 - Wenn nicht eingeloggt :/
            WhoamiDTO{message='You are not authenticated / logged in', deliverables_done='null', delivered='null', ip='null', location='null', name='null'}
            */

                Blackboard.getInstance().setUser(null, null, null);

                throw new NotAuthenticatedException(dto.getMessage());

            }

        } catch (ErrorCodeException e) {
            Blackboard.getInstance().setUser(null, null, null);

            throw new ErrorCodeException(e.getErrorDTO());

        }


        return dto;
    }
}
