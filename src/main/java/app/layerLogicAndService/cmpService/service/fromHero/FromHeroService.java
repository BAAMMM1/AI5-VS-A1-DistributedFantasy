package app.layerLogicAndService.cmpService.service.fromHero;


import app.Application;
import app.configuration.API;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.service.taverna.ITavernaService;
import app.layerLogicAndService.cmpService.service.taverna.TavernaService;
import app.layerLogicAndService.cmpService.service.toHero.IToHeroService;
import app.layerLogicAndService.cmpService.service.toHero.ToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.util.List;

/**
 * Diese Klasse ist aus der Sicht des Empfängers
 * @author Chris on 03.12.2017
 */
@org.springframework.stereotype.Service
public class FromHeroService implements IFromHeroService {

    private ITavernaService tavernaService = new TavernaService();

    private IToHeroService toHeroService = new ToHeroService();

    private IToHeroConsumer toHeroConsumer = new ToHeroConsumer();

    @Override
    public Service getService() {

        // TODO IP und Port korrekt rausziehen
        Service service = new Service(
                Blackboard.getInstance().getUser().get_links().getSelf(),
                false,
                Blackboard.getInstance().getUser().getGroup(),
                "http://" + Application.IP + ":8080" + API.PATH_HIRINGS,
                "http://" + Application.IP + ":8080" + API.PATH_ASSIGNMENTS,
                "http://" + Application.IP + ":8080" + API.PATH_MESSAGES);

        return service;
    }

    @Override
    public void addHiring(Hiring hiring) throws AlreadyInGroupException, UnexpectedResponseCodeException {

        if(Blackboard.getInstance().getUser().getGroup() != null){
            throw new AlreadyInGroupException("ehhmm ohh ehmm Nein! oder vlt ehm Nein! Ne doch nicht Nein! Ne Nein!");
        }

        // TODO - Was ist wenn hiring.getGroup() = http://xxxx:xx/taverna/groups/387 statt /taverna/groups/387
        int groupId;
        try{
            groupId = Integer.valueOf(hiring.getGroup().replace("/taverna/groups/", ""));
        } catch (Exception e){
            throw new IllegalArgumentException("group link incorrect");
        }

        // Testen ob es die Group auch gibt, ansonsten throw exception
        Group group = this.tavernaService.getGroup(groupId);

        // Wenn es sie gibt, beitreten
        // in die Groupe eintretren in der taverna
        this.tavernaService.enterGroup(groupId);

        Blackboard.getInstance().getUser().setGroup(hiring.getGroup());


    }

    @Override
    public void addAssignment(Assignment assignment) {

        Blackboard.getInstance().getUser().setAssignment(assignment);
        System.out.println("adding assignment");

    }

    @Override
    public void addAssignmentDeliver(AssignmentDerliver assignmentDerliver) {

        Blackboard.getInstance().getUser().setAssignmentDerliver(assignmentDerliver);
        System.out.println("adding assignmentdeliver");

    }

    @Override
    public void addMessage(Message message) {

        Blackboard.getInstance().getUser().addMessage(message);
        System.out.println("adding message");

    }

    @Override
    public List<Message> getMessages() {

        List<Message> messages = Blackboard.getInstance().getUser().getMessages();
        Blackboard.getInstance().getUser().removeMessage();

        return messages;
    }


    @Override
    public Election addElection(Election election) throws UnexpectedResponseCodeException {
        // Sicht des Empfänger der Election

        // 1. Um welche Election Stand handelt es sich? election || answer || coordinator
        if(election.getPayload().equals(API.ELECTION_STATE_ELECTION)){

            // falls eigene ID größer, dann Antoworte mit answer und sende election an höhere Id's
            if(election.getUser().length() > Blackboard.getInstance().getUser().get_links().getSelf().length()){
                toHeroConsumer.sendElection(

                        "TODO-URL",

                        new Election(
                                API.ELECTION_ALGORTIHM,
                                API.ELECTION_STATE_ANSWER,
                                Blackboard.getInstance().getUser().get_links().getSelf(),
                                null,
                                "message"
                        ));
            }

            // falls eigene ID kleiner, nicht mehr an der Wahl beteilen, der Prozess hat die Wahl verloren
            this.waitForCoordinatorMessage();

        }

        if(election.getPayload().equals(API.ELECTION_STATE_ANSWER)){
            /*
            Wenn eine answer-Nachricht kommt, verliert P unbeteiligt sich nicht weiter an der Wahl
            und wartet auf eine coordinator-Nachricht
             */
            /*
            Falls wir eine(mehrer) election nachricht gesendet haben
            und hier eine antwort bekommen, ist die election für uns verloren -> als flag hinterlegen
            flag-answer erhalten true!

             */

            // TODO - waitForCoordinator;
            this.waitForCoordinatorMessage();

        }

        if(election.getPayload().equals(API.ELECTIOn_STATE_COORDINATOR)){
            // TODO - Setze neuen Coordinator

        }

        return null;
    }

    private void waitForCoordinatorMessage(){
        // TODO - waitForCoordinator;

    }
}
