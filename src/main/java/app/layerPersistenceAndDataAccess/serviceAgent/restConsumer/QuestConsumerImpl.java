package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpBlackboard.util.JSONUtil;
import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerLogicAndService.cmpQuest.entity.questing.Step;
import app.layerLogicAndService.cmpQuest.entity.questing.TaskPart;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.service.HttpAccess;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.API;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class QuestConsumerImpl implements IQuestConsumer {

    private HttpAccess httpAccess;
    private Gson gson;

    public QuestConsumerImpl() {
        this.httpAccess = new HttpAccess();
        this.gson = new Gson();
    }

    @Override
    public List<Quest> getQuests() throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.BLACKBOARD_QUESTS, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectList(response.getBody(), "objects", Quest.class);

    }

    @Override
    public Quest getQuest(int index) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.BLACKBOARD_QUESTS + "/" + index, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "object", Quest.class);

    }

    @Override
    public Task getTask(int index) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.BLACKBOARD_TASKS + "/" + index, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "object", Task.class);

    }

    @Override
    public Map lookAtTheMap(String location) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(API.MAP + "/" + location, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "object", Map.class);

    }

    @Override
    public Visit visitHost(String ipPort, String ressource) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get("http://" + ipPort + ressource, Blackboard.getInstance().getUser().getUserToken());

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Visit.class);

    }

    @Override
    public Answer post(String ipPort, String ressource, String body) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post("http://" + ipPort + ressource, Blackboard.getInstance().getUser().getUserToken(), body);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Answer.class);

    }

    @Override
    public List<Delivery> deliverTask(Task task) throws UnexpectedResponseCodeException {

        // creating the body with the task token
        JSONObject jsonToken = new JSONObject();
        jsonToken.put(Blackboard.getInstance().getUrl() + task.get_links().getSelf(), task.getToken());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tokens", jsonToken);


        HttpResponse response = this.httpAccess.post(
                API.BLACKBOARD_QUESTS +"/" + task.getQuest() + "/deliveries",
                Blackboard.getInstance().getUser().getUserToken(),
                jsonObject.toString()
        );

        if (response.getCode() != 201) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObjectList(response.getBody(), "object", Delivery.class);

    }

    @Override
    public Visit deliverTaskPart(TaskPart taskpart) throws UnexpectedResponseCodeException {

        // creating the body with the tokens from the steps
        List<String> tokenList = new ArrayList<String>();
        for (Step step : taskpart.getStepList()) {
            tokenList.add(step.getToken().getToken());
        }

        String tokens = new JSONObject().put("tokens", tokenList).toString();

        HttpResponse response = this.httpAccess.post("http://" + taskpart.getDeliverUri(), Blackboard.getInstance().getUser().getUserToken(), tokens);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Visit.class);

    }


}
