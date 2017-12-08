package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.entity.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public class TavernaConsumer implements ITavernaConsumer {

    private HTTPCaller httpCaller;
    private Gson gson;

    public TavernaConsumer() {
        this.httpCaller = new HTTPCaller();
        this.gson = new Gson();
    }

    @Override
    public Adventurer addHeroService(Hero hero) throws ErrorCodeException {


        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.ADVENTURERS,
                        EnumHTTPMethod.POST,
                        gson.toJson(hero)
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 201) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            AdventurerDTO dto = gson.fromJson(response.getBody(), AdventurerDTO.class);

            Adventurer adventurer = gson.fromJson(new JSONObject(response.getBody()).getJSONArray("object").get(0).toString(), Adventurer.class);

            System.out.println("->\n" + adventurer.toString());

            return dto.getObject().get(0);
        }

    }

    @Override
    public List<Adventurer> getAdventurers() throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.ADVENTURERS,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            GroupGetMembersDTO dto = gson.fromJson(response.getBody(), GroupGetMembersDTO.class);

            return dto.getObjects();
        }
    }

    @Override
    public Adventurer getAdventure(String name) throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.ADVENTURERS + "/" + name,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {

            return gson.fromJson(new JSONObject(response.getBody()).get("object").toString(), Adventurer.class);
        }
    }

    @Override
    public List<Group> getGroups() throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        // TODO - Wenn 500 Fehler, dann nehme nicht als ErrorCodeDTO, bzw erstelle es selber

        if (response.getCode() >= 500) {

            throw new ErrorCodeException(new ErrorCodeDTO("server error >= 500", "operation could not be performend"));

        } else if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {

            GroupsGetDTO dto = gson.fromJson(response.getBody(), GroupsGetDTO.class);

            return dto.getObject();
        }
    }

    @Override
    public Group getGroup(int id) throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        // TODO - Wenn 500 Fehler, dann nehme nicht als ErrorCodeDTO, bzw erstelle es selber

        if (response.getCode() >= 500) {

            throw new ErrorCodeException(new ErrorCodeDTO("server error >= 500", "operation could not be performend"));

        } else if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {

            GroupGetDTO dto = gson.fromJson(response.getBody(), GroupGetDTO.class);

            return dto.getObject();
        }


    }

    @Override
    public Group createGroup() throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS,
                        EnumHTTPMethod.POST,
                        "{}"
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() >= 500) {

            throw new ErrorCodeException(new ErrorCodeDTO("server error >= 500", "operation could not be performend"));

        } else if (response.getCode() != 201) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            GroupCreateDTO dto = gson.fromJson(response.getBody(), GroupCreateDTO.class);

            return dto.getObject().get(0);
        }
    }

    @Override
    public String deleteGroup(int id) throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id,
                        EnumHTTPMethod.DELETE
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        // TODO - Wenn 500 Fehler, dann nehme nicht als ErrorCodeDTO, bzw erstelle es selber

        if (response.getCode() >= 500) {

            throw new ErrorCodeException(new ErrorCodeDTO("server error >= 500", "operation could not be performend"));

        } else if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            GroupDeleteDTO dto = gson.fromJson(response.getBody(), GroupDeleteDTO.class);

            return dto.getMessage();
        }

    }

    @Override
    public String enterGroup(int id) throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id + "/members",
                        EnumHTTPMethod.POST,
                        ""
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 201) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            GroupEnterDTO dto = gson.fromJson(response.getBody(), GroupEnterDTO.class);

            return dto.getMessage();
        }
    }

    @Override
    public List<Adventurer> getGroupMembers(int id) throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id + "/members",
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            GroupGetMembersDTO dto = gson.fromJson(response.getBody(), GroupGetMembersDTO.class);

            return dto.getObjects();
        }
    }

    private class AdventurerDTO {

        private String message;
        private List<Adventurer> object;
        private String status;

        public AdventurerDTO(String message, List<Adventurer> object, String status) {
            this.message = message;
            this.object = object;
            this.status = status;
        }

        public List<Adventurer> getObject() {
            return object;
        }

    }

    private class AdventurerGetDTO {

        private Adventurer object;
        private String status;

        public AdventurerGetDTO(Adventurer object, String status) {
            this.object = object;
            this.status = status;
        }

        public Adventurer getObject() {
            return object;
        }

    }

    private class GroupGetMembersDTO {

        private List<Adventurer> objects;
        private String status;

        public GroupGetMembersDTO(List<Adventurer> objects, String status) {
            this.objects = objects;
            this.status = status;
        }

        public List<Adventurer> getObjects() {
            return objects;
        }

    }

    private class GroupEnterDTO {

        private String message;
        private String objects;
        private String status;

        public GroupEnterDTO(String message, String object, String status) {
            this.message = message;
            this.objects = object;
            this.status = status;
        }

        public String getObject() {
            return objects;
        }

        public String getMessage() {
            return message;
        }
    }


    private class GroupCreateDTO {

        private String message;
        private List<Group> object;
        private String status;

        public GroupCreateDTO(String message, List<Group> object, String status) {
            this.message = message;
            this.object = object;
            this.status = status;
        }

        public List<Group> getObject() {
            return object;
        }
    }

    private class GroupsGetDTO {

        private String message;
        private List<Group> objects;
        private String status;

        public GroupsGetDTO(String message, List<Group> object, String status) {
            this.message = message;
            this.objects = object;
            this.status = status;
        }

        public List<Group> getObject() {
            return objects;
        }
    }

    private class GroupDeleteDTO {

        private String message;
        private String status;

        public GroupDeleteDTO(String message, String status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }
    }

    private class GroupGetDTO {

        private String message;
        private Group object;
        private String status;

        public GroupGetDTO(String message, Group object, String status) {
            this.message = message;
            this.object = object;
            this.status = status;
        }

        public Group getObject() {
            return object;
        }
    }


}
