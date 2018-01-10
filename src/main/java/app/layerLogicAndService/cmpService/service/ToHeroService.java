package app.layerLogicAndService.cmpService.service;

import app.Application;
import app.configuration.API;
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

        // Phase 1 - Alle mit Capability mutex identifizieren und request schicken
        logger.info("Phase 1 - identify all heros with capability mutex and send mutex-request to them");
        logger.info("mutex-state - change state to: " + MutexState.WANTING.toString());

        Blackboard.getInstance().getUser().getMutex().setState(MutexState.WANTING);
        logger.info("mutex-state - change state to: " + MutexState.WANTING.toString());

        // Wir erhöhen hier die Zeit um eins, da es der Zeitstempel ist, den wir für unseren mutex-request an
        // alle anderen übermittelnt
        Blackboard.getInstance().getUser().getMutex().incrementTimeStampSend();
        logger.info("time now at: " + Blackboard.getInstance().getUser().getMutex().getTime());

        int requestedTimeStamp = Blackboard.getInstance().getUser().getMutex().getTime();
        Blackboard.getInstance().getUser().setMutexRquestedTimeStamp(requestedTimeStamp);
        logger.info("set mutex requested timestamp to: " + requestedTimeStamp);


        // 1. hole alle heros, die die capability mutex besitzen
        List<Adventurer> adventurerWithMutexList = this.tavernaService.getAdventurersWithCapabilityMutex();

        for (Adventurer adventurer : adventurerWithMutexList) {
            logger.info("send mutex-request for: " + adventurer.getUser() + "to: " + adventurer.getUrl());


            try {

                String urlHeroService = adventurer.getUrl();

                if (!urlHeroService.substring(0, 7).equals("http://")) {
                    urlHeroService = "http://" + urlHeroService;
                }

                Service heroService = this.toHeroConsumer.getHeroService(urlHeroService);

                String urlHeroMutexRequestUrl = heroService.getMutex();

                if (!urlHeroMutexRequestUrl.substring(0, 7).equals("http://")) {
                    urlHeroMutexRequestUrl = "http://" + urlHeroMutexRequestUrl;
                }

                String urlHeroMutexState = heroService.getMutexstate();

                if (!urlHeroMutexRequestUrl.substring(0, 7).equals("http://")) {
                    urlHeroMutexRequestUrl = "http://" + urlHeroMutexState;
                }

                String uuid = UUID.randomUUID().toString();

                MutexRequest request = new MutexRequest(
                        MutexMessage.REQUEST.toString(),
                        requestedTimeStamp,
                        HTTP + Application.IP + PORT + API.PATH_MUTEX_REPLY + "/" + uuid,
                        API.USERS + "/" + Blackboard.getInstance().getUser().getName()
                );

                logger.info("sending mutex-request to: " + adventurer.getUser() + " with id: " + uuid);


                MutexRequestWrapper wrapper = new MutexRequestWrapper(adventurer.getUser(), uuid, request, urlHeroMutexState);
                logger.info("adding mutex-request to sendMutexRequestList");
                Blackboard.getInstance().getUser().getSendMutexRequestList().add(wrapper);


                // TODO - muss hier für jedes mutex-request raussenden unsere uhr incrementiert werden?
                Blackboard.getInstance().getUser().getMutex().incrementTimeStampSend();
                this.toHeroConsumer.sendMutexMessage(urlHeroMutexRequestUrl, request);

            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }


        logger.info("Phase 2");
        // Phase 2, Prüfen ob von allen ein ein reply_ok zurpckgekommen ist.
        // Wenn nicht warte noch (z.b. ein anderer Prozess ist im kritischen Bereichen)
        // Und prüfe den mutex state


        // Wait bis Liste leer
        // 1. angemessene Zeit warten wenn die sendMutexRequestList nicht leer ist


        // Wenn antwort, dann lösche aus getSendMutexRequestList -> FromHeroService -> addMutexReply

        // So lange die Liste der gesendeten noch nicht leer ist
        // 2. Wenn nicht in einer angemessenen Zeit geantowrtet, dann prüfen von /mutexState
        // if wanting oder holt noch etwas warten, else bei release oder gar keine Antwort, aus der Liste löschen
        // die Liste aller gesendeten muss leer sein, damit von jedem eine Antwort rein gekommen ist
        List<MutexRequestWrapper> sendMutexRequestList = new ArrayList<MutexRequestWrapper>();
        sendMutexRequestList.addAll(Blackboard.getInstance().getUser().getSendMutexRequestList());

        while (!sendMutexRequestList.isEmpty()) {

            this.sleep(SLEEP_TIME);

            logger.info("sendMutexRequestList is after waiting not empty");

            List<MutexRequestWrapper> list = new ArrayList<MutexRequestWrapper>();
            list.addAll(Blackboard.getInstance().getUser().getSendMutexRequestList());

            logger.info("sendMutexRequestList: " + list.toString());

            // für jeden der noch in der Liste ist, Frage den mutexState ab
            for (MutexRequestWrapper wrapper : list) {

                logger.info("check mutex-state for: " + wrapper.getName().toString());

                // mutextState abfragen für den jeweiligen wrapper
                if (!wrapper.getPathMutexState().isEmpty()) {

                    Mutex hisCurrentMutexState = null;
                    try {
                        hisCurrentMutexState = this.toHeroConsumer.getMutexState(wrapper.getPathMutexState());
                    } catch (Exception e) {
                        logger.warn(e.getClass().getSimpleName());

                        // falss connection reuse löschen
                        Blackboard.getInstance().getUser().getSendMutexRequestList().remove(wrapper);
                        logger.info("hero is unavaible - delete mutex-request from sendMutexRequestList");
                        continue;

                    }

                    String mutexState = hisCurrentMutexState.getState();
                    logger.info("mutex-state is at: " + mutexState);

                    if (mutexState.equals(MutexState.RELEASED.toString())) {
                        Blackboard.getInstance().getUser().getSendMutexRequestList().remove(wrapper);
                        logger.info("hero does not want the mutex - delete mutex-request from sending list");
                    }

                    if (mutexState == null) {
                        // Falls der Service keine mutexState addresse hat, wird er aus der LIste gelöscht
                        Blackboard.getInstance().getUser().getSendMutexRequestList().remove(wrapper);
                        logger.info("service mutex-state is null - delete mutex-request from sending list");
                    }


                } else {
                    Blackboard.getInstance().getUser().getSendMutexRequestList().remove(wrapper);
                    logger.info("service does not have a mutex-state url - delete mutex-request from sending list because there is no mutexstate address");

                }


            }

            sendMutexRequestList = new ArrayList<MutexRequestWrapper>();
            sendMutexRequestList.addAll(Blackboard.getInstance().getUser().getSendMutexRequestList());

        }

        // Phase 3 - Betrete den kritischen Abschnitt/Bereich
        logger.info("Phase 3");
        String body = null;

        if (Blackboard.getInstance().getUser().getSendMutexRequestList().isEmpty()) {
            logger.info("sendMutexRequestList is empty");
            // Liste leer, alle haben geantwortet, dann kritisch Bereich betreten
            Blackboard.getInstance().getUser().getMutex().setState(MutexState.HOLD);
            logger.info("set mutex-state to: " + MutexState.HOLD.toString());
            logger.info("entering critcal section");
            body = this.questConsumer.postBuffered(ipPort, ressource, "");

        }

        logger.info("leaving critcal section");
        Blackboard.getInstance().getUser().getMutex().setState(MutexState.RELEASED);
        logger.info("set mutex-state to: " + MutexState.RELEASED.toString());


        // Phase 4 - Nach dem der kritische Bereich verlassen wurde, sende an die gespeicherten request
        // Anfragen ein reply_ok

        logger.info("Phase 4");
        logger.info("start to answer receive mutex-request queue");

        // 5. Wenn kritischer Bereich verlassen, dann mutexRequestStorageList, abarbeiten und allen ok senden.
        List<MutexRequest> mutexRequestStorageList = new ArrayList<MutexRequest>();
        mutexRequestStorageList.addAll(Blackboard.getInstance().getUser().getReceiveMutexRequestQueue());

        logger.info("receiveMutexRequestQueue: " + mutexRequestStorageList.toString());

        for (MutexRequest request : mutexRequestStorageList) {

            MutexRequest response = new MutexRequest(
                    MutexMessage.REPLYOK.toString(),
                    Blackboard.getInstance().getUser().getMutex().getTime(),
                    HTTP + Application.IP + PORT + API.PATH_MUTEX_REPLY,
                    API.USERS + "/" + Blackboard.getInstance().getUser().getName()
            );

            logger.info("senden reply-ok to: " + request.getReply());

            Blackboard.getInstance().getUser().getMutex().incrementTimeStampSend();
            this.toHeroConsumer.sendMutexMessage(request.getReply(), response);

            logger.info("time now at: " + Blackboard.getInstance().getUser().getMutex().getTime());
            Blackboard.getInstance().getUser().getReceiveMutexRequestQueue().remove(request);

        }


        return body;

    }

    private void sleep(int time){

            try {
                logger.info("sleep for: " + time);
                Thread.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
