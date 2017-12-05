package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer;

import app.layerLogicAndService.cmpHero.entity.Assignment;
import app.layerLogicAndService.cmpHero.entity.Hiring;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IHeroToHeroConsumer {

    // For invite heroService to group
    Service getHeroService(String heroServiceUrl) throws ErrorCodeException;
    String hiringHero(Hiring hiring, String herHiringUrl) throws ErrorCodeException;
    void sendMessage(Message message, String heroMessageUrl) throws ErrorCodeException;
    void sendAssignment(String adventurer, String heroAssignmentUrl, Assignment assignment) throws ErrorCodeException;



}
