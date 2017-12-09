package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.util.JSONUtil;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.entity.taverna.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.service.HttpAccess;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.configuration.API;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public class TavernaConsumer implements ITavernaConsumer {

    private HttpAccess httpAccess;
    private Gson gson;

    public TavernaConsumer() {
        this.httpAccess = new HttpAccess();
        this.gson = new Gson();
    }

    @Override
    public Adventurer addHeroService(Hero hero) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(API.TAVERNA_ADVENTURERS, Blackboard.getInstance().getUser().getUserToken(), gson.toJson(hero));

        if (response.getCode() != 201) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectFirst(response.getBody(), "object", Adventurer.class);

    }

    @Override
    public List<Adventurer> getAdventurers() throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.TAVERNA_ADVENTURERS, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectList(response.getBody(), "objects", Adventurer.class);

    }

    @Override
    public Adventurer getAdventure(String name) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.TAVERNA_ADVENTURERS + "/" + name, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "object", Adventurer.class);

    }

    @Override
    public List<Group> getGroups() throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.TAVERNA_GROUPS, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectList(response.getBody(), "objects", Group.class);

    }

    @Override
    public Group getGroup(int groupId) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.TAVERNA_GROUPS + "/" + groupId, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "object", Group.class);
    }


    @Override
    public Group createGroup() throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(API.TAVERNA_GROUPS, Blackboard.getInstance().getUser().getUserToken(), "{}");

        if (response.getCode() != 201) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectFirst(response.getBody(), "object", Group.class);
    }

    @Override
    public String deleteGroup(int groupId) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.delete(API.TAVERNA_GROUPS + "/" + groupId, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "message", String.class);

    }

    @Override
    public String enterGroup(int groupId) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(API.TAVERNA_GROUPS + "/" + groupId + "/members", Blackboard.getInstance().getUser().getUserToken(), "");

        if (response.getCode() != 201) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "message", String.class);

    }

    @Override
    public List<Adventurer> getGroupMembers(int groupId) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.TAVERNA_GROUPS + "/" + groupId + "/members", Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectList(response.getBody(), "objects", Adventurer.class);

    }

}
