package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer;

import app.layerLogicAndService.cmpBlackboard.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonDto.ErrorDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonHttpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonHttpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonHttpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonHttpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
import com.google.gson.Gson;

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
    public QuestsDTO getQuests() throws ErrorCodeException {

        String token = Blackboard.getInstance().getToken();


        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + "/blackboard/quests",
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            QuestsDTO dto = gson.fromJson(response.getBody(), QuestsDTO.class);

            return dto;

        }
    }

    @Override
    public QuestDTO getQuest(int index) throws ErrorCodeException {

        String token = Blackboard.getInstance().getToken();


        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + "/blackboard/quests" + "/" + index,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            QuestDTO dto = gson.fromJson(response.getBody(), QuestDTO.class);

            return dto;

        }
    }

    @Override
    public TaskDTO getTask(int index) throws ErrorCodeException {
        String token = Blackboard.getInstance().getToken();

        // /blackboard/quests/2/tasks
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + "/blackboard/tasks" + "/" + index,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            TaskDTO dto = gson.fromJson(response.getBody(), TaskDTO.class);

            return dto;

        }
    }

    @Override
    public MapDTO lookAtTheMap(String location) throws ErrorCodeException {

        String token = Blackboard.getInstance().getToken();

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + location,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            MapDTO dto = gson.fromJson(response.getBody(), MapDTO.class);

            return dto;

        }
    }

    @Override
    public VisitDTO visitHost(String ip, int port, String ressource) throws ErrorCodeException {

        String token = Blackboard.getInstance().getToken();

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        "http://" + ip + ":" + port + ressource,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            VisitDTO dto = gson.fromJson(response.getBody(), VisitDTO.class);

            return dto;

        }
    }

    @Override
    public AnswerDTO post(String ip, int port, String ressource, String body) throws ErrorCodeException {

        String token = Blackboard.getInstance().getToken();

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        "http://" + ip + ":" + port + ressource,
                        EnumHTTPMethod.POST,
                        body
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            AnswerDTO dto = gson.fromJson(response.getBody(), AnswerDTO.class);

            return dto;

        }
    }

    @Override
    public DeliverDTO deliver(int questId, String taskUri, String token) throws ErrorCodeException {

        String authToken = Blackboard.getInstance().getToken();

        System.out.println(Blackboard.getInstance().getUrl().toString() + "/blackboard/quests/" + questId + "/deliveries");

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + "/blackboard/quests/" + questId + "/deliveries",
                        EnumHTTPMethod.POST,
                        "{ \"tokens\": { \""+ taskUri + "\": \""+ token +"\" } }"
                );
        httpRequest.setAuthorizationToken(authToken);

        System.out.println(httpRequest.getBody().toString());

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            DeliverDTO dto = gson.fromJson(response.getBody(), DeliverDTO.class);

            return dto;

        }
    }
}
