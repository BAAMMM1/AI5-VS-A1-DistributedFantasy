package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer;


import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerLogicAndService.cmpQuest.entity.questing.TaskPart;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public interface IQuestConsumer {

    List<Quest> getQuests() throws UnexpectedResponseCodeException;
    Quest getQuest(int index) throws UnexpectedResponseCodeException;
    Task getTask(int index) throws UnexpectedResponseCodeException;
    Map lookAtTheMap(String location) throws UnexpectedResponseCodeException;
    Visit visitHost(String ipPort, String ressource) throws UnexpectedResponseCodeException;
    Answer post(String ipPort, String ressource, String body) throws UnexpectedResponseCodeException;

    // TODO - Nicht task-uri sinder taskId
    List<Delivery> deliverTask(Task task) throws UnexpectedResponseCodeException;

    Visit deliverTaskPart(TaskPart taskpart) throws UnexpectedResponseCodeException;

}
