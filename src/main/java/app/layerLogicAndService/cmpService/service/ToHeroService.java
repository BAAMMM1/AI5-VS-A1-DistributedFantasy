package app.layerLogicAndService.cmpService.service;

import app.Application;
import app.configuration.API;
import app.layerLogicAndService.cmpService.algorithm.DistributedMutex;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.mutex.*;
import app.layerLogicAndService.cmpService.entity.quest.Task;
import app.layerLogicAndService.cmpService.entity.quest.questing.Step;
import app.layerLogicAndService.cmpService.entity.quest.questing.TaskPart;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IQuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.QuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IToHeroConsumer;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Chris on 02.12.2017
 */
public class ToHeroService implements IToHeroService {

    public final static Logger logger = Logger.getLogger(new Object() {
    }.getClass().getEnclosingClass());

    public static final String PORT = ":8080";
    public static final String HTTP = "http://";
    public static final int SLEEP_TIME = 5000;

    IToHeroConsumer toHeroConsumer = new ToHeroConsumer();

    ITavernaService tavernaService = new TavernaService();

    IFromHeroService fromHeroService = new FromHeroService();

    IQuestConsumer questConsumer = new QuestConsumer();

    @Override
    public String sendHiringForGroupToHero(String heroName, int groupId, int taskid, String messageToHero) throws UnexpectedResponseCodeException {

        // TODO - Benutzer über sendHiringForGroupToHero benachrichten

        Adventurer adventurer = this.tavernaService.getAdventure(heroName);

        // TODO - Wenn Grouß nicht da, dann besser response als 404 - Not Found
        Group group = this.tavernaService.getGroup(groupId);

        Task task = this.questConsumer.getTask(taskid);

        String heroServiceUrl = adventurer.getUrl();

        if (!heroServiceUrl.substring(0, 7).equals("http://")) {
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        //System.out.println(heroServiceUrl);

        Service heroService = this.toHeroConsumer.getHeroService(heroServiceUrl);

        String heroHiringUrl = heroService.getHirings();

        if (!heroHiringUrl.substring(0, 7).equals("http://")) {
            heroHiringUrl = "http://" + heroHiringUrl;
        }

        // TODO - Ist das Hiring hier mit den richtigen Daten befüllt?
        Hiring hiring = new Hiring(group.get_links().getSelf(), task.getName(), messageToHero);

        return this.toHeroConsumer.sendHiring(hiring, heroHiringUrl);
    }

    @Override
    public void getHerosServices() throws UnexpectedResponseCodeException {

        List<Adventurer> adventurerList = this.tavernaService.getAdventurers();

        for (Adventurer adventurer : adventurerList) {

            try {

                String heroServiceUrl = adventurer.getUrl();

                if (!heroServiceUrl.substring(0, 7).equals("http://")) {
                    heroServiceUrl = "http://" + heroServiceUrl;
                }

                Service heroService = this.toHeroConsumer.getHeroService(heroServiceUrl);

                System.out.println(heroService.toString() + "\n");

            } catch (Exception e) {

            }

        }

    }

    @Override
    public String wantMutex(String ipPort, String ressource) throws UnexpectedResponseCodeException {

        DistributedMutex.getInstance().requestMutex(this.tavernaService.getAdventurersWithCapabilityMutex());
        // Phase 3 - Betrete den kritischen Abschnitt/Bereich
        logger.info("Phase 3 - entering the critical section");

        Blackboard.getInstance().getUser().getMutex().setState(MutexState.HOLD);
        logger.info("set mutex-state to: " + MutexState.HOLD.toString());

        logger.info("entering critcal section");
        String body = this.questConsumer.postBuffered(ipPort, ressource, "");

        logger.info("leaving critcal section");
        Blackboard.getInstance().getUser().getMutex().setState(MutexState.RELEASED);
        logger.info("set mutex-state to: " + MutexState.RELEASED.toString());

        DistributedMutex.getInstance().dispenseMutext();

        return body;

    }


    @Override
    public void sendMessage(String adventurer, String string) throws UnexpectedResponseCodeException {


        Adventurer adven = this.tavernaService.getAdventure(adventurer);

        String heroServiceUrl = adven.getUrl();

        if (!heroServiceUrl.substring(0, 7).equals("http://")) {
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        Service heroService = this.toHeroConsumer.getHeroService(heroServiceUrl);

        String heroMessageUrl = heroService.getMessages();

        if (!heroMessageUrl.substring(0, 7).equals("http://")) {
            heroMessageUrl = "http://" + heroMessageUrl;
        }

        // TODO - User über fehlgeschlagene Zustellung informieren

        // TODO - User übererfolgreiche zustellung informieren
        Message message = new Message(string, "", "message");

        this.toHeroConsumer.sendMessage(message, heroMessageUrl);
    }

    @Override
    public void sendAssignment(String adventurer, String message) throws UnexpectedResponseCodeException {

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

        if (!heroServiceUrl.substring(0, 7).equals("http://")) {
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        Service heroService = this.toHeroConsumer.getHeroService(heroServiceUrl);

        String heroAssignmentUrl = heroService.getAssignments();

        if (!heroAssignmentUrl.substring(0, 7).equals("http://")) {
            heroAssignmentUrl = "http://" + heroAssignmentUrl;
        }

        Assignment assignment = new Assignment(
                String.valueOf(Assignment.counter),
                Blackboard.getInstance().getUser().getCurrentQuesting().getTask().get_links().getSelf(),
                Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri(),
                "POST", // TODO - method muss vom command mit übergeben werden,per user eingabe
                " ",
                "http://" + Application.IP + ":8080/assignments/deliveries",
                message
        );

        //Blackboard.getInstance().getUser().setSendetAssignment(assignment);
        Blackboard.getInstance().getUser().getSendetAssignmentList().add(assignment);

        this.toHeroConsumer.sendAssignment(heroAssignmentUrl, assignment);

    }

    @Override
    public void sendEndAssignment(String adventurer, String message) throws UnexpectedResponseCodeException {

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getTask().getToken() == null) {
            throw new IllegalArgumentException("cant send end token, because empty");
        }

        Adventurer adven = this.tavernaService.getAdventure(adventurer);

        String heroServiceUrl = adven.getUrl();

        if (!heroServiceUrl.substring(0, 7).equals("http://")) {
            heroServiceUrl = "http://" + heroServiceUrl;
        }

        Service heroService = this.toHeroConsumer.getHeroService(heroServiceUrl);

        String heroAssignmentUrl = heroService.getAssignments();

        if (!heroAssignmentUrl.substring(0, 7).equals("http://")) {
            heroAssignmentUrl = "http://" + heroAssignmentUrl;
        }

        // creating the body with the task token
        JSONObject jsonToken = new JSONObject();
        jsonToken.put(Blackboard.getInstance().getUrl() + Blackboard.getInstance().getUser().getCurrentQuesting().getTask().get_links().getSelf(), Blackboard.getInstance().getUser().getCurrentQuesting().getTask().getToken());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tokens", jsonToken);

        Assignment assignment = new Assignment(
                String.valueOf(Assignment.counter),
                Blackboard.getInstance().getUser().getCurrentQuesting().getTask().get_links().getSelf(),
                API.BLACKBOARD_QUESTS + "/" + Blackboard.getInstance().getUser().getCurrentQuesting().getTask().getQuest() + "/deliveries",
                "POST", // TODO - method muss vom command mit übergeben werden,per user eingabe
                jsonToken.toString(),
                "http://" + Application.IP + ":8080/assignments/deliveries",
                message
        );

        //Blackboard.getInstance().getUser().setSendetAssignment(assignment);
        Blackboard.getInstance().getUser().getSendetAssignmentList().add(assignment);

        this.toHeroConsumer.sendAssignment(heroAssignmentUrl, assignment);

    }

    @Override
    public void sendAssignmentDeliver() throws UnexpectedResponseCodeException {

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart() == null) {
            throw new IllegalArgumentException("no part to deliverTask");
        }

        if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList() == null) {
            throw new IllegalArgumentException("no steps to deliverTask");
        }


        for (int i = 0; i < Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList().size(); i++) {

            if (Blackboard.getInstance().getUser().getCurrentQuesting().getPart().getStepList().get(i).getToken().getToken() == null) {

                throw new IllegalArgumentException("a steptoken is missing");
            }
        }

        TaskPart part = Blackboard.getInstance().getUser().getCurrentQuesting().getPart();

        String data = "";
        for (Step step : part.getStepList()) {
            String token = "\"" + step.getToken().getToken() + "\", ";
            data = data + token;
        }

        data = data.substring(0, data.length() - 2);

        AssignmentDeliver assignmentDeliver = new AssignmentDeliver(
                Blackboard.getInstance().getUser().getAssignmentList().get(0).getId(),
                Blackboard.getInstance().getUser().getAssignmentList().get(0).getTask(),
                Blackboard.getInstance().getUser().getAssignmentList().get(0).getResource(),
                Blackboard.getInstance().getUser().getAssignmentList().get(0).getMethod(),
                data,
                Blackboard.getInstance().getUser().get_links().getSelf(),
                "i done the job!");

        //System.out.println(data);

        toHeroConsumer.sendAssignmentDeliver(Blackboard.getInstance().getUser().getAssignmentList().get(0).getCallback(), assignmentDeliver);

    }

    public void doElection() throws NotInGroupException, UnexpectedResponseCodeException {

        if (Blackboard.getInstance().getUser().isDead()) {
            throw new IllegalArgumentException("you are dead!, you cant start a election");
        }

        Assignment assignment;
        String data = null;

        if (Blackboard.getInstance().getUser().getCurrentQuesting() == null) {

            assignment = null;

        } else {

            if (Blackboard.getInstance().getUser().getCurrentQuesting().getRingToken() == null) {
                data = "{\"group\":\"" + Blackboard.getInstance().getUser().getCurrentGroup().get_links().getSelf() + "\"}";
            } else {
                data = "{\"group\":\"" + Blackboard.getInstance().getUser().getCurrentGroup().get_links().getSelf() + "\",\"token\":\"" + Blackboard.getInstance().getUser().getCurrentQuesting().getRingToken() + "\"}";
            }

        }


        assignment = new Assignment(
                String.valueOf(Assignment.counter) + "ELECTION",
                Blackboard.getInstance().getUser().getCurrentQuesting().getTask().get_links().getSelf(),
                Blackboard.getInstance().getUser().getCurrentQuesting().getCurrentUri(),
                "POST", // TODO - method muss vom command mit übergeben werden,per user eingabe
                data,
                "http://" + Application.IP + ":8080/assignments/deliveries",
                "do the job!"
        );

        this.startElection(assignment);

    }


    @Override
    public void startElection(Assignment assignment) throws UnexpectedResponseCodeException, NotInGroupException {

        logger.info("starting election");

        if (Blackboard.getInstance().getUser().getCurrentGroup() == null) {
            logger.info("current user is not in a group");
            throw new NotInGroupException("you must be in a group to start a election");
        }


        // Hier die election starten
        // Hole alle GroupMembers, sende status election an alle mit höhere Id

        Blackboard.getInstance().getUser().setElectionWinFlag(true);
        logger.info("set electionWinFlag: " + "true");

        List<Adventurer> groupMembers = this.tavernaService.getGroupMembers(Blackboard.getInstance().getUser().getCurrentGroup().getId());

        for (Adventurer adventurer : groupMembers) {

            if (adventurer.getUser().length() > Blackboard.getInstance().getUser().get_links().getSelf().length()) {

                // Wenn Hero abgestürtz, dann nimm den nächsten hero
                try {

                    Service heroService = this.toHeroConsumer.getHeroService(adventurer.getUrl());

                    logger.info("sending election_state ELECTION to: " + adventurer.getUser() + " - " + heroService.getElection());


                    this.toHeroConsumer.sendElection(

                            heroService.getElection(),

                            new Election(
                                    API.ELECTION_ALGORTIHM,
                                    API.ELECTION_STATE_ELECTION,
                                    Blackboard.getInstance().getUser().get_links().getSelf(),
                                    assignment,
                                    "we have to elect a new coordinator"
                            ));

                } catch (Exception e) {
                    logger.warn("unavailable: cant sending election_state ELECTION to" + adventurer.getUser().toString());
                    continue;

                }

            }


        }

        // TODO - Warte auf timeout zeit
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO - falls electionWinFlag = false, tue nichts - wir haben ein answer bekommen
        if (!Blackboard.getInstance().getUser().isElectionWinFlag()) {
            logger.info("user losed the election");
            System.out.print("\nyou lose the election");
        }

        // TODO - falls electionWinFlag true, sende hier an alle status coordinator
        if (Blackboard.getInstance().getUser().isElectionWinFlag()) {
            logger.info("user wins the election");
            System.out.print("\nyou win the election, your are now the new coordinator\n");

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
                                    "now its my time!"
                            ));

                } catch (Exception e) {
                    logger.warn("unavailable: cant sendeing election_state COORDINATOR to " + adventurer.getUser().toString());
                    continue;

                }

            }


            logger.info("current user wins the election");
            logger.info("set coodirnator: " + Blackboard.getInstance().getUser().getName());
            logger.info("election job: " + assignment.toString());
            this.fromHeroService.addAssignment(assignment);

            Blackboard.getInstance().getUser().getCurrentGroup().setCoordinator(Blackboard.getInstance().getUser().get_links().getSelf());
            Blackboard.getInstance().getUser().setElectionWinFlag(false);

        }


    }


}
