package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.LoginTokenDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.dto.ErrorDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.WhoamiDTO;
import com.google.gson.Gson;

/**
 * Erstellt die HTTPAnfrage, ruft die Pfade der API auf und erhält die HTTPResponse, welche JSON Objekte als dto´s
 * im Body enhalten
 *
 * @author Christian G. on 01.11.2017
 */
public class BlackboardConsumer implements IBlackboardConsumer {

    private static final String PATH_LOGIN = "/login";
    private static final String PATH_WHOAMI = "/whoami";
    private static final String PATH_USERS = "/users";

    private HTTPCaller httpCaller;
    private Gson gson;

    public BlackboardConsumer() {
        this.httpCaller = new HTTPCaller();
        this.gson = new Gson();
    }

    @Override
    public RegisterUserDTO registerUser(String name, String password) throws ErrorCodeException {

        // preconditions-check
        if (name == null) {
            throw new IllegalArgumentException("getCommandName must not be null");
        }

        if (password == null) {
            throw new IllegalArgumentException("password must not be null");
        }

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PATH_USERS,
                        EnumHTTPMethod.POST,
                        "{ \"name\":\"" + name + "\",\"password\":\"" + password + "\"}"
                );


        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 201 || response == null) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            RegisterUserDTO dto = gson.fromJson(response.getBody(), RegisterUserDTO.class);

            return dto;

        }

    }

    @Override
    public LoginTokenDTO getAuthenticationToken(String name, String password) throws ErrorCodeException {

        // preconditions-check
        if (name == null) {
            throw new IllegalArgumentException("getCommandName must not be null");
        }

        if (password == null) {
            throw new IllegalArgumentException("password must not be null");
        }

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PATH_LOGIN,
                        EnumHTTPMethod.GET
                );
        httpRequest.setBasicAuth(name, password);

        // Aufrufen des API´s Pfad

        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200 || response == null) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            LoginTokenDTO userTokenDTO = gson.fromJson(response.getBody(), LoginTokenDTO.class);

            return userTokenDTO;

        }


    }

    @Override
    public WhoamiDTO checkLogin(String token) throws ErrorCodeException {

        // preconditions-check
        if (token == null) {
            throw new IllegalArgumentException("token must not be null");
        }

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PATH_WHOAMI,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        //System.out.println(response.toString());

        // Antwort prüfen

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);

            throw new ErrorCodeException(errorDTO);

        } else {
            WhoamiDTO whoamiDTO = gson.fromJson(response.getBody(), WhoamiDTO.class);

            return whoamiDTO;

        }

    }


}