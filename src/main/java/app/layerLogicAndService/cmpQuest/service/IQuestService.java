package app.layerLogicAndService.cmpQuest.service;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.*;

/**
 * @author Chris on 19.11.2017
 */
public interface IQuestService {

    QuestsDTO getQuests() throws ErrorCodeException;
    QuestDTO getQuest(int index) throws ErrorCodeException;
    TaskDTO getTask(int index) throws ErrorCodeException;
    MapDTO lookAtTheMap(String location) throws ErrorCodeException;
    VisitDTO visitHost(String ip, int port, String ressource) throws ErrorCodeException;
    AnswerDTO post(String ip, int port, String ressource, String body) throws ErrorCodeException;
    DeliverDTO deliver(int questId, String taskUri, String token) throws ErrorCodeException, ErrorDeliverCodeException;

}
