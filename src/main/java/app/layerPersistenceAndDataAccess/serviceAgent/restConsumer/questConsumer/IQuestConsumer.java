package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer;

import app.layerLogicAndService.cmpQuest.entity.Task;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;

/**
 * @author Chris on 19.11.2017
 */
public interface IQuestConsumer {

    QuestsDTO getQuests() throws ErrorCodeException;
    QuestDTO getQuest(int index) throws ErrorCodeException;
    TaskDTO getTask(int index) throws ErrorCodeException;
    MapDTO lookAtTheMap(String location) throws ErrorCodeException;
    VisitDTO visitHost(String ipPort, String ressource) throws ErrorCodeException;
    AnswerDTO post(String ipPort, String ressource, String body) throws ErrorCodeException;

    // TODO - Nicht task-uri sinder taskId
    DeliverDTO deliver(Task task) throws ErrorCodeException, ErrorDeliverCodeException;

    DeliverDTO deliverStepToken(Task task) throws ErrorCodeException, ErrorDeliverCodeException;

}
