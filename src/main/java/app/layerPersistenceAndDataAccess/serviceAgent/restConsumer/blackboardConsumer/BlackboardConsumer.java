package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpBlackboard.entity.User;
import app.layerLogicAndService.cmpBlackboard.entity.Login;
import app.layerLogicAndService.cmpBlackboard.entity.Register;
import app.layerLogicAndService.cmpBlackboard.util.JSONUtil;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer.API;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * Erstellt die HTTPAnfrage, ruft die Pfade der API auf und erhält die HTTPResponse, welche JSON Objekte als entity´s
 * im Body enhalten
 *
 * @author Christian G. on 01.11.2017
 */
public class BlackboardConsumer implements IBlackboardConsumer {

    private HTTPCaller httpCaller;
    private Gson gson;

    public BlackboardConsumer() {
        this.httpCaller = new HTTPCaller();
        this.gson = new Gson();
    }

    // TODO - Return User
    @Override
    public Register registerUser(String name, String password) throws UnexpectedResponseCodeException {

        // preconditions-check
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }

        if (password == null) {
            throw new IllegalArgumentException("password must not be null");
        }

        HTTPResponse response = this.httpCaller.post(API.USERS, new JSONObject().put("name", name).put("password", password).toString());

        if (response.getCode() != 201) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Register.class);


    }

    // TODO - Return User
    @Override
    public Login getAuthenticationToken(String name, String password) throws UnexpectedResponseCodeException {

        // preconditions-check
        if (name == null) {
            throw new IllegalArgumentException("getCommandName must not be null");
        }

        if (password == null) {
            throw new IllegalArgumentException("password must not be null");
        }

        HTTPResponse response = this.httpCaller.get(API.LOGIN, name, password);


        if (response.getCode() != 200 || response == null) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Login.class);

    }

    @Override
    public User checkLogin(String token) throws UnexpectedResponseCodeException {

        // preconditions-check
        if (token == null) {
            throw new IllegalArgumentException("token must not be null");
        }

        HTTPResponse response = this.httpCaller.get(API.WHOAMI, token);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "user", User.class);

    }



}
