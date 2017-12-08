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


        HTTPResponse response = this.httpCaller.doPOST(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.ADVENTURERS,
                Blackboard.getInstance().getUser().getUserToken(),
                gson.toJson(hero));


        return gson.fromJson(new JSONObject(response.getBody()).getJSONArray("object").get(0).toString(), Adventurer.class);


    }

    @Override
    public List<Adventurer> getAdventurers() throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.ADVENTURERS,
                Blackboard.getInstance().getUser().getUserToken()
        );

        List<Adventurer> adventurerList = new ArrayList<Adventurer>();
        JSONArray jsonArray = new JSONObject(response.getBody()).getJSONArray("objects");

        for (int i = 0; i < jsonArray.length(); i++) {
            adventurerList.add(gson.fromJson(jsonArray.get(i).toString(), Adventurer.class));
        }

        return adventurerList;

    }

    @Override
    public Adventurer getAdventure(String name) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.ADVENTURERS + "/" + name,
                Blackboard.getInstance().getUser().getUserToken()
        );

        return gson.fromJson(new JSONObject(response.getBody()).get("object").toString(), Adventurer.class);

    }

    @Override
    public List<Group> getGroups() throws UnexpectedResponseCodeException {


        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS,
                Blackboard.getInstance().getUser().getUserToken());


        List<Group> groupList = new ArrayList<Group>();
        JSONArray jsonArray = new JSONObject(response.getBody()).getJSONArray("objects");

        for (int i = 0; i < jsonArray.length(); i++) {
            groupList.add(gson.fromJson(jsonArray.get(i).toString(), Group.class));
        }

        return groupList;


    }

    @Override
    public Group getGroup(int id) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id,
                Blackboard.getInstance().getUser().getUserToken()
        );

        return gson.fromJson(new JSONObject(response.getBody()).get("object").toString(), Group.class);


    }

    @Override
    public Group createGroup() throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doPOST(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS,
                Blackboard.getInstance().getUser().getUserToken(),
                "{}"
        );

        return gson.fromJson(new JSONObject(response.getBody()).getJSONArray("object").get(0).toString(), Group.class);

    }

    @Override
    public String deleteGroup(int id) throws UnexpectedResponseCodeException {


        HTTPResponse response = this.httpCaller.doDELETE(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id,
                Blackboard.getInstance().getUser().getUserToken()
        );

        return new JSONObject(response.getBody()).get("message").toString();


    }

    @Override
    public String enterGroup(int id) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doPOST(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id + "/members",
                Blackboard.getInstance().getUser().getUserToken(),
                ""
        );

        return new JSONObject(response.getBody()).get("message").toString();

    }

    @Override
    public List<Adventurer> getGroupMembers(int id) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id + "/members",
                Blackboard.getInstance().getUser().getUserToken());

        List<Adventurer> adventurerList = new ArrayList<Adventurer>();
        JSONArray jsonArray = new JSONObject(response.getBody()).getJSONArray("objects");

        for (int i = 0; i < jsonArray.length(); i++) {
            adventurerList.add(gson.fromJson(jsonArray.get(i).toString(), Adventurer.class));
        }

        return adventurerList;

    }


}
