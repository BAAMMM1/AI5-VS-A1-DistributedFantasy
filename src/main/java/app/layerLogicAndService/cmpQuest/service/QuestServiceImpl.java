package app.layerLogicAndService.cmpQuest.service;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpQuest.entity.Quest;
import app.layerLogicAndService.cmpQuest.entity.Task;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.IQuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.QuestConsumerImpl;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.*;

/**
 * @author Chris on 19.11.2017
 */
public class QuestServiceImpl implements IQuestService {

    IQuestConsumer questConsumer = new QuestConsumerImpl();
    Task currentTask;

    public QuestServiceImpl(IQuestConsumer questConsumer) {
        this.questConsumer = questConsumer;
    }

    @Override
    public QuestsDTO getQuests() throws ErrorCodeException {

        // TODO - Do here something with the data
        QuestsDTO dto = questConsumer.getQuests();

        Blackboard.getInstance().getUser().setKnownQuests(dto.getObjects());

        System.out.println(Blackboard.getInstance().getUser().toString());

        return dto;
    }

    @Override
    public Quest getQuest(int index) throws ErrorCodeException {

        Quest knownQuest = Blackboard.getInstance().getUser()
                .getKnownQuests().stream()
                .filter(quest -> index == quest.getId())
                .findFirst()
                .orElse(null);

        if(knownQuest != null){

            System.out.println("quest vorhanden");

            System.out.println(Blackboard.getInstance().getUser().toString());

            return knownQuest;


        } else {
            System.out.println("quest nicht vorhanden");
            QuestDTO dto = this.questConsumer.getQuest(index);

            Quest newQuestDTO = dto.getObject();

            Blackboard.getInstance().getUser().addQuest(dto.getObject());

            System.out.println(Blackboard.getInstance().getUser().toString());

            return newQuestDTO;

        }



        /*
        QuestDTO dto = this.questConsumer.getQuest(index);

        return dto;
        */
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
    public VisitDTO visitHost(String location, int taskId) throws ErrorCodeException {

        MapDTO mapDto = this.questConsumer.lookAtTheMap(location);

        TaskDTO taskdto = this.questConsumer.getTask(taskId);

        VisitDTO dto = this.questConsumer.visitHost(mapDto.getObject().getHost(), taskdto.getObject().getResource());

        this.currentTask = taskdto.getObject();
        this.currentTask.setHost(mapDto.getObject().getHost());
        this.currentTask.setCurrentUri(taskdto.getObject().getResource());

        if(dto.getNext() != null){
            this.currentTask.setNext(dto.getNext());
        }

        if(dto.getSteps_todo() != null){
            this.currentTask.setSteps_todo(dto.getSteps_todo());
            this.currentTask.setStepTokensDeliver(this.currentTask.getCurrentUri());
        }


        // Beim Deliver schaue im Task zu welcher Quest er gehört. Hole die Quest, nehme den deliver link und poste

        return dto;
    }

    @Override
    public VisitDTO next() throws ErrorCodeException {

        if(this.currentTask.getNext() == null){
            throw  new IllegalArgumentException("no next");
        }

        VisitDTO dto = this.questConsumer.visitHost(this.currentTask.getHost(), this.currentTask.getNext());
        this.currentTask.setCurrentUri(this.currentTask.getNext());

        if(dto.getNext() != null){
            this.currentTask.setNext(dto.getNext());
        }

        if(dto.getSteps_todo() != null){
            this.currentTask.setSteps_todo(dto.getSteps_todo());
            this.currentTask.setStepTokensDeliver(this.currentTask.getCurrentUri());
        }

        return dto;
    }

    // TODO - für einen übergebenen Task
    @Override
    public VisitDTO step(int step) throws ErrorCodeException {

        if(this.currentTask.getSteps_todo().get(step-1) == null){
            throw  new IllegalArgumentException("no next");
        }

        VisitDTO dto = this.questConsumer.visitHost(this.currentTask.getHost(), this.currentTask.getSteps_todo().get(step-1));
        this.currentTask.setCurrentUri(this.currentTask.getSteps_todo().get(step-1));


        return dto;
    }

    @Override
    public DeliverDTO deliverStepToken() throws ErrorCodeException, ErrorDeliverCodeException {

        if(this.currentTask.getStep_tokens() == null){
            throw new IllegalArgumentException("no stepTokens at this point do deliver");
        }

        DeliverDTO dto = this.questConsumer.deliverStepToken(currentTask);


        return dto;
    }

    @Override
    public AnswerDTO post(String body) throws ErrorCodeException {

        if(currentTask == null){
            throw new  IllegalArgumentException("no to answer task");
        }

        AnswerDTO dto = this.questConsumer.post(this.currentTask.getHost(), this.currentTask.getCurrentUri(), body);

        // TODO - step Tokens?

        if(this.currentTask.getStep_tokens() != null){

        }

        if(dto.getToken() != null){

            if(this.currentTask.getSteps_todo() == null){
                this.currentTask.setToken(dto.getToken());
            } else {
                this.currentTask.addStepToken(dto.getToken());
            }


        }

        return dto;
    }

    // TODO - Von currentTask zu halten der Task und dann Deliver Taskid, check ob user den hält
    @Override
    public DeliverDTO deliver() throws ErrorCodeException, ErrorDeliverCodeException {

        if(this.currentTask.getToken() == null){
            throw new IllegalArgumentException("no token at this point do deliver");
        }


        DeliverDTO dto = this.questConsumer.deliver(currentTask);

        return dto;
    }


}
