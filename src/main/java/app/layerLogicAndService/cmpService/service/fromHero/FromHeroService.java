package app.layerLogicAndService.cmpService.service.fromHero;


import app.Application;
import app.configuration.API;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.service.taverna.ITavernaService;
import app.layerLogicAndService.cmpService.service.taverna.TavernaService;
import app.layerLogicAndService.cmpService.service.toHero.IToHeroService;
import app.layerLogicAndService.cmpService.service.toHero.ToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.util.List;

/**
 * Diese Klasse ist aus der Sicht des Empfängers
 * @author Chris on 03.12.2017
 */
@org.springframework.stereotype.Service
public class FromHeroService implements IFromHeroService {

    private ITavernaService tavernaService = new TavernaService();

    private IToHeroService toHeroService =new ToHeroService();

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
        if(election.getPayload().equals("election")){

        }

        if(election.getPayload().equals("answer")){

        }




        return null;
    }
}
