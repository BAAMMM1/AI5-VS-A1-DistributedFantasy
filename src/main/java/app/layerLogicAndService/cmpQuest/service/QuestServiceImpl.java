package app.layerLogicAndService.cmpQuest.service;

import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerLogicAndService.cmpQuest.entity.questing.Questing;
import app.layerLogicAndService.cmpQuest.entity.questing.Step;
import app.layerLogicAndService.cmpQuest.entity.questing.TaskPart;
import app.layerLogicAndService.cmpQuest.entity.questing.Token;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.IQuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.QuestConsumerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class QuestServiceImpl implements IQuestService {

    private IQuestConsumer questConsumer = new QuestConsumerImpl();

    private Questing currentQuesting;

    public QuestServiceImpl(IQuestConsumer questConsumer) {
        this.questConsumer = questConsumer;
    }

    @Override
    public List<Quest> getQuests() throws ErrorCodeException {

        return questConsumer.getQuests();
    }

    @Override
    public Quest getQuest(int index) throws ErrorCodeException {

        return this.questConsumer.getQuest(index);
    }

    @Override
    public Task getTask(int index) throws ErrorCodeException {

        return this.questConsumer.getTask(index);
    }

    @Override
    public Map lookAtTheMap(String location) throws ErrorCodeException {

        return this.questConsumer.lookAtTheMap(location);
    }

    @Override
    public Visit visitHost(String location, int taskId) throws ErrorCodeException {

        Map map = this.lookAtTheMap(location);

        Task task = this.getTask(taskId);

        Visit dto = this.questConsumer.visitHost(map.getHost(), task.getResource());

        this.currentQuesting = new Questing(task, map, task.getResource()); //


        if (dto.getNext() != null) {
            this.currentQuesting.setNext(dto.getNext()); //

        }

        if (dto.getSteps_todo() != null) {

            List<Step> steps = new ArrayList<Step>();
            for (int i = 0; i < dto.getSteps_todo().size(); i++) {
                steps.add(new Step(dto.getSteps_todo().get(i)));
            }

            this.currentQuesting.setPart(new TaskPart(this.currentQuesting.getMap().getHost() + this.currentQuesting.getCurrentUri(), steps));


        }

        System.out.println(this.currentQuesting.toString());

        return dto;
    }

    @Override
    public Visit next() throws ErrorCodeException {

        if (this.currentQuesting.getNext() == null) {
            throw new IllegalArgumentException("no next");
        }

        Visit dto = this.questConsumer.visitHost(this.currentQuesting.getMap().getHost(), this.currentQuesting.getNext());
        this.currentQuesting.setCurrentUri(this.currentQuesting.getNext());

        if (dto.getNext() != null) {
            this.currentQuesting.setNext(dto.getNext());

        }

        if (dto.getSteps_todo() != null) {

            List<Step> steps = new ArrayList<Step>();
            for (int i = 0; i < dto.getSteps_todo().size(); i++) {
                steps.add(new Step(dto.getSteps_todo().get(i)));
            }

            this.currentQuesting.setPart(new TaskPart(this.currentQuesting.getMap().getHost() + this.currentQuesting.getCurrentUri(), steps));
        }

        System.out.println(this.currentQuesting.toString());
        return dto;
    }

    @Override
    public Visit step(int step) throws ErrorCodeException {


        if (this.currentQuesting.getPart() == null) {
            throw new IllegalArgumentException("no part");
        }

        if (this.currentQuesting.getPart().getStepList() == null) {
            throw new IllegalArgumentException("no steps");
        }

        if (this.currentQuesting.getPart().getStepList().get(step - 1) == null) {
            throw new IllegalArgumentException("no step");
        }


        Visit dto = this.questConsumer.visitHost(this.currentQuesting.getMap().getHost(), this.currentQuesting.getPart().getStepList().get(step - 1).getUri());
        this.currentQuesting.setCurrentUri(this.currentQuesting.getPart().getStepList().get(step - 1).getUri());


        System.out.println(this.currentQuesting.toString());
        return dto;
    }

    @Override
    public Visit deliverStepToken() throws ErrorCodeException {

        if (this.currentQuesting.getPart() == null) {
            throw new IllegalArgumentException("no part to deliver");
        }

        if (this.currentQuesting.getPart().getStepList() == null) {
            throw new IllegalArgumentException("no steps to deliver");
        }

        for (int i = 0; i < this.currentQuesting.getPart().getStepList().size(); i++){

            if(this.currentQuesting.getPart().getStepList().get(i).getToken().getToken() == null){

                throw new IllegalArgumentException("a steptoken is missing");
            }
        }

        Visit visit = this.questConsumer.deliverTaskPart(this.currentQuesting.getPart());

        this.currentQuesting.setCurrentUri(this.currentQuesting.getPart().getDeliverUri());

        this.currentQuesting.getTask().setToken(visit.getToken());


        return visit;
    }

    @Override
    public Answer answerToCurrentUri(String body) throws ErrorCodeException {

        if (this.currentQuesting == null) {
            throw new IllegalArgumentException("no to answer task");
        }

        Answer answer = this.questConsumer.post(this.currentQuesting.getMap().getHost(), this.currentQuesting.getCurrentUri(), body);


        if (answer.getToken() != null) {

            if(this.currentQuesting.getPart() != null){
                Step step = this.currentQuesting.getPart().getStepList().stream().filter(s -> s.getUri().equals(this.currentQuesting.getCurrentUri())).findFirst().orElse(null);

                if (step != null) {

                    step.setToken(new Token(answer.getToken_name(), answer.getToken()));
                } else {

                    this.currentQuesting.getTask().setToken(answer.getToken());
                }

            } else {

                this.currentQuesting.getTask().setToken(answer.getToken());
            }


        }

        return answer;
    }

    @Override
    public List<Delivery> deliver() throws ErrorCodeException {


        if (this.currentQuesting.getTask().getToken() == null) {
            throw new IllegalArgumentException("no token at this point to deliver");
        }


        List<Delivery> list = this.questConsumer.deliverTask(this.currentQuesting.getTask());
        this.currentQuesting.setCurrentUri(this.currentQuesting.getTask().getResource());



        return list;
    }


}
