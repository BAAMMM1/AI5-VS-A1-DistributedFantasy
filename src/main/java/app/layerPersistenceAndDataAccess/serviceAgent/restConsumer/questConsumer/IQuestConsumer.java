package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer;


import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerLogicAndService.cmpQuest.entity.questing.TaskPart;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public interface IQuestConsumer {

    List<Quest> getQuests() throws ErrorCodeException;
    Quest getQuest(int index) throws ErrorCodeException;
    Task getTask(int index) throws ErrorCodeException;
    Map lookAtTheMap(String location) throws ErrorCodeException;
    Visit visitHost(String ipPort, String ressource) throws ErrorCodeException;
    Answer post(String ipPort, String ressource, String body) throws ErrorCodeException;

    // TODO - Nicht task-uri sinder taskId
    List<Delivery> deliverTask(Task task) throws ErrorCodeException;

    Visit deliverTaskPart(TaskPart taskpart) throws ErrorCodeException;

}
