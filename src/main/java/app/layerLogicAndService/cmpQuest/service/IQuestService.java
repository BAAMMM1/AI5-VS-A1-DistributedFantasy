package app.layerLogicAndService.cmpQuest.service;

import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.*;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public interface IQuestService {

    List<Quest> getQuests() throws ErrorCodeException;
    Quest getQuest(int index) throws ErrorCodeException;
    Task getTask(int index) throws ErrorCodeException;
    Map lookAtTheMap(String location) throws ErrorCodeException;

    // TODO - Im moment für currentQuesting, später für übergebenen Questing
    Visit visitHost(String location, int taskId) throws ErrorCodeException;
    Answer answerToCurrentUri(String body) throws ErrorCodeException;
    Visit next() throws ErrorCodeException;
    Visit step(int step) throws ErrorCodeException;

    DeliverTaskDTO deliver() throws ErrorCodeException, ErrorDeliverCodeException;
    DeliverTaskPartDTO deliverStepToken() throws ErrorCodeException, ErrorDeliverCodeException;

}
