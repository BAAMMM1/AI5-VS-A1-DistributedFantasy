package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer;

import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.entity.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public interface ITavernaConsumer {

    // TODO - updateAdventurer
    Adventurer addHeroService(Hero hero) throws ErrorCodeException;

    List<Group> getGroups() throws ErrorCodeException;
    Group getGroup(int id) throws ErrorCodeException;
    Group createGroup() throws ErrorCodeException;
    String deleteGroup(int id) throws ErrorCodeException;
    String enterGroup(int id) throws ErrorCodeException;
    List<Adventurer> getGroupMembers(int id) throws ErrorCodeException;



}
