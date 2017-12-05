package app.layerLogicAndService.cmpQuest.service;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
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
    public Map getMap(String location) throws ErrorCodeException {

        return this.questConsumer.lookAtTheMap(location);
    }

    @Override
    public Visit visitLocationForTask(String location, int taskId) throws ErrorCodeException {

        Map map = this.getMap(location);

        Task task = this.getTask(taskId);

        Visit dto = this.questConsumer.visitHost(map.getHost(), task.getResource());

        Blackboard.getInstance().getUser().setCurrentQuesting(new Questing(task, map, task.getResource()));


        if (dto.getNext() != null) {
            Blackboard.getInstance().getUser().getCurrentQuesting().setNext(dto.getNext()); //

        }

        if (dto.getSteps_todo() != null) {

            List<Step> steps = new ArrayList<Step>();
            for (int i = 0; i < dto.getSteps_todo().size(); i++) {
                steps.add(new Step(dto.getSteps_todo().get(i)));
            }

            Blackboard.getInstance().getUser().getCurrentQuesting().setPart(new TaskPart(Blackboard.getInstance().getUser().getCurrentQuesting().getMap().getHost() + Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri(), steps));


        }

        System.out.println(Blackboard.getInstance().getUser().getCurrentQuesting().toString());

        return dto;
    }

    @Override
    public Visit next() throws ErrorCodeException {

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getNext() == null) {
            throw new IllegalArgumentException("no next");
        }

        Visit dto = this.questConsumer.visitHost(Blackboard.getInstance().getUser().getCurrentQuesting().getMap().getHost(), Blackboard.getInstance().getUser().getCurrentQuesting().getNext());
        Blackboard.getInstance().getUser().getCurrentQuesting().setCurrentUri(Blackboard.getInstance().getUser().getCurrentQuesting().getNext());

        if (dto.getNext() != null) {
            Blackboard.getInstance().getUser().getCurrentQuesting().setNext(dto.getNext());

        }

        if (dto.getSteps_todo() != null) {

            List<Step> steps = new ArrayList<Step>();
            for (int i = 0; i < dto.getSteps_todo().size(); i++) {
                steps.add(new Step(dto.getSteps_todo().get(i)));
            }

            Blackboard.getInstance().getUser().getCurrentQuesting().setPart(new TaskPart(Blackboard.getInstance().getUser().getCurrentQuesting().getMap().getHost() + Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri(), steps));
        }

        System.out.println(Blackboard.getInstance().getUser().getCurrentQuesting().toString());
        return dto;
    }

    @Override
    public Visit step(int step) throws ErrorCodeException {


        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart() == null) {
            throw new IllegalArgumentException("no part");
        }

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList() == null) {
            throw new IllegalArgumentException("no steps");
        }

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList().get(step - 1) == null) {
            throw new IllegalArgumentException("no step");
        }


        Visit dto = this.questConsumer.visitHost(Blackboard.getInstance().getUser().getCurrentQuesting().getMap().getHost(), Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList().get(step - 1).getUri());
        Blackboard.getInstance().getUser().getCurrentQuesting().setCurrentUri(Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList().get(step - 1).getUri());


        System.out.println(Blackboard.getInstance().getUser().getCurrentQuesting().toString());
        return dto;
    }

    @Override
    public Visit deliverTaskPart() throws ErrorCodeException {

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart() == null) {
            throw new IllegalArgumentException("no part to deliverTask");
        }

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList() == null) {
            throw new IllegalArgumentException("no steps to deliverTask");
        }

        for (int i = 0; i < Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList().size(); i++){

            if(Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList().get(i).getToken().getToken() == null){

                throw new IllegalArgumentException("a steptoken is missing");
            }
        }

        Visit visit = this.questConsumer.deliverTaskPart(Blackboard.getInstance().getUser().getCurrentQuesting().getPart());

        Blackboard.getInstance().getUser().getCurrentQuesting().setCurrentUri(Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getDeliverUri());

        Blackboard.getInstance().getUser().getCurrentQuesting().getTask().setToken(visit.getToken());


        return visit;
    }

    @Override
    public Answer answerToCurrentUri(String body) throws ErrorCodeException {

        if (Blackboard.getInstance().getUser().getCurrentQuesting() == null) {
            throw new IllegalArgumentException("no to answer task");
        }

        Answer answer = this.questConsumer.post(Blackboard.getInstance().getUser().getCurrentQuesting().getMap().getHost(), Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri(), body);


        if (answer.getToken() != null) {

            if(Blackboard.getInstance().getUser().getCurrentQuesting().getPart() != null){
                Step step = Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList().stream().filter(s -> s.getUri().equals(Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri())).findFirst().orElse(null);

                if (step != null) {

                    step.setToken(new Token(answer.getToken_name(), answer.getToken()));
                } else {

                    Blackboard.getInstance().getUser().getCurrentQuesting().getTask().setToken(answer.getToken());
                }

            } else {

                Blackboard.getInstance().getUser().getCurrentQuesting().getTask().setToken(answer.getToken());
            }

        }

        System.out.println(Blackboard.getInstance().getUser().getCurrentQuesting().toString());

        return answer;
    }

    @Override
    public List<Delivery> deliverTask() throws ErrorCodeException {


        if (Blackboard.getInstance().getUser().getCurrentQuesting().getTask().getToken() == null) {
            throw new IllegalArgumentException("no token at this point to deliverTask");
        }


        List<Delivery> list = this.questConsumer.deliverTask(Blackboard.getInstance().getUser().getCurrentQuesting().getTask());
        Blackboard.getInstance().getUser().getCurrentQuesting().setCurrentUri(Blackboard.getInstance().getUser().getCurrentQuesting().getTask().getResource());



        return list;
    }


}
