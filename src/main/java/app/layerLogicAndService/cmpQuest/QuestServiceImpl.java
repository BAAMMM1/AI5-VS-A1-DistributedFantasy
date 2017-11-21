package app.layerLogicAndService.cmpQuest;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.IQuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.QuestConsumerImpl;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.*;

/**
 * @author Chris on 19.11.2017
 */
public class QuestServiceImpl implements IQuestService {

    IQuestConsumer questConsumer = new QuestConsumerImpl();

    public QuestServiceImpl(IQuestConsumer questConsumer) {
        this.questConsumer = questConsumer;
    }

    @Override
    public QuestsDTO getQuests() throws ErrorCodeException {

        // TODO - Do here something with the data
        QuestsDTO dto = questConsumer.getQuests();

        return dto;
    }

    @Override
    public QuestDTO getQuest(int index) throws ErrorCodeException {

        QuestDTO dto = this.questConsumer.getQuest(index);

        return dto;
    }

    @Override
    public TaskDTO getTask(int index) throws ErrorCodeException {

        TaskDTO dto = this.questConsumer.getTask(index);

        return dto;
    }

    @Override
    public MapDTO lookAtTheMap(String location) throws ErrorCodeException {

        MapDTO dto = this.questConsumer.lookAtTheMap(location);

        return dto;
    }

    @Override
    public VisitDTO visitHost(String ip, int port, String ressource) throws ErrorCodeException {

        VisitDTO dto = this.questConsumer.visitHost(ip, port, ressource);

        return dto;
    }

    @Override
    public AnswerDTO post(String ip, int port, String ressource, String body) throws ErrorCodeException {

        AnswerDTO dto = this.questConsumer.post(ip, port, ressource, body);

        return dto;
    }

    @Override
    public DeliverDTO deliver(int questId, String taskUri, String token) throws ErrorCodeException {

        DeliverDTO dto = this.questConsumer.deliver(questId, taskUri, token);

        return dto;
    }
}
