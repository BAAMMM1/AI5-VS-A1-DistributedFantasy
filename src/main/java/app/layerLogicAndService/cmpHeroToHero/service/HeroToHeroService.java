package app.layerLogicAndService.cmpHeroToHero.service;

import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer.IHeroToHeroConsumer;

/**
 * @author Chris on 02.12.2017
 */
public class HeroToHeroService implements IHeroToHeroService {

    IHeroToHeroConsumer heroToHeroConsumer;

    public HeroToHeroService(IHeroToHeroConsumer heroToHeroConsumer) {
        this.heroToHeroConsumer = heroToHeroConsumer;
    }

    @Override
    public Service getHeroService(String heroName, int groupId, String heroServiceUrl) throws ErrorCodeException {
        return this.heroToHeroConsumer.getHeroService(heroName, groupId, heroServiceUrl);
    }
}
