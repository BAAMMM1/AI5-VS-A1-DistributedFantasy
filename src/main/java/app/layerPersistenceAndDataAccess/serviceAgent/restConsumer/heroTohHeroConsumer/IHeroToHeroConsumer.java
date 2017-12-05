package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer;

import app.layerLogicAndService.cmpHero.entity.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IHeroToHeroConsumer {

    // For invite heroService to group
    Service getHeroService(String heroServiceUrl) throws ErrorCodeException;
    String hiringHero(Hiring hiring, String herHiringUrl) throws ErrorCodeException;
    void sendMessage(Message message, String heroMessageUrl) throws ErrorCodeException;
    void sendAssignment(String heroAssignmentUrl, Assignment assignment) throws ErrorCodeException;
    void sendAssignmentDeliver(String assignmentDeliverUrl, AssignmentDerliver assignmentDeliver) throws ErrorCodeException;



}
