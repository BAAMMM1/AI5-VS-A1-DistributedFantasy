package app.layerLogicAndService.cmpService.algorithm;

import app.Application;
import app.configuration.API;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.Service;
import app.layerLogicAndService.cmpService.entity.hero.mutex.*;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.IToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.ToHeroConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.xlayerMiddleware.logicalClock.LamportClock;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Chris on 28.01.2018
 */
public class DistributedMutex {

    public final static Logger logger = Logger.getLogger(new Object() {
    }.getClass().getEnclosingClass());

    private List<MutexRequest> receiveMutexRequestQueue;
    private List<MutexRequestWrapper> sendMutexRequestList;
    private IToHeroConsumer toHeroConsumer;
    private int requestTime;

    private static DistributedMutex instance = null;

    public static final String PORT = ":8080";
    public static final String HTTP = "http://";
    public static final int SLEEP_TIME = 5000;

    public static DistributedMutex getInstance()
    {
        if (instance == null){
            instance = new DistributedMutex();
        }

        return instance;
    }

    private DistributedMutex() {
        this.receiveMutexRequestQueue = new ArrayList<MutexRequest>();
        this.sendMutexRequestList = new ArrayList<MutexRequestWrapper>();
        this.toHeroConsumer = new ToHeroConsumer();
    }

    public void requestMutex(List<Adventurer> adventurersWithMutexCapability){


        Blackboard.getInstance().getUser().getMutex().setState(MutexState.WANTING);
        logger.info("mutex-state - change state to: " + MutexState.WANTING.toString());

        this.sendRequests(adventurersWithMutexCapability);
        this.responseCheck();

    }

    public void dispenseMutext() throws UnexpectedResponseCodeException {
        this.answerQueue();

    }

    private void sendRequests(List<Adventurer> adventurersWithMutexCapability){

        logger.info("Phase 1 - identify all heros with capability mutex and send mutex-request to them");

        // Wir erhöhen hier die Zeit um eins, da es der Zeitstempel ist, den wir für unseren mutex-request an
        // alle anderen übermittelnt
        LamportClock.getInstance().tick();
        logger.info("time now at: " + LamportClock.getInstance().getTime());

        int time = LamportClock.getInstance().getTime();
        this.requestTime = time;

        logger.info("set mutex requested timestamp to: " + time);

        MutexRequestWrapper wrapper = null;

        for (Adventurer adventurer : adventurersWithMutexCapability) {
            logger.info("for adventuer: " + adventurer.getUser().replace("/name/", "") + " to: " + adventurer.getUrl());


            Service heroService = null;
            try {
                heroService = this.toHeroConsumer.getHeroService(this.getUrl(adventurer.getUrl()));

            } catch (UnexpectedResponseCodeException e) {
                logger.warn(e.getMessage());
                continue;
            }


            try {
                String uuid = UUID.randomUUID().toString();


                logger.info("mutex-request with id: " + uuid);
                wrapper = new MutexRequestWrapper(adventurer.getUser(), uuid, this.getUrl(heroService.getMutexstate()));


                this.sendMutexRequestList.add(wrapper);

                this.toHeroConsumer.sendMutexMessage(
                        this.getUrl(heroService.getMutex()),
                        MutexMessage.REQUEST, time,
                        HTTP + Application.IP + PORT + API.PATH_MUTEX_REPLY + "/" + uuid
                );


            } catch (Exception e) {
                if(wrapper != null){
                    sendMutexRequestList.remove(wrapper);
                }
                logger.warn(e.getMessage());
            }


        }

    }

    private void responseCheck(){

        List<MutexRequestWrapper> sendMutexRequestList = new ArrayList<MutexRequestWrapper>();
        sendMutexRequestList.addAll(this.sendMutexRequestList);

        while (!sendMutexRequestList.isEmpty()) {

            this.sleep(SLEEP_TIME);

            logger.info("sendMutexRequestList is after waiting not empty");

            List<MutexRequestWrapper> list = new ArrayList<MutexRequestWrapper>();
            list.addAll(this.sendMutexRequestList);

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

                        // falls connection reuse löschen
                        this.sendMutexRequestList.remove(wrapper);
                        logger.info("hero is unavaible - delete mutex-request from sendMutexRequestList");
                        continue;

                    }

                    String mutexState = hisCurrentMutexState.getState();
                    logger.info("mutex-state is at: " + mutexState);

                    if (mutexState.equals(MutexState.RELEASED.toString())) {
                        this.sendMutexRequestList.remove(wrapper);
                        logger.info("hero does not want the mutex - delete mutex-request from sending list");
                    }

                    if (mutexState == null) {
                        // Falls der Service keine mutexState addresse hat, wird er aus der LIste gelöscht
                        this.sendMutexRequestList.remove(wrapper);
                        logger.info("service mutex-state is null - delete mutex-request from sending list");
                    }


                } else {
                    this.sendMutexRequestList.remove(wrapper);
                    logger.info("service does not have a mutex-state url - delete mutex-request from sending list because there is no mutexstate address");

                }


            }

            sendMutexRequestList = new ArrayList<MutexRequestWrapper>();
            sendMutexRequestList.addAll(this.sendMutexRequestList);

        }

    }



    private void answerQueue() throws UnexpectedResponseCodeException {

        logger.info("Phase 4 - Start to answer receive mutex-request queue");
        // 5. Wenn kritischer Bereich verlassen, dann mutexRequestStorageList, abarbeiten und allen ok senden.

        List<MutexRequest> mutexRequestStorageList = new ArrayList<MutexRequest>();
        mutexRequestStorageList.addAll(this.receiveMutexRequestQueue);

        logger.info("receiveMutexRequestQueue: " + mutexRequestStorageList.toString());

        for (MutexRequest request : mutexRequestStorageList) {

            logger.info("send reply-ok for: " + request.getUser() + " to: " + request.getReply());
            this.toHeroConsumer.sendMutexMessage(request.getReply(), MutexMessage.REPLYOK, null);

            logger.info("time now at: " + LamportClock.getInstance().getTime());
            this.receiveMutexRequestQueue.remove(request);

        }

    }

    private String getUrl(String string) {

        if (!string.substring(0, 7).equals("http://")) {
            return "http://" + string;
        } else {
            return string;
        }

    }

    private void sleep(int time) {

        try {
            logger.info("sleep for: " + time);
            Thread.sleep(time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void incomeRequest(MutexRequest request) throws UnexpectedResponseCodeException {

        Mutex currentMutex = Blackboard.getInstance().getUser().getMutex();
        logger.info("current mutex: " + currentMutex);
        String mutexState = currentMutex.getState();

        logger.info("current state at: " + mutexState);
        logger.info("time now at: " + LamportClock.getInstance().getTime());



        // Falls das request von sich selber kommt, antworte mit reply_ok
        if (request.getUser().equals(API.USERS + "/" + Blackboard.getInstance().getUser().getName())) {
            logger.info("request comes from self");
            this.sendReplyOK(request);

        } else if (mutexState.equals(MutexState.RELEASED.toString())) {
            this.sendReplyOK(request);

        } else if (mutexState.equals(MutexState.WANTING.toString())) {

            if (request.getTime() < this.requestTime) {
                logger.info("request time is lower than own requested time");
                this.sendReplyOK(request);

            } else if (request.getTime() == this.requestTime) {
                logger.info("request time equals own requested time");

                logger.info("user-id: " + request.getUser());
                logger.info("own-id: " + API.USERS + "/" + Blackboard.getInstance().getUser().getName());

                if (request.getUser().length() < (API.USERS + "/" + Blackboard.getInstance().getUser().getName()).length()) {

                    logger.info("userId is lower than own id");
                    this.sendReplyOK(request);

                } else {
                    //eigene id ist kleiner als income ID
                    logger.info("own id is lower than userId");
                    this.saveMutexRequest(request);
                }

            } else {
                // eigene Zeit ist kleiner als income Zeit
                logger.info("own time is lower");
                this.saveMutexRequest(request);
            }

        } else if (mutexState.equals(MutexState.HOLD.toString())) {
            this.saveMutexRequest(request);
        }
    }


    private void saveMutexRequest(MutexRequest request) {
        logger.info("saving request to answer later");
        this.receiveMutexRequestQueue.add(request);


    }

    private void sendReplyOK(MutexRequest request) throws UnexpectedResponseCodeException {

        logger.info("answer with: " + MutexMessage.REPLYOK.toString());

        this.toHeroConsumer.sendMutexMessage(request.getReply(), MutexMessage.REPLYOK, null);

    }

    public void incomeReplyOK(String uuid, MutexRequest request){

        logger.info("mutex-reply - processed: " + uuid);

        List<MutexRequestWrapper> wrapperList = this.sendMutexRequestList;

        MutexRequestWrapper wrapper = wrapperList.stream().filter(w -> w.getUuid().equals(uuid)).findFirst().orElse(null);

        if (wrapper != null) {
            logger.info("mutex-reply - remove from mutexMessageSendingList: " + uuid);
            this.sendMutexRequestList.remove(wrapper);
        } else {
            logger.info("mutex-reply - not found in sending list: " + uuid);
        }
    }
}
