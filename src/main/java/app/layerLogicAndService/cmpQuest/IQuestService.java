package app.layerLogicAndService.cmpQuest;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
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
    // TODO - 11
    AnswerDTO post(String ip, int port, String ressource, String body) throws ErrorCodeException;
    // TODO - 12
    DeliverDTO deliver(int questId, String taskUri, String token) throws ErrorCodeException;

}
