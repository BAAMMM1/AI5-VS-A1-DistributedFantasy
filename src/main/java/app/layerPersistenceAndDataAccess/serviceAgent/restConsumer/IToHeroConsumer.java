package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerLogicAndService.cmpService.entity.hero.mutex.Mutex;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexMessage;
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
    void sendAssignmentDeliver(String assignmentDeliverUrl, AssignmentDeliver assignmentDeliver) throws UnexpectedResponseCodeException;
    void sendElection(String electionUrl, Election election) throws UnexpectedResponseCodeException;

    void sendMutexMessage(String mutexUrl, MutexMessage mutexMessage) throws UnexpectedResponseCodeException;
    Mutex getMutexState(String heroMutexStateUrl) throws UnexpectedResponseCodeException;

}
