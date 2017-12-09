package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpBlackboard.util.JSONUtil;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.entity.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
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
                URLTaverna.TAVERNA_ADVENTURERS,
                Blackboard.getInstance().getUser().getUserToken(),
                gson.toJson(hero));


        return gson.fromJson(new JSONObject(response.getBody()).getJSONArray("object").get(0).toString(), Adventurer.class);


    }

    @Override
    public List<Adventurer> getAdventurers() throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                URLTaverna.TAVERNA_ADVENTURERS,
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
                URLTaverna.TAVERNA_ADVENTURERS + "/" + name,
                Blackboard.getInstance().getUser().getUserToken()
        );

        return JSONUtil.getObject(response.getBody(), "object", Adventurer.class);
        //return gson.fromJson(new JSONObject(response.getBody()).get("object").toString(), Adventurer.class);

    }

    @Override
    public List<Group> getGroups() throws UnexpectedResponseCodeException {


        HTTPResponse response = this.httpCaller.doGET(
                URLTaverna.TAVERNA_GROUPS,
                Blackboard.getInstance().getUser().getUserToken());


        List<Group> groupList = new ArrayList<Group>();
        JSONArray jsonArray = new JSONObject(response.getBody()).getJSONArray("objects");

        for (int i = 0; i < jsonArray.length(); i++) {
            groupList.add(gson.fromJson(jsonArray.get(i).toString(), Group.class));
        }

        return groupList;


    }

    @Override
    public Group getGroup(int groupId) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                URLTaverna.TAVERNA_GROUPS + "/" + groupId,
                Blackboard.getInstance().getUser().getUserToken()
        );

        return JSONUtil.getObject(response.getBody(), "object", Group.class);

    }


    @Override
    public Group createGroup() throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doPOST(
                URLTaverna.TAVERNA_GROUPS,
                Blackboard.getInstance().getUser().getUserToken(),
                "{}"
        );

        return gson.fromJson(new JSONObject(response.getBody()).getJSONArray("object").get(0).toString(), Group.class);

    }

    @Override
    public String deleteGroup(int groupId) throws UnexpectedResponseCodeException {


        HTTPResponse response = this.httpCaller.doDELETE(
                URLTaverna.TAVERNA_GROUPS + "/" + groupId,
                Blackboard.getInstance().getUser().getUserToken()
        );

        return JSONUtil.getObject(response.getBody(), "message", String.class);
        //return new JSONObject(response.getBody()).get("message").toString();


    }

    @Override
    public String enterGroup(int groupId) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doPOST(
                URLTaverna.TAVERNA_GROUPS + "/" + groupId + "/members",
                Blackboard.getInstance().getUser().getUserToken(),
                ""
        );

        return JSONUtil.getObject(response.getBody(), "message", String.class);
        //return new JSONObject(response.getBody()).get("message").toString();

    }

    @Override
    public List<Adventurer> getGroupMembers(int groupId) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.doGET(
                URLTaverna.TAVERNA_GROUPS + "/" + groupId + "/members",
                Blackboard.getInstance().getUser().getUserToken());

        /*
        List<Adventurer> adventurerList = new ArrayList<Adventurer>();
        JSONArray jsonArray = new JSONObject(response.getBody()).getJSONArray("objects");

        for (int i = 0; i < jsonArray.length(); i++) {
            adventurerList.add(gson.fromJson(jsonArray.get(i).toString(), Adventurer.class));
        }

        return adventurerList;
        */

        return JSONUtil.getObjectList(response.getBody(), "objects", Adventurer.class);

    }


}
