package app.layerLogicAndService.cmpTaverna.service;

import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.net.UnknownHostException;
import java.util.List;


/**
 * @author Chris on 01.12.2017
 */
public interface ITavernaService {

    // TODO - updateAdventurer
    Adventurer addHeroServiceToTaverna() throws ErrorCodeException, UnknownHostException;

    List<Group> getGroups() throws ErrorCodeException;
    Group getGroup(int id) throws ErrorCodeException;
    Group createGroup() throws ErrorCodeException;
    String deleteGroup(int id) throws ErrorCodeException;




}
