package app.layerLogicAndService.cmpHero.service;

import app.layerLogicAndService.cmpHero.entity.*;
import app.layerLogicAndService.cmpHero.service.exception.AlreadyInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

import java.util.List;

/**
 * @author Chris on 03.12.2017
 */
public interface IHeroService {

    Service getService();
    void addHiring(Hiring hiring) throws AlreadyInGroupException, UnexpectedResponseCodeException;
    void addAssignment(Assignment assignment);
    void addAssignmentDeliver(AssignmentDerliver assignmentDerliver);
    void addMessage(Message message);
    List<Message> getMessages();

}
