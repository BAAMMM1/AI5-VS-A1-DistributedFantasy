package app.layerLogicAndService.cmpTaverna.service;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.entity.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer.ITavernaConsumer;

import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public class TavernaService implements ITavernaService {

    ITavernaConsumer tavernaConsumer;

    public TavernaService(ITavernaConsumer tavernaConsumer) {
        this.tavernaConsumer = tavernaConsumer;
    }

    @Override
    public Adventurer addHeroServiceToTaverna() throws ErrorCodeException, UnknownHostException {

        Hero hero = new Hero(Blackboard.getInstance().getUser().getHeroclass(), Blackboard.getInstance().getUser().getCapabilities(), "http://" + java.net.InetAddress.getLocalHost().getHostAddress().toString() + ":22/services");

        // TODO - Was machen wir mit den erhaltenen Daten? Irgendwo hinterlegen?
        /*
        {
          "capabilities": "WiZzArD",
          "heroclass": "WiZzArD",
          "url": "http://172.19.0.59:22/services",
          "user": "/users/MeinSuperTestUser"
        }
         */
        Adventurer adventurer = this.tavernaConsumer.addHeroService(hero);

        return adventurer;
    }

    @Override
    public List<Group> getGroups() throws ErrorCodeException {
        return this.tavernaConsumer.getGroups();
    }

    @Override
    public Group getGroup(int id) throws ErrorCodeException {
        return this.tavernaConsumer.getGroup(id);
    }

    @Override
    public Group createGroup() throws ErrorCodeException {
        return this.tavernaConsumer.createGroup();
    }

    @Override
    public String deleteGroup(int id) throws ErrorCodeException {
        return this.tavernaConsumer.deleteGroup(id);
    }

    @Override
    public String enterGroup(int id) throws ErrorCodeException {
        return this.tavernaConsumer.enterGroup(id);
    }

}
