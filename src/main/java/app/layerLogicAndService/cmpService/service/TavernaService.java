package app.layerLogicAndService.cmpService.service;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.entity.taverna.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.TavernaConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ITavernaConsumer;

import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public class TavernaService implements ITavernaService {

    ITavernaConsumer tavernaConsumer = new TavernaConsumer();


    @Override
    public Adventurer addHeroServiceToTaverna() throws UnexpectedResponseCodeException, UnknownHostException {

        Hero hero = new Hero(Blackboard.getInstance().getUser().getHeroclass(), Blackboard.getInstance().getUser().getCapabilities(), "http://" + java.net.InetAddress.getLocalHost().getHostAddress().toString() + ":8080/services");

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
    public List<Adventurer> getAdventurers() throws UnexpectedResponseCodeException {
        return this.tavernaConsumer.getAdventurers();
    }

    @Override
    public Adventurer getAdventure(String name) throws UnexpectedResponseCodeException {
        return this.tavernaConsumer.getAdventure(name);
    }

    @Override
    public List<Group> getGroups() throws UnexpectedResponseCodeException {
        return this.tavernaConsumer.getGroups();
    }

    @Override
    public Group getGroup(int id) throws UnexpectedResponseCodeException {
        return this.tavernaConsumer.getGroup(id);
    }

    @Override
    public Group createGroup() throws UnexpectedResponseCodeException {
        return this.tavernaConsumer.createGroup();
    }

    @Override
    public String deleteGroup(int id) throws UnexpectedResponseCodeException {
        return this.tavernaConsumer.deleteGroup(id);
    }

    @Override
    public String enterGroup(int id) throws UnexpectedResponseCodeException {
        Group group = this.getGroup(id);

        Blackboard.getInstance().getUser().setCurrentGroup(group);

        return this.tavernaConsumer.enterGroup(id);
    }

    @Override
    public List<Adventurer> getGroupMembers(int id) throws UnexpectedResponseCodeException {
        return this.tavernaConsumer.getGroupMembers(id);
    }

}
