package app.layerLogicAndService.cmpService.service.toHero;

import app.layerLogicAndService.cmpService.entity.hero.Election;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IToHeroService {

    String sendHiringForGroupToHero(String heroName, int groupId, int taskId, String messageToHero) throws UnexpectedResponseCodeException;
    void sendMessage(String adventurer, String string) throws UnexpectedResponseCodeException;
    void sendAssignment(String adventurer,  String message) throws UnexpectedResponseCodeException;
    void sendAssignmentDeliver() throws UnexpectedResponseCodeException;

    // Sicht des Senders der Election (addElection starten)
    void startElection() throws UnexpectedResponseCodeException;




}
