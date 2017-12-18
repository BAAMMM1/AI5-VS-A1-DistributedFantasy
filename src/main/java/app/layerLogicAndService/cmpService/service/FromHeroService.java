package app.layerLogicAndService.cmpService.service;


import app.Application;
import app.configuration.API;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ITavernaConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.TavernaConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Diese Klasse ist aus der Sicht des Empfängers
 * @author Chris on 03.12.2017
 */
@org.springframework.stereotype.Service
public class FromHeroService implements IFromHeroService {

    public final static Logger logger = Logger.getLogger(new Object() { }.getClass().getEnclosingClass());

    private static final String GROUP_DENIED = "I am already in a group!";

    private ITavernaService tavernaService = new TavernaService();

    private IToHeroService toHeroService = new ToHeroService();

    private IToHeroConsumer toHeroConsumer = new ToHeroConsumer();

    private ITavernaConsumer tavernaConsumer = new TavernaConsumer();

    @Override
    public Service getService() {

        // TODO IP und Port korrekt rausziehen
        Service service = new Service(
                Blackboard.getInstance().getUser().get_links().getSelf(),
                false,
                Blackboard.getInstance().getUser().getGroup(),
                "http://" + Application.IP + ":8080" + API.PATH_HIRINGS,
                "http://" + Application.IP + ":8080" + API.PATH_ASSIGNMENTS,
                "http://" + Application.IP + ":8080" + API.PATH_MESSAGES,
                "http://" + Application.IP + ":8080" + API.PATH_ELECTION,
                "http://" + Application.IP + ":8080" + API.PATH_MUTEX,
                "http://" + Application.IP + ":8080" + API.PATH_MUTEXSTATE);

        return service;
    }

    @Override
    public void addHiring(Hiring hiring) throws AlreadyInGroupException, UnexpectedResponseCodeException {

        System.out.print("attention!: you got an hiring");
        System.out.print("message: " + hiring.getMessage());

        if(Blackboard.getInstance().getUser().getGroup() != null){
            System.out.println("hiring was declined automatically because you are already in a group");
            throw new AlreadyInGroupException(GROUP_DENIED);
        }

        // TODO - Was ist wenn hiring.getGroup() = http://xxxx:xx/taverna/groups/387 statt /taverna/groups/387
        int groupId;
        try{
            groupId = Integer.valueOf(hiring.getGroup().replace("/taverna/groups/", ""));
        } catch (Exception e){
            System.out.println("hiring was declined automatically because the group id is incorrect");
            throw new IllegalArgumentException("group link incorrect");
        }

        // Testen ob es die Group auch gibt, ansonsten throw exception
        Group group = this.tavernaService.getGroup(groupId);

        // Wenn es sie gibt, beitreten
        // in die Groupe eintretren in der taverna
        System.out.println("you accepted the hiring automatically");
        this.tavernaService.enterGroup(groupId);

        Blackboard.getInstance().getUser().setGroup(hiring.getGroup());


    }

    @Override
    public void addAssignment(Assignment assignment) {

        System.out.println("attention!: you have got a assignment");
        System.out.println("the coordinator selected you to execute an " + assignment.getMethod() + " for task: " + assignment.getTask());
        System.out.println("messages: " + assignment.getMessage());
        System.out.println("use the command \"assignment\" to do your job");

        Blackboard.getInstance().getUser().setAssignment(assignment);

    }

    @Override
    public void addAssignmentDeliver(AssignmentDerliver assignmentDerliver) {

        System.out.println("attention!: a fellow has delivered his assignment");
        System.out.println("messages: " + assignmentDerliver.getMessage());

        Blackboard.getInstance().getUser().setAssignmentDerliver(assignmentDerliver);

    }

    @Override
    public void addMessage(Message message) {

        System.out.println("attention!: you have got a messages");
        System.out.println("type: " + message.getTyp());
        System.out.println("messages: " + message.getMessage());

        Blackboard.getInstance().getUser().addMessage(message);

    }

    @Override
    public List<Message> getMessages() {

        List<Message> messages = Blackboard.getInstance().getUser().getMessages();
        //Blackboard.getInstance().getUser().removeMessage();

        return messages;
    }


    @Override
    public void addElection(Election election) throws UnexpectedResponseCodeException, NotInGroupException {

        // TODO - Abfangen, wenn man noch nicht in der selen Gruppe ist
        if(Blackboard.getInstance().getUser().getCurrentGroup() == null){
            logger.warn("current user is not in a group");
            throw new NotInGroupException("I am not in any group");
        }

        // Sicht des Empfänger der Election
        System.out.println("attention!: you have got a election");
        System.out.println("state: " + election.getPayload());

        // 1. Um welche Election Stand handelt es sich? election || answer || coordinator
        if(election.getPayload().equals(API.ELECTION_STATE_ELECTION)){

            // falls eigene ID größer, dann Antoworte mit answer und sende election an höhere Id's
            if( Blackboard.getInstance().getUser().get_links().getSelf().length() > election.getUser().length()){
                logger.info("own id is grater than the election id");
                System.out.println("you have a grater Id, your change to win the election");

                Adventurer adventurer = this.tavernaService.getAdventure(election.getUser().replaceAll("/users/", ""));

                Service adventurerService = this.toHeroConsumer.getHeroService(adventurer.getUrl());

                logger.info("sending election_state ANSWER to: " + adventurer.getUser() + " - " + adventurerService.getElection());

                toHeroConsumer.sendElection(

                        adventurerService.getElection(),

                        new Election(
                                API.ELECTION_ALGORTIHM,
                                API.ELECTION_STATE_ANSWER,
                                Blackboard.getInstance().getUser().get_links().getSelf(),
                                null,
                                "You will never be the coordinator!"
                        ));

                if(!Blackboard.getInstance().getUser().isElectionWinFlag()){

                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        IToHeroService toHeroService = new ToHeroService();

                        try {

                                toHeroService.startElection(election.getJob());

                        } catch (UnexpectedResponseCodeException e) {
                            e.printStackTrace();
                        } catch (NotInGroupException e) {
                            e.printStackTrace();
                        }

                    }
                });

                t.start();
                }

                //this.toHeroService.startElection();

            } else {
                logger.info("own id is lower than the election id");
                System.out.println("your Id is smaller, you lose the election");
                Blackboard.getInstance().getUser().setElectionWinFlag(false);

                // falls eigene ID kleiner, nicht mehr an der Wahl beteilen, der Prozess hat die Wahl verloren


            }


        }

        if(election.getPayload().equals(API.ELECTION_STATE_ANSWER)){
            System.out.println("you got a election answer, you lose the election");
            logger.info("set electionWinFlag: false");
            Blackboard.getInstance().getUser().setElectionWinFlag(false);

            /*
            Wenn eine answer-Nachricht kommt, verliert P unbeteiligt sich nicht weiter an der Wahl
            und wartet auf eine coordinator-Nachricht
             */
            /*
            Falls wir eine(mehrer) election nachricht gesendet haben
            und hier eine antwort bekommen, ist die election für uns verloren -> als flag hinterlegen
            flag-answer erhalten true!

             */

        }

        if(election.getPayload().equals(API.ELECTION_STATE_COORDINATOR)){
            System.out.println("winner of the election: " + election.getUser());
            logger.info("set the winner of the election to: " + election.getUser().replace("/users/", ""));
            Blackboard.getInstance().getUser().getCurrentGroup().setCoordinator(election.getUser().replace("/users/", ""));

        }

    }

}