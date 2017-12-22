package app.layerLogicAndService.cmpService.service;


import app.Application;
import app.configuration.API;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.mutex.Mutex;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexMessage;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexMsg;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexState;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.exception.AlreadyInGroupException;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Diese Klasse ist aus der Sicht des Empfängers
 *
 * @author Chris on 03.12.2017
 */
@org.springframework.stereotype.Service
public class FromHeroService implements IFromHeroService {

    public final static Logger logger = Logger.getLogger(new Object() {
    }.getClass().getEnclosingClass());

    private static final String GROUP_DENIED = "I am already in a group!";
    public static final String ELECTION_IDENTIFIER = "ELECTION";
    public static final String PORT = ":8080";
    public static final String HTTP = "http://";

    private ITavernaService tavernaService = new TavernaService();

    private IToHeroConsumer toHeroConsumer = new ToHeroConsumer();

    @Override
    public Service getService() {

        // TODO IP und Port korrekt rausziehen
        Service service = new Service(
                Blackboard.getInstance().getUser().get_links().getSelf(),
                false,
                Blackboard.getInstance().getUser().getGroup(),
                HTTP + Application.IP + PORT + API.PATH_HIRINGS,
                HTTP + Application.IP + PORT + API.PATH_ASSIGNMENTS,
                HTTP + Application.IP + PORT + API.PATH_MESSAGES,
                HTTP + Application.IP + PORT + API.PATH_ELECTION,
                HTTP + Application.IP + PORT + API.PATH_MUTEX,
                HTTP + Application.IP + PORT + API.PATH_MUTEXSTATE);

        return service;
    }

    @Override
    public Mutex getMutexState(){
        return Blackboard.getInstance().getUser().getMutex();
    }

    @Override
    public void addHiring(Hiring hiring) throws AlreadyInGroupException, UnexpectedResponseCodeException {

        System.out.print("\n");
        System.out.println("attention!: you got an hiring");
        System.out.println("message: " + hiring.getMessage());

        if (Blackboard.getInstance().getUser().getGroup() != null) {
            System.out.println("hiring was declined automatically because you are already in a group");
            System.out.print(Context.getInstance().getPromptState());
            throw new AlreadyInGroupException(GROUP_DENIED);
        }

        // TODO - Was ist wenn hiring.getGroup() = http://xxxx:xx/taverna/groups/387 statt /taverna/groups/387
        int groupId;
        try {
            groupId = Integer.valueOf(hiring.getGroup().replace("/taverna/groups/", ""));
        } catch (Exception e) {
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

        System.out.print(Context.getInstance().getPromptState());


    }

    @Override
    public void addAssignment(Assignment assignment) {

        System.out.print("\n");
        System.out.println("attention!: you have got a assignment");
        System.out.println("execute an " + assignment.getMethod() + " for task: " + assignment.getTask() + " to the ressource: " + assignment.getResource());
        System.out.println("message: \n" + assignment.getMessage());
        System.out.println("use the command \"assignment\" to do your job");

        //Blackboard.getInstance().getUser().setAssignment(assignment);

        Blackboard.getInstance().getUser().getAssignmentList().add(assignment);

        System.out.print(Context.getInstance().getPromptState());

    }

    @Override
    public void addAssignmentDeliver(AssignmentDeliver assignmentDeliver) throws UnexpectedResponseCodeException {

        System.out.print("\n");
        System.out.println("attention!: a fellow has delivered his assignment");
        System.out.println("message: " + assignmentDeliver.getMessage());

        // TODO - AssignmentDeliver
        //Blackboard.getInstance().getUser().setAssignmentDeliver(assignmentDeliver);

        Blackboard.getInstance().getUser().addAssignmentDerliver(assignmentDeliver);

        if (assignmentDeliver.getData() != null) {
            Blackboard.getInstance().getUser().getCurrentQuesting().setRingToken(assignmentDeliver.getData());
            Blackboard.getInstance().getUser().getCurrentQuesting().getTask().setToken(assignmentDeliver.getData());

            // TODO - assignment ID für assignment job von einer election
            if(assignmentDeliver.getId().contains(ELECTION_IDENTIFIER)) {
                // TODO - schön machen
                List<Adventurer> groupMemberList = this.tavernaService.getGroupMembers(Blackboard.getInstance().getUser().getCurrentGroup().getId());

                for (Adventurer adventurer : groupMemberList) {

                    try {
                        // falls man selber der nächste ist, dann nicht an sich selber senden
                        if (adventurer.getUser().equals(Blackboard.getInstance().getUser().get_links().getSelf())) {
                            continue;
                        }

                        Service heroService = this.toHeroConsumer.getHeroService(adventurer.getUrl());

                        logger.info("sending election_state COORDINATOR to: " + adventurer.getUser() + " - " + heroService.getElection());

                        this.toHeroConsumer.sendElection(

                                heroService.getElection(),

                                new Election(
                                        API.ELECTION_ALGORTIHM,
                                        API.ELECTION_STATE_COORDINATOR,
                                        Blackboard.getInstance().getUser().get_links().getSelf(),
                                        null,
                                        "good job!"
                                ));

                    } catch (Exception e) {
                        logger.warn("unavailable: cant sendeing election_state COORDINATOR to " + adventurer.getUser().toString());
                        continue;

                    }

                }
            }

        }

        System.out.print(Context.getInstance().getPromptState());

    }

    @Override
    public void addMessage(Message message) {

        System.out.print("\n");
        System.out.println("attention!: you have got a message");
        System.out.println("type: " + message.getTyp());
        System.out.println("message: " + message.getMessage());

        Blackboard.getInstance().getUser().addMessage(message);

        System.out.print(Context.getInstance().getPromptState());

    }

    @Override
    public List<Message> getMessages() {

        List<Message> messages = Blackboard.getInstance().getUser().getMessages();
        //Blackboard.getInstance().getUser().removeMessage();

        return messages;
    }


    @Override
    public void addElection(Election election) throws UnexpectedResponseCodeException, NotInGroupException {

        System.out.print("\n");
        // TODO - Abfangen, wenn man noch nicht in der selen Gruppe ist
        if (Blackboard.getInstance().getUser().getCurrentGroup() == null) {
            logger.warn("current user is not in a group");
            throw new NotInGroupException("I am not in any group");
        }

        // Sicht des Empfänger der Election
        System.out.println("attention!: you have got a election");
        System.out.println("state: " + election.getPayload());
        System.out.println("from: " + election.getUser());
        System.out.println("message: " + election.getMessage());

        // 1. Um welche Election Stand handelt es sich? election || answer || coordinator
        if (election.getPayload().equals(API.ELECTION_STATE_ELECTION)) {

            // falls eigene ID größer, dann Antoworte mit answer und sende election an höhere Id's
            if (Blackboard.getInstance().getUser().get_links().getSelf().length() > election.getUser().length()) {
                logger.info("own id is grater than the election id");
                System.out.println("you have a greater Id, your chance to win the election");

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

                if (!Blackboard.getInstance().getUser().isElectionWinFlag()) {

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

        if (election.getPayload().equals(API.ELECTION_STATE_ANSWER)) {
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

        if (election.getPayload().equals(API.ELECTION_STATE_COORDINATOR)) {
            System.out.println("winner of the election: " + election.getUser());
            logger.info("set the winner of the election to: " + election.getUser().replace("/users/", ""));
            Blackboard.getInstance().getUser().getCurrentGroup().setCoordinator(election.getUser().replace("/users/", ""));

        }

        System.out.print(Context.getInstance().getPromptState());

    }

    @Override
    public void addMutexMessage(MutexMessage request) throws UnexpectedResponseCodeException {

        if(true){
            throw new IllegalArgumentException("not implemented yet");
        }

        // TODO - Mutex hinterlegen wenn ...

        Mutex currentMutex = Blackboard.getInstance().getUser().getMutex();

        currentMutex.incrementTime();

        String mutexState = currentMutex.getState();
        int time = currentMutex.getTime();

        MutexMessage response = null;

        if(mutexState.equals(MutexState.RELEASED.toString())){
            response = new MutexMessage(
                    MutexMsg.REPLYOK.toString(),
                    time,
                    HTTP + Application.IP + PORT + API.PATH_MUTEX_REPLY,
                    HTTP + Application.IP + PORT + API.PATH_SERVICES
            );

        } else if(mutexState.equals(MutexState.WANTING.toString())){

            if(request.getTime() < time){
                response = new MutexMessage(
                        MutexMsg.REPLYOK.toString(),
                        time,
                        HTTP + Application.IP + PORT + API.PATH_MUTEX_REPLY,
                        HTTP + Application.IP + PORT + API.PATH_SERVICES
                );

            } else if (request.getTime() == time){

                //if()



            }

        } else {

        }

        this.toHeroConsumer.sendMutexMessage(request.getReply(), response);

    }

}
