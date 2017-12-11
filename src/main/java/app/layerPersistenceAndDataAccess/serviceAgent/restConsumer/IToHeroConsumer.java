package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IToHeroConsumer {

    // For sendHiringForGroupToHero heroService to group
    Service getHeroService(String heroServiceUrl) throws UnexpectedResponseCodeException;

    String sendHiring(Hiring hiring, String herHiringUrl) throws UnexpectedResponseCodeException;
    void sendMessage(Message message, String heroMessageUrl) throws UnexpectedResponseCodeException;
    void sendAssignment(String heroAssignmentUrl, Assignment assignment) throws UnexpectedResponseCodeException;
    void sendAssignmentDeliver(String assignmentDeliverUrl, AssignmentDerliver assignmentDeliver) throws UnexpectedResponseCodeException;
    void sendElection(String electionUrl, Election election) throws UnexpectedResponseCodeException;



}
