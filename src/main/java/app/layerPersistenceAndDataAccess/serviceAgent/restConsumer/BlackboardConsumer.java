package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpService.entity.blackboard.User;
import app.layerLogicAndService.cmpService.entity.blackboard.Login;
import app.layerLogicAndService.cmpService.entity.blackboard.Register;
import app.layerLogicAndService.cmpService.util.JSONUtil;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.service.HttpAccess;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpResponse;
import app.configuration.API;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * Erstellt die HTTPAnfrage, ruft die Pfade der API auf und erhält die HttpResponse, welche JSON Objekte als entity´s
 * im Body enhalten
 *
 * @author Christian G. on 01.11.2017
 */
public class BlackboardConsumer implements IBlackboardConsumer {

    private HttpAccess httpAccess;
    private Gson gson;

    public BlackboardConsumer() {
        this.httpAccess = new HttpAccess();
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

        HttpResponse response = this.httpAccess.post(API.USERS, new JSONObject().put("name", name).put("password", password).toString());

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

        HttpResponse response = this.httpAccess.get(API.LOGIN, name, password);


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

        HttpResponse response = this.httpAccess.get(API.WHOAMI, token);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return JSONUtil.getObject(response.getBody(), "user", User.class);

    }



}
