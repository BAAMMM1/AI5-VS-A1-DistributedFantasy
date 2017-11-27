package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerLogicAndService.cmpQuest.entity.questing.Step;
import app.layerLogicAndService.cmpQuest.entity.questing.TaskPart;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
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
    public List<Quest> getQuests() throws ErrorCodeException {

        String token = Blackboard.getInstance().getUser().getUserToken();


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
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            QuestsDTO dto = gson.fromJson(response.getBody(), QuestsDTO.class);

            return dto.getObjects();

        }
    }

    @Override
    public Quest getQuest(int index) throws ErrorCodeException {

        String token = Blackboard.getInstance().getUser().getUserToken();


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
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            QuestDTO dto = gson.fromJson(response.getBody(), QuestDTO.class);

            return dto.getObject();

        }
    }

    @Override
    public Task getTask(int index) throws ErrorCodeException {
        String token = Blackboard.getInstance().getUser().getUserToken();

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
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            TaskDTO dto = gson.fromJson(response.getBody(), TaskDTO.class);

            return dto.getObject();

        }
    }

    @Override
    public Map lookAtTheMap(String location) throws ErrorCodeException {

        String token = Blackboard.getInstance().getUser().getUserToken();

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + "/map/" + location,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            MapDTO dto = gson.fromJson(response.getBody(), MapDTO.class);

            return dto.getObject();

        }
    }

    @Override
    public Visit visitHost(String ipPort, String ressource) throws ErrorCodeException {

        String token = Blackboard.getInstance().getUser().getUserToken();

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        "http://" + ipPort + ressource,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            Visit visit = gson.fromJson(response.getBody(), Visit.class);

            return visit;

        }
    }

    @Override
    public Answer post(String ipPort, String ressource, String body) throws ErrorCodeException {

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

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            Answer dto = gson.fromJson(response.getBody(), Answer.class);

            return dto;

        }
    }

    @Override
    public List<Delivery> deliverTask(Task task) throws ErrorCodeException {

        String authToken = Blackboard.getInstance().getUser().getUserToken();

        //System.out.println(Blackboard.getInstance().getUrl().toString() + "/blackboard/quests/" + questId + "/deliveries");

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + "/blackboard/quests/" + task.getQuest() + "/deliveries",
                        EnumHTTPMethod.POST,
                        "{ \"tokens\": { \"" + Blackboard.getInstance().getUrl() + task.get_links().getSelf() + "\": \"" + task.getToken() + "\" } }"
                );
        httpRequest.setAuthorizationToken(authToken);

        //System.out.println(httpRequest.getBody().toString());

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 201) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            DeliverTaskDTO dto = gson.fromJson(response.getBody(), DeliverTaskDTO.class);

            return dto.getObject();

        }
    }

    @Override
    public Visit deliverTaskPart(TaskPart taskpart) throws ErrorCodeException {
        String authToken = Blackboard.getInstance().getUser().getUserToken();


        String tokens = "{ \"tokens\":[";

        for (Step step : taskpart.getStepList()) {
            String token = "\""+ step.getToken().getToken() +"\", ";
            tokens = tokens + token;
        }

        tokens = tokens.substring(0, tokens.length()-2) + "]}";

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

            throw new ErrorCodeException(errorCodeDTO);

            // TODO - DeliverTaskPartDTO

        } else {
            Visit visit = gson.fromJson(response.getBody(), Visit.class);

            return visit;

        }
    }

    private class QuestDTO {

        // TODO - requires_tokens fehlt noch
    /*
    {
  "object": {
    "_links": {
      "deliveries": "/blackboard/quests/3/deliveries",
      "self": "/blackboard/quests/3",
      "tasks": "/blackboard/quests/3/tasks"
    },
    "deliveries": [],
    "description": "\nWhile you where away the Throneroom got attacked.\nWe were able to fight off the scum, but there are still a lot of wounded.\nPlease help to carry the wounded to the infirmary.",
    "followups": [],
    "id": 3,
    "name": "Help the Wounded",
    "prerequisites": [],
    "requirements": "group",
    "requires_tokens": {
      "/blackboard/tasks/4": "Token:Carry a wounded person" // TODO - warum?
    },
    "reward": 1,
    "tasks": [
      "/blackboard/tasks/4"
    ]
  },
  "status": "success"
}

     */

        private Quest object;
        private String status;

        public QuestDTO(app.layerLogicAndService.cmpQuest.entity.Quest object, String status) {
            this.object = object;
            this.status = status;
        }

        public app.layerLogicAndService.cmpQuest.entity.Quest getObject() {
            return object;
        }

        public void setObject(app.layerLogicAndService.cmpQuest.entity.Quest object) {
            this.object = object;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "QuestDTO{" +
                    "\nobject=" + object +
                    ", \nstatus='" + status + '\'' +
                    '}';
        }


    }


    private class QuestsDTO {

        List<Quest> objects;
        String status;

        public QuestsDTO(List<Quest> objects, String status) {
            this.objects = objects;
            this.status = status;
        }

        public List<Quest> getObjects() {
            return objects;
        }

        public void setObjects(List<Quest> objects) {
            this.objects = objects;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "QuestsDTO{" +
                    "\nobjects=" + objects +
                    ", \nstatus='" + status + '\'' +
                    '}';
        }

    }


    private class TaskDTO {

        private Task object;
        private String status;

        public TaskDTO(Task object, String status) {
            this.object = object;
            this.status = status;
        }

        public Task getObject() {
            return object;
        }

        public void setObject(Task object) {
            this.object = object;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "TaskDTO{" +
                    "\nobject=" + object +
                    ", \nstatus='" + status + '\'' +
                    '}';
        }

    }



    private class MapDTO {

        private Map object;
        private String status;

        public MapDTO(Map object, String status) {
            this.object = object;
            this.status = status;
        }

        public Map getObject() {
            return object;
        }

        public void setObject(Map object) {
            this.object = object;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "MapDTO{" +
                    "\nobject=" + object +
                    ", \nstatus='" + status + '\'' +
                    '}';
        }

    }

    private class DeliverTaskDTO {

        /*

{
  "message": "Created Delivery",
  "object": [
    {
      "deliverables": [
        193
      ],
      "id": 434,
      "quest": 1,
      "timestamp": "2017-11-21T16:11:39.834529+00:00",
      "user": "x2"
    }
  ],
  "status": "success"
}

     */

        private String message;
        private List<Delivery> object;
        private String status;

        public DeliverTaskDTO(String message, List<Delivery> object, String status) {
            this.message = message;
            this.object = object;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Delivery> getObject() {
            return object;
        }

        public void setObject(List<Delivery> object) {
            this.object = object;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "DeliverTaskDTO{" +
                    "\nmessage='" + message + '\'' +
                    ", \nobject=" + object +
                    ", \nstatus='" + status + '\'' +
                    '}';
        }



    }
}
