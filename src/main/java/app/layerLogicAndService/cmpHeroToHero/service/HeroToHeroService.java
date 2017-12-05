package app.layerLogicAndService.cmpHeroToHero.service;

import app.Application;
import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpHero.entity.*;
import app.layerLogicAndService.cmpQuest.entity.Quest;
import app.layerLogicAndService.cmpQuest.entity.Task;
import app.layerLogicAndService.cmpQuest.entity.questing.Step;
import app.layerLogicAndService.cmpQuest.entity.questing.TaskPart;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer.IHeroToHeroConsumer;

import java.util.Random;

/**
 * @author Chris on 02.12.2017
 */
public class HeroToHeroService implements IHeroToHeroService {

    IHeroToHeroConsumer heroToHeroConsumer;

    ITavernaService tavernaService;

    IQuestService questService;

    public HeroToHeroService(IHeroToHeroConsumer heroToHeroConsumer, ITavernaService tavernaService, IQuestService questService) {
        this.heroToHeroConsumer = heroToHeroConsumer;
        this.tavernaService = tavernaService;
        this.questService = questService;
    }

    @Override
    public String invite(String heroName, int groupId, int taskid, String messageToHero) throws ErrorCodeException {

        // TODO - Benutzer über invite benachrichten

        Adventurer adventurer = this.tavernaService.getAdventure(heroName);

        // TODO - Wenn Grouß nicht da, dann besser response als 404 - Not Found
        Group group = this.tavernaService.getGroup(groupId);

        Task task = this.questService.getTask(taskid);

        String heroServiceUrl = adventurer.getUrl();

        if(!heroServiceUrl.substring(0,7).equals("http://")){
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        System.out.println(heroServiceUrl);

        Service heroService = this.heroToHeroConsumer.getHeroService(heroServiceUrl);

        String heroHiringUrl = heroService.getHirings();

        if(!heroHiringUrl.substring(0,7).equals("http://")){
            heroHiringUrl = "http://" + heroHiringUrl;
        }

        // TODO - Ist das Hiring hier mit den richtigen Daten befüllt?
        Hiring hiring = new Hiring(group.get_links().getSelf(), task.getName(),  messageToHero);

        return this.heroToHeroConsumer.hiringHero(hiring, heroHiringUrl);
    }

    @Override
    public void sendMessage(String adventurer, String string) throws ErrorCodeException {

        // TODO - Benutzer über Message income benachrichtigen

        Adventurer adven = this.tavernaService.getAdventure(adventurer);

        String heroServiceUrl = adven.getUrl();

        if(!heroServiceUrl.substring(0,7).equals("http://")){
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        Service heroService = this.heroToHeroConsumer.getHeroService(heroServiceUrl);

        String heroMessageUrl = heroService.getMessages();

        if(!heroMessageUrl.substring(0,7).equals("http://")){
            heroMessageUrl = "http://" + heroMessageUrl;
        }

        // TODO - User über fehlgeschlagene Zustellung informieren

        // TODO - User übererfolgreiche zustellung informieren
        Message message = new Message(string, "", "message");

        this.heroToHeroConsumer.sendMessage(message, heroMessageUrl);
    }

    @Override
    public void sendAssignment(String adventurer, String message) throws ErrorCodeException {

        /*
        if(Blackboard.getInstance().getUser().getCurrentGroup().getOwner()== null){
            throw new IllegalArgumentException("no group");
        }

        if(Blackboard.getInstance().getUser().getCurrentGroup().getOwner() != Blackboard.getInstance().getUser().getName()){
            throw new IllegalArgumentException("no group");
        }
        */

        Adventurer adven = this.tavernaService.getAdventure(adventurer);

        String heroServiceUrl = adven.getUrl();

        if(!heroServiceUrl.substring(0,7).equals("http://")){
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        Service heroService = this.heroToHeroConsumer.getHeroService(heroServiceUrl);

        String heroAssignmentUrl = heroService.getAssignments();

        if(!heroAssignmentUrl.substring(0,7).equals("http://")){
            heroAssignmentUrl = "http://" + heroAssignmentUrl;
        }

        Assignment assignment = new Assignment(
                Assignment.counter,
                Blackboard.getInstance().getUser().getCurrentQuesting().getTask().get_links().getSelf(),
                Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri(),
                "method",
                "data",
                "http://" + Application.IP + ":8080/assignments/deliveries",
                message
                );

        this.heroToHeroConsumer.sendAssignment(heroAssignmentUrl, assignment);

    }

    @Override
    public void sendAssignmentDeliver() throws ErrorCodeException {

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

        TaskPart part = Blackboard.getInstance().getUser().getCurrentQuesting().getPart();

        String data = "";
        for (Step step : part.getStepList()) {
            String token = "\""+ step.getToken().getToken() +"\", ";
            data = data + token;
        }

        data = data.substring(0, data.length()-2);

        AssignmentDerliver assignmentDerliver = new AssignmentDerliver(
                Blackboard.getInstance().getUser().getAssignment().getId(),
                Blackboard.getInstance().getUser().getAssignment().getTask(),
                Blackboard.getInstance().getUser().getAssignment().getResource(),
                Blackboard.getInstance().getUser().getAssignment().getMethod(),
                data,
                Blackboard.getInstance().getUser().get_links().getSelf(),
                "message");

        System.out.println(data);

        heroToHeroConsumer.sendAssignmentDeliver(Blackboard.getInstance().getUser().getAssignment().getCallback(), assignmentDerliver);

    }


}
