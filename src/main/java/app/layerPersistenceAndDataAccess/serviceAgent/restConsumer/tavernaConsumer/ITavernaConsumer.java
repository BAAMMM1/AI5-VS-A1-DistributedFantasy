package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer;

import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.entity.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

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

    //String invite(String heroname, int groupId) throws UnexpectedResponseCodeException;



}
