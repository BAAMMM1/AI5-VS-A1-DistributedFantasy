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

        HTTPResponse response = this.httpCaller.post(API.TAVERNA_ADVENTURERS, Blackboard.getInstance().getUser().getUserToken(), gson.toJson(hero));

        if (response.getCode() != 201) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectFirst(response.getBody(), "object", Adventurer.class);

    }

    @Override
    public List<Adventurer> getAdventurers() throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.get(API.TAVERNA_ADVENTURERS, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectList(response.getBody(), "objects", Adventurer.class);

    }

    @Override
    public Adventurer getAdventure(String name) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.get(API.TAVERNA_ADVENTURERS + "/" + name, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "object", Adventurer.class);

    }

    @Override
    public List<Group> getGroups() throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.get(API.TAVERNA_GROUPS, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectList(response.getBody(), "objects", Group.class);

    }

    @Override
    public Group getGroup(int groupId) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.get(API.TAVERNA_GROUPS + "/" + groupId, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "object", Group.class);
    }


    @Override
    public Group createGroup() throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.post(API.TAVERNA_GROUPS, Blackboard.getInstance().getUser().getUserToken(), "{}");

        if (response.getCode() != 201) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectFirst(response.getBody(), "object", Group.class);
    }

    @Override
    public String deleteGroup(int groupId) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.delete(API.TAVERNA_GROUPS + "/" + groupId, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "message", String.class);

    }

    @Override
    public String enterGroup(int groupId) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.post(API.TAVERNA_GROUPS + "/" + groupId + "/members", Blackboard.getInstance().getUser().getUserToken(), "");

        if (response.getCode() != 201) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "message", String.class);

    }

    @Override
    public List<Adventurer> getGroupMembers(int groupId) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.get(API.TAVERNA_GROUPS + "/" + groupId + "/members", Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectList(response.getBody(), "objects", Adventurer.class);

    }

}
