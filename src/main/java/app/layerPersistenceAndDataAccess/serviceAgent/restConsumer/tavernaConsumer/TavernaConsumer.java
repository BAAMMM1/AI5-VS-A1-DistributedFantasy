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
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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
    public Adventurer addHeroService(Hero hero) throws UnexpectedResponseCodeException {


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

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {

            return gson.fromJson(new JSONObject(response.getBody()).getJSONArray("object").get(0).toString(), Adventurer.class);
        }

    }

    @Override
    public List<Adventurer> getAdventurers() throws UnexpectedResponseCodeException {
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

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {

            List<Adventurer> adventurerList = new ArrayList<Adventurer>();
            JSONArray jsonArray = new JSONObject(response.getBody()).getJSONArray("objects");

            for (int i = 0; i < jsonArray.length(); i++) {
                adventurerList.add(gson.fromJson(jsonArray.get(i).toString(), Adventurer.class));
            }

            return adventurerList;
        }
    }

    @Override
    public Adventurer getAdventure(String name) throws UnexpectedResponseCodeException {
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

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {

            return gson.fromJson(new JSONObject(response.getBody()).get("object").toString(), Adventurer.class);
        }
    }

    @Override
    public List<Group> getGroups() throws UnexpectedResponseCodeException {
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

            throw new UnexpectedResponseCodeException(new ErrorCodeDTO("server error >= 500", "operation could not be performend"));

        } else if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {


            List<Group> groupList = new ArrayList<Group>();
            JSONArray jsonArray = new JSONObject(response.getBody()).getJSONArray("objects");

            for (int i = 0; i < jsonArray.length(); i++) {
                groupList.add(gson.fromJson(jsonArray.get(i).toString(), Group.class));
            }

            return groupList;

        }
    }

    @Override
    public Group getGroup(int id) throws UnexpectedResponseCodeException {

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id,
                Blackboard.getInstance().getUser().getUserToken());

         if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(gson.fromJson(response.getBody(), ErrorCodeDTO.class));

        } else {
            return gson.fromJson(new JSONObject(response.getBody()).get("object").toString(), Group.class);
        }


    }

    @Override
    public Group createGroup() throws UnexpectedResponseCodeException {
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

            throw new UnexpectedResponseCodeException(new ErrorCodeDTO("server error >= 500", "operation could not be performend"));

        } else if (response.getCode() != 201) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {

            return gson.fromJson(new JSONObject(response.getBody()).getJSONArray("object").get(0).toString(), Group.class);
        }
    }

    @Override
    public String deleteGroup(int id) throws UnexpectedResponseCodeException {
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

            throw new UnexpectedResponseCodeException(new ErrorCodeDTO("server error >= 500", "operation could not be performend"));

        } else if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {

            return new JSONObject(response.getBody()).get("message").toString();
        }

    }

    @Override
    public String enterGroup(int id) throws UnexpectedResponseCodeException {
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

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {

            return new JSONObject(response.getBody()).get("message").toString();
        }
    }

    @Override
    public List<Adventurer> getGroupMembers(int id) throws UnexpectedResponseCodeException {


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id + "/members",
                Blackboard.getInstance().getUser().getUserToken());


        if (response.getCode() != 200) {

            throw new UnexpectedResponseCodeException(gson.fromJson(response.getBody(), ErrorCodeDTO.class));

        } else {

            List<Adventurer> adventurerList = new ArrayList<Adventurer>();
            JSONArray jsonArray = new JSONObject(response.getBody()).getJSONArray("objects");

            for (int i = 0; i < jsonArray.length(); i++) {
                adventurerList.add(gson.fromJson(jsonArray.get(i).toString(), Adventurer.class));
            }

            return adventurerList;
        }
    }


}
