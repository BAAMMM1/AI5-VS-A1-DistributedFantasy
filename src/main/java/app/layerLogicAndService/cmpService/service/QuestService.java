package app.layerLogicAndService.cmpService.service;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.Assignment;
import app.layerLogicAndService.cmpService.entity.hero.AssignmentDerliver;
import app.layerLogicAndService.cmpService.entity.quest.*;
import app.layerLogicAndService.cmpService.entity.quest.questing.Questing;
import app.layerLogicAndService.cmpService.entity.quest.questing.Step;
import app.layerLogicAndService.cmpService.entity.quest.questing.TaskPart;
import app.layerLogicAndService.cmpService.entity.quest.questing.Token;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.QuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IQuestConsumer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class QuestService implements IQuestService {

    public final static Logger logger = Logger.getLogger(new Object() {
    }.getClass().getEnclosingClass());

    private IQuestConsumer questConsumer = new QuestConsumer();

    private IToHeroConsumer toHeroConsumer = new ToHeroConsumer();

    @Override
    public List<Quest> getQuests() throws UnexpectedResponseCodeException {

        return questConsumer.getQuests();
    }

    @Override
    public Quest getQuest(int index) throws UnexpectedResponseCodeException {

        return this.questConsumer.getQuest(index);
    }

    @Override
    public Task getTask(int index) throws UnexpectedResponseCodeException {

        return this.questConsumer.getTask(index);
    }

    @Override
    public Map getMap(String location) throws UnexpectedResponseCodeException {

        return this.questConsumer.lookAtTheMap(location);
    }

    @Override
    public Visit visitLocationForTask(String location, int taskId) throws UnexpectedResponseCodeException {

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

        logger.info(Blackboard.getInstance().getUser().getCurrentQuesting().toString());

        return dto;
    }

    @Override
    public Visit next() throws UnexpectedResponseCodeException {

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

        logger.info(Blackboard.getInstance().getUser().getCurrentQuesting().toString());
        return dto;
    }

    @Override
    public Visit step(int step) throws UnexpectedResponseCodeException {


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


        logger.info(Blackboard.getInstance().getUser().getCurrentQuesting().toString());
        return dto;
    }

    @Override
    public Visit deliverTaskPart() throws UnexpectedResponseCodeException {

        /*
        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart() == null) {
            throw new IllegalArgumentException("no part to deliverTask");
        }

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList() == null) {
            throw new IllegalArgumentException("no steps to deliverTask");
        }
        */

        if(Blackboard.getInstance().getUser().getSendetAssignmentList().size() != Blackboard.getInstance().getUser().getAssignmentDerliverList().size()){
            throw new IllegalArgumentException("not all sendet assignments delivered at this time");
        }
        // TODO - Auf korrekte ids prüfen


        /*
        // Prüfen ob alle Assignments abgeliefert
        if(Blackboard.getInstance().getUser().getSendetAssignment() != null){

            if(Blackboard.getInstance().getUser().getAssignmentDerliver() == null){
                throw new IllegalArgumentException("assignment not delivered");
            } else {
                if(Blackboard.getInstance().getUser().getAssignmentDerliver().getId() != Blackboard.getInstance().getUser().getAssignmentDerliver().getId()){
                    throw new IllegalArgumentException("assignment id != assignmentdeliver id");
                }
            }


        }
        */



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
    public Visit doAssignment() throws UnexpectedResponseCodeException {

        if(Blackboard.getInstance().getUser().getAssignmentList().size() == 0){
            throw new IllegalArgumentException("no assignment at the moment");
        }

        /*
        if(Blackboard.getInstance().getUser().getAssignment() == null){
            throw new IllegalArgumentException("no assignment at the moment");
        }

        Assignment assignment = Blackboard.getInstance().getUser().getAssignment();
        */

        Assignment assignment = Blackboard.getInstance().getUser().getAssignmentList().get(0);

        int taskid = Integer.valueOf(assignment.getTask().replace("/blackboard/tasks/", ""));

        Task task = this.getTask(taskid);

        Map map = this.getMap(task.getLocation().replace("/map/", ""));


        Visit dto = this.questConsumer.visitHost(map.getHost(), assignment.getResource());

        Blackboard.getInstance().getUser().setCurrentQuesting(new Questing(task, map, assignment.getResource()));

        // TODO - raus ziehen
        System.out.print("\n");
        System.out.println(dto.getMessage());
        System.out.print("\n");
        System.out.println("required_players: " + dto.getRequired_players());
        System.out.println("required_tokens: " + dto.getRequired_tokens());
        System.out.print("\n");

        if (dto.getNext() != null) {
            System.out.println("next: " + dto.getNext());
        }

        if (dto.getSteps_todo() != null) {
            System.out.println("steps_todo: " + dto.getSteps_todo());
        }

        if (dto.getToken_name() != null) {
            System.out.println("token_name: " + dto.getToken_name());
        }



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

        logger.info(Blackboard.getInstance().getUser().getCurrentQuesting().toString());

        if(assignment.getData() != null){
            if (assignment.getMethod().equals("POST")){

                List<String> strings = this.questConsumer.postData(Blackboard.getInstance().getUser().getCurrentQuesting().getMap().getHost(), Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri(), assignment.getData());

                AssignmentDerliver deliver = new AssignmentDerliver(assignment.getId(),
                        assignment.getTask(),
                        assignment.getResource(),
                        assignment.getMethod(),
                        strings.get(0),
                        Blackboard.getInstance().getUser().get_links().getSelf(),
                        strings.get(1) + "\n" + strings.get(2));

                System.out.print(strings.get(1)+"\n");
                System.out.print(strings.get(2)+"\n");

                this.toHeroConsumer.sendAssignmentDeliver(assignment.getCallback(), deliver);

                // TODO - raus ziehen

            }
        }

        Blackboard.getInstance().getUser().getAssignmentList().remove(0);

        return dto;
    }


    @Override
    public Answer answerToCurrentUri(String body) throws UnexpectedResponseCodeException {

        if (Blackboard.getInstance().getUser().getCurrentQuesting() == null) {
            throw new IllegalArgumentException("no to answer task");
        }

        Answer answer = this.questConsumer.answer(Blackboard.getInstance().getUser().getCurrentQuesting().getMap().getHost(), Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri(), body);


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

        logger.info(Blackboard.getInstance().getUser().getCurrentQuesting().toString());

        return answer;
    }

    @Override
    public List<Delivery> deliverTask() throws UnexpectedResponseCodeException {


        if (Blackboard.getInstance().getUser().getCurrentQuesting().getTask().getToken() == null) {
            throw new IllegalArgumentException("no token at this point to deliverTask");
        }


        List<Delivery> list = this.questConsumer.deliverTask(Blackboard.getInstance().getUser().getCurrentQuesting().getTask());
        Blackboard.getInstance().getUser().getCurrentQuesting().setCurrentUri(Blackboard.getInstance().getUser().getCurrentQuesting().getTask().getResource());



        return list;
    }


}
