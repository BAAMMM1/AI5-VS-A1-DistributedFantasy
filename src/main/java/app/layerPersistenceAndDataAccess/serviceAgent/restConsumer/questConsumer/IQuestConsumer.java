package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer;

import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerLogicAndService.cmpQuest.entity.questing.TaskPart;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;

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
    DeliverTaskDTO deliverTask(Task task) throws ErrorCodeException, ErrorDeliverCodeException;

    DeliverTaskPartDTO deliverTaskPart(TaskPart taskpart) throws ErrorCodeException, ErrorDeliverCodeException;

}
