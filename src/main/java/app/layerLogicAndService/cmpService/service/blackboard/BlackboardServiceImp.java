package app.layerLogicAndService.cmpService.service.blackboard;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.blackboard.User;
import app.layerLogicAndService.cmpService.entity.blackboard.Login;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.BlackboardConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IBlackboardConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

/**
 * @author Christian G. on 02.11.2017
 */
public class BlackboardServiceImp implements IBlackboardService {

    private IBlackboardConsumer registerConsumer = new BlackboardConsumer();

    // 1. Register user, 2. login user, 3. führe whoami aus
    @Override
    public User registerUser(String name, String password) throws UnexpectedResponseCodeException {

        this.registerConsumer.registerUser(name, password);

        return this.login(name, password);

    }

    // 2. login user, 3. führe whoami aus
    @Override
    public User login(String name, String password) throws UnexpectedResponseCodeException {

        Login login = this.registerConsumer.getAuthenticationToken(name, password);

        // Username, Password und Token im System hinterlegen
        Blackboard.getInstance().setUser(name, login.getToken(),login.getValid_till());

        User user = this.checkLogin(Blackboard.getInstance().getUser().getUserToken());

        return user;


    }

    @Override
    public User checkLogin(String Token) throws UnexpectedResponseCodeException {

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


        } catch (UnexpectedResponseCodeException e) {
            Blackboard.getInstance().setUser(null, null, null);

            throw new UnexpectedResponseCodeException(e.getResponse());

        }

        return user;
    }
}
