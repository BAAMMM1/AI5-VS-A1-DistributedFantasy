package app.layerLogicAndService.cmpBlackboard.service;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpBlackboard.entity.User;
import app.layerLogicAndService.cmpBlackboard.entity.Register;
import app.layerLogicAndService.cmpBlackboard.entity.Login;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.IBlackboardConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

/**
 * @author Christian G. on 02.11.2017
 */
public class BlackboardServiceImp implements IBlackboardService {

    private IBlackboardConsumer registerConsumer;

    public BlackboardServiceImp(IBlackboardConsumer registerConsumer) {
        this.registerConsumer = registerConsumer;
    }

    @Override
    public Register registerUser(String name, String password) throws ErrorCodeException {

        Register dto = this.registerConsumer.registerUser(name, password);

        return dto;

    }

    @Override
    public Login login(String name, String password) throws ErrorCodeException {

        Login dto = this.registerConsumer.getAuthenticationToken(name, password);

        // Username, Password und Token im System hinterlegen

        // TODO - IlegalArgument, falls dto variablen null
        Blackboard.getInstance().setUser(name, dto.getToken(),dto.getValid_till());

        System.out.println(Blackboard.getInstance().getUser().toString());

        return dto;


    }

    @Override
    public User checkLogin(String Token) throws ErrorCodeException {

        // 200 mit eingeloggt
        User user = null;

        try {
            user = this.registerConsumer.checkLogin(Token);

            if(user == null){
                throw new IllegalArgumentException("user is null");
            }

            Blackboard.getInstance().getUser().set_links(user.get_links());
            Blackboard.getInstance().getUser().setDeliverables_done(user.getDeliverables_done());
            Blackboard.getInstance().getUser().setDelivered(user.getDelivered());
            Blackboard.getInstance().getUser().setIp(user.getIp());
            Blackboard.getInstance().getUser().setLocation(user.getLocation());

            System.out.println(Blackboard.getInstance().getUser().toString());



        } catch (ErrorCodeException e) {
            Blackboard.getInstance().setUser(null, null, null);

            throw new ErrorCodeException(e.getErrorCodeDTO());

        }


        return user;
    }
}
