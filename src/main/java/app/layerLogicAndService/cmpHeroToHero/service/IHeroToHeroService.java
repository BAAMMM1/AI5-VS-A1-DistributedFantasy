package app.layerLogicAndService.cmpHeroToHero.service;

import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IHeroToHeroService {

    String invite(String heroName, int groupId, int questId, String messageToHero) throws ErrorCodeException;


}
