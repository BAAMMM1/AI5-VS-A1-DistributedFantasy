package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;


import app.layerLogicAndService.cmpService.entity.quest.*;
import app.layerLogicAndService.cmpService.entity.quest.questing.TaskPart;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

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
    Answer answer(String ipPort, String ressource, String body) throws UnexpectedResponseCodeException;

    // TODO - Nicht task-uri sinder taskId
    List<Delivery> deliverTask(Task task) throws UnexpectedResponseCodeException;

    Visit deliverTaskPart(TaskPart taskpart) throws UnexpectedResponseCodeException;

    String postData(String ipPort, String ressource, String body) throws UnexpectedResponseCodeException;

}
