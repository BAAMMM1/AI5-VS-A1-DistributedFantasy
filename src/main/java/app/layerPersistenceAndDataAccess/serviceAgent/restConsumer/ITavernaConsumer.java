package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.entity.taverna.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public interface ITavernaConsumer {

    // TODO - updateAdventurer
    Adventurer addHeroService(Hero hero) throws UnexpectedResponseCodeException;
    List<Adventurer> getAdventurers() throws UnexpectedResponseCodeException;
    Adventurer getAdventure(String name) throws UnexpectedResponseCodeException;

    List<Group> getGroups() throws UnexpectedResponseCodeException;
    Group getGroup(int id) throws UnexpectedResponseCodeException;
    Group createGroup() throws UnexpectedResponseCodeException;
    String deleteGroup(int id) throws UnexpectedResponseCodeException;
    String enterGroup(int id) throws UnexpectedResponseCodeException;
    List<Adventurer> getGroupMembers(int id) throws UnexpectedResponseCodeException;

    //String sendHiringForGroupToHero(String heroname, int groupId) throws UnexpectedResponseCodeException;



}
