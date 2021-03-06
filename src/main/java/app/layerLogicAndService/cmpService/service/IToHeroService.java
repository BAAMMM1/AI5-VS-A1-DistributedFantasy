package app.layerLogicAndService.cmpService.service;

import app.layerLogicAndService.cmpService.entity.hero.Assignment;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IToHeroService {

    String sendHiringForGroupToHero(String heroName, int groupId, int taskId, String messageToHero) throws UnexpectedResponseCodeException;
    void sendMessage(String adventurer, String string) throws UnexpectedResponseCodeException;
    void sendAssignment(String adventurer,  String message) throws UnexpectedResponseCodeException;
    void sendAssignmentDeliver() throws UnexpectedResponseCodeException;
    void sendEndAssignment(String adventurer, String message) throws UnexpectedResponseCodeException;

    // Sicht des Senders der Election (addElection starten)
    void startElection(Assignment assignment) throws UnexpectedResponseCodeException, NotInGroupException;

    void doElection() throws UnexpectedResponseCodeException, NotInGroupException;

    void getHerosServices() throws UnexpectedResponseCodeException;

    String wantMutex(String ipPort, String ressource) throws UnexpectedResponseCodeException;





}
