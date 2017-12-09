package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpBlackboard.util.JSONUtil;
import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerLogicAndService.cmpQuest.entity.questing.Step;
import app.layerLogicAndService.cmpQuest.entity.questing.TaskPart;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class QuestConsumerImpl implements IQuestConsumer {

    private HTTPCaller httpCaller;
    private Gson gson;

    public QuestConsumerImpl() {
        this.httpCaller = new HTTPCaller();
        this.gson = new Gson();
    }

    @Override
    public List<Quest> getQuests() throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + "/blackboard/quests",
                Blackboard.getInstance().getUser().getUserToken());

        return JSONUtil.getObjectList(response.getBody(), "objects", Quest.class);

    }

    @Override
    public Quest getQuest(int index) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + "/blackboard/quests" + "/" + index,
                Blackboard.getInstance().getUser().getUserToken()
        );

        return JSONUtil.getObject(response.getBody(), "object", Quest.class);

    }

    @Override
    public Task getTask(int index) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + "/blackboard/tasks" + "/" + index,
                Blackboard.getInstance().getUser().getUserToken()
        );

        return JSONUtil.getObject(response.getBody(), "object", Task.class);

    }

    @Override
    public Map lookAtTheMap(String location) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + "/map/" + location,
                Blackboard.getInstance().getUser().getUserToken()
        );


        return JSONUtil.getObject(response.getBody(), "object", Map.class);

    }

    @Override
    public Visit visitHost(String ipPort, String ressource) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                "http://" + ipPort + ressource,
                Blackboard.getInstance().getUser().getUserToken()
        );


        return gson.fromJson(response.getBody(), Visit.class);

    }

    @Override
    public Answer post(String ipPort, String ressource, String body) throws UnexpectedResponseCodeException {

        String token = Blackboard.getInstance().getUser().getUserToken();

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        "http://" + ipPort + ressource,
                        EnumHTTPMethod.POST,
                        body
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {
            Answer dto = gson.fromJson(response.getBody(), Answer.class);

            return dto;

        }
    }

    @Override
    public List<Delivery> deliverTask(Task task) throws UnexpectedResponseCodeException {


        HTTPResponse response = this.httpCaller.doPOST(
                Blackboard.getInstance().getUrl().toString() + "/blackboard/quests/" + task.getQuest() + "/deliveries",
                Blackboard.getInstance().getUser().getUserToken(),
                "{ \"tokens\": { \"" + Blackboard.getInstance().getUrl() + task.get_links().getSelf() + "\": \"" + task.getToken() + "\" } }"
        );

        return JSONUtil.getObjectList(response.getBody(), "object", Delivery.class);

    }

    @Override
    public Visit deliverTaskPart(TaskPart taskpart) throws UnexpectedResponseCodeException {
        String authToken = Blackboard.getInstance().getUser().getUserToken();


        String tokens = "{ \"tokens\":[";

        for (Step step : taskpart.getStepList()) {
            String token = "\"" + step.getToken().getToken() + "\", ";
            tokens = tokens + token;
        }

        if (Blackboard.getInstance().getUser().getAssignmentDerliver() != null) {
            tokens = tokens + Blackboard.getInstance().getUser().getAssignmentDerliver().getData();
            tokens = tokens + "]}";
        } else {
            tokens = tokens.substring(0, tokens.length() - 2) + "]}";
        }


        System.out.println("tokens: " + tokens);
        System.out.println("url: " + "http://" + taskpart.getDeliverUri());

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        "http://" + taskpart.getDeliverUri(),
                        EnumHTTPMethod.POST,
                        tokens
                );
        httpRequest.setAuthorizationToken(authToken);

        //System.out.println(httpRequest.getBody().toString());

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

            // TODO - DeliverTaskPartDTO

        } else {
            Visit visit = gson.fromJson(response.getBody(), Visit.class);

            return visit;

        }
    }


}
