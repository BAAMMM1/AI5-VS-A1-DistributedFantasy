package app.layerLogicAndService.cmpHeroToHero.service;

import app.layerLogicAndService.cmpHero.entity.AssignmentDerliver;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

/**
 * @author Chris on 02.12.2017
 */
public interface IHeroToHeroService {

    String invite(String heroName, int groupId, int taskId, String messageToHero) throws ErrorCodeException;
    void sendMessage(String adventurer, String string) throws ErrorCodeException;
    void sendAssignment(String adventurer,  String message) throws ErrorCodeException;

    void sendAssignmentDeliver() throws ErrorCodeException;




}
