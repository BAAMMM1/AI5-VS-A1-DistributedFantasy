package app.layerLogicAndService.cmpQuest.service;

import app.layerLogicAndService.cmpQuest.entity.Quest;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.*;

/**
 * @author Chris on 19.11.2017
 */
public interface IQuestService {

    QuestsDTO getQuests() throws ErrorCodeException;
    Quest getQuest(int index) throws ErrorCodeException;
    TaskDTO getTask(int index) throws ErrorCodeException;
    MapDTO lookAtTheMap(String location) throws ErrorCodeException;
    VisitDTO visitHost(String location, int taskId) throws ErrorCodeException;
    AnswerDTO post(String body) throws ErrorCodeException;
    DeliverDTO deliver() throws ErrorCodeException, ErrorDeliverCodeException;


    // TODO - Im moment für current, später für übergebenen Task
    VisitDTO next() throws ErrorCodeException;
    VisitDTO step(int step) throws ErrorCodeException;

    DeliverDTO deliverStepToken() throws ErrorCodeException, ErrorDeliverCodeException;

}
