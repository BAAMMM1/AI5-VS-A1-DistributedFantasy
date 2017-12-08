package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer;

import app.layerLogicAndService.cmpHero.entity.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IHeroToHeroConsumer {

    // For invite heroService to group
    Service getHeroService(String heroServiceUrl) throws UnexpectedResponseCodeException;
    String hiringHero(Hiring hiring, String herHiringUrl) throws UnexpectedResponseCodeException;
    void sendMessage(Message message, String heroMessageUrl) throws UnexpectedResponseCodeException;
    void sendAssignment(String heroAssignmentUrl, Assignment assignment) throws UnexpectedResponseCodeException;
    void sendAssignmentDeliver(String assignmentDeliverUrl, AssignmentDerliver assignmentDeliver) throws UnexpectedResponseCodeException;



}
