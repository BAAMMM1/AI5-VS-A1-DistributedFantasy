package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer;

import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.entity.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

/**
 * @author Chris on 01.12.2017
 */
public interface ITavernaConsumer {

    Adventurer addHeroService(Hero hero) throws ErrorCodeException;
    Group createGroup() throws ErrorCodeException;
    String deleteGroup(int id) throws ErrorCodeException;

    // TODO - updateAdventurer
    // TODO - delete Group

}
