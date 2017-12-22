package app.layerLogicAndService.cmpService.service;

import app.layerLogicAndService.cmpService.entity.hero.mutex.Mutex;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.util.List;

/**
 * @author Chris on 03.12.2017
 */
public interface IFromHeroService {

    Service getService();
    void addHiring(Hiring hiring) throws AlreadyInGroupException, UnexpectedResponseCodeException;
    void addAssignment(Assignment assignment);
    void addAssignmentDeliver(AssignmentDeliver assignmentDeliver) throws UnexpectedResponseCodeException;
    void addMessage(Message message);
    List<Message> getMessages();
    void addElection(Election election) throws UnexpectedResponseCodeException, NotInGroupException;

    Mutex getMutex();

}
