package app.layerLogicAndService.cmpTaverna.service;

import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.net.UnknownHostException;


/**
 * @author Chris on 01.12.2017
 */
public interface ITavernaService {

    Adventurer addHeroService() throws ErrorCodeException, UnknownHostException;
    Group createGroup() throws ErrorCodeException;
    String deleteGroup(int id) throws ErrorCodeException;

    // TODO - updateAdventurer
    // TODO - delete Group
}
