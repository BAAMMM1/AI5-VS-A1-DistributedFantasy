package app.layerPersistence.restConsumer.account;

import app.layerLogic.cmpBlackboard.Blackboard;
import app.layerPersistence.restConsumer.Exception.NotOKValueException;
import app.layerPersistence.httpAccess.EnumHTTPMethod;
import app.layerPersistence.httpAccess.HTTPCaller;
import app.layerPersistence.httpAccess.HTTPRequest;
import app.layerPersistence.httpAccess.HTTPResponse;
import app.layerPersistence.restConsumer.dto.ErrorDTO;
import app.layerPersistence.restConsumer.account.dto.RegisterUserDTO;
import app.layerPersistence.restConsumer.account.dto.UserTokenDTO;
import app.layerPersistence.restConsumer.account.dto.WhoamiDTO;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Erstellt die HTTPAnfrage, ruft die Pfade der API auf und erhält die HTTPResponse, welche JSON Objekte als dto´s im
 * Body enhalten
 *
 * @author Christian G. on 01.11.2017
 */
public class AccountConsumer implements IAccountConsumer {

    private static final String PATH_LOGIN = "/login";
    private static final String PATH_WHOAMI = "/whoami";

    private HTTPCaller httpCaller;
    private Gson gson;

    public AccountConsumer() {
        this.httpCaller = new HTTPCaller();
        this.gson = new Gson();
    }

    @Override
    public RegisterUserDTO registerUser(String name, String password) throws IOException {
        return null;
    }

    @Override
    public UserTokenDTO getAuthenticationToken(String name, String password) throws NotOKValueException {

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

                throw new NotOKValueException("user or password incorrect: " + errorDTO.getMessage());

            } else {
                UserTokenDTO userTokenDTO = gson.fromJson(response.getBody(), UserTokenDTO.class);

                return userTokenDTO;

            }


    }

    @Override
    public WhoamiDTO checkLogin(String token) throws IOException {

        // preconditions-check
        if (token == null) {
            throw new IllegalArgumentException("token must not be null");
        }

        // TODO - token size muss grüßer 0 sein

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PATH_WHOAMI,
                        EnumHTTPMethod.GET
                );
        httpRequest.setAuthorizationToken(token);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        System.out.println(response.toString());

        // Antwort prüfen
        WhoamiDTO whoamiDTO = null;

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);
            // throw new illegal Argument
            System.out.println(errorDTO.toString());
            return null;

        } else {
            whoamiDTO = gson.fromJson(response.getBody(), WhoamiDTO.class);
            System.out.println(whoamiDTO.toString());
            return whoamiDTO;

        }

    }


}
