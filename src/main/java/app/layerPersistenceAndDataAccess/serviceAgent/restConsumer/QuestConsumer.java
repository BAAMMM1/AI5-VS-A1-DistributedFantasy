package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.AssignmentDeliver;
import app.layerLogicAndService.cmpService.entity.quest.*;
import app.layerLogicAndService.cmpService.util.JSONUtil;
import app.layerLogicAndService.cmpService.entity.quest.questing.Step;
import app.layerLogicAndService.cmpService.entity.quest.questing.TaskPart;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.service.HttpAccess;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.configuration.API;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class QuestConsumer implements IQuestConsumer {

    private HttpAccess httpAccess;
    private Gson gson;

    public QuestConsumer() {
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
    public Answer answer(String ipPort, String ressource, String body) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post("http://" + ipPort + ressource, Blackboard.getInstance().getUser().getUserToken(), body);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Answer.class);

    }

    @Override
    public Answer postToURL(String url, String body) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(url, body);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Answer.class);

    }

    @Override
    public List<String> postData(String ipPort, String ressource, String body) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post("http://" + ipPort + ressource, Blackboard.getInstance().getUser().getUserToken(), body);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        List<String> strings = new ArrayList<String>();

        strings.add(JSONUtil.getObject(response.getBody(), "token", String.class));
        strings.add(JSONUtil.getObject(response.getBody(), "message", String.class));
        strings.add(JSONUtil.getObject(response.getBody(), "token_name", String.class));

        return strings;
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
        List<AssignmentDeliver> assignmentDeliverList = Blackboard.getInstance().getUser().getAssignmentDeliverList();
        if(!assignmentDeliverList.isEmpty()){
            for (AssignmentDeliver derliver: assignmentDeliverList){
                tokenList.add(derliver.getData());
            }
        }

        String tokens = new JSONObject().put("tokens", tokenList).toString();

        Blackboard.getInstance().getUser().removeAllAssignmentDerliver();
        Blackboard.getInstance().getUser().removeAllSendetAssignments();

        /*
        if(Blackboard.getInstance().getUser().getAssignmentDeliver() != null) {

            tokens = tokens.substring(0, tokens.length()-2);
            tokens = tokens + ", " +Blackboard.getInstance().getUser().getAssignmentDeliver().getData();
            tokens = tokens + "]}";
        }
       */



        //System.out.println(tokens);



        HttpResponse response = this.httpAccess.post("http://" + taskpart.getDeliverUri(), Blackboard.getInstance().getUser().getUserToken(), tokens);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Visit.class);

    }


    public void postToCritial() throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post2("http://172.19.0.8:5000/magicaltower/wand", Blackboard.getInstance().getUser().getUserToken(), " ");

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

    }



}
