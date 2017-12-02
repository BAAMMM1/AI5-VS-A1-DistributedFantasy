package app.layerLogicAndService.cmpHeroToHero.service;

import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IHeroToHeroService {

    // getHeroService(String heroName, int groupId) throws ErrorCodeException;
    Service getHeroService(String heroName, int groupId, String heroServiceUrl) throws ErrorCodeException;

}
