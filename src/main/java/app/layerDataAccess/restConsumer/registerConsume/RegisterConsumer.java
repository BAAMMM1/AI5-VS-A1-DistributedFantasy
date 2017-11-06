package app.layerDataAccess.restConsumer.registerConsume;

import app.layerBusinessLogic.cmpDiscover.IDiscoverClient;
import app.layerDataAccess.restConsumer.HTTP.EnumHTTPMethod;
import app.layerDataAccess.restConsumer.HTTP.HTTPCaller;
import app.layerDataAccess.restConsumer.HTTP.HTTPRequest;
import app.layerDataAccess.restConsumer.HTTP.HTTPResponse;
import app.layerDataAccess.restConsumer.registerConsume.DTO.ErrorDTO;
import app.layerDataAccess.restConsumer.registerConsume.DTO.RegisterUserDTO;
import app.layerDataAccess.restConsumer.registerConsume.DTO.UserTokenDTO;
import app.layerDataAccess.restConsumer.registerConsume.DTO.WhoamiDTO;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Erstellt die HTTPAnfrage, ruft die Pfade der API auf und erhält die HTTPResponse, welche JSON
 * Objekte als DTO´s im Body enhalten
 *
 * @author Christian G. on 01.11.2017
 */
public class RegisterConsumer implements IRegisterConsumer {

    private static final String PATH_LOGIN = "/login";
    private static final String PATH_WHOAMI = "/whoami";

    private HTTPCaller httpCaller;
    private Gson gson;
    private IDiscoverClient discoverClient;

    public RegisterConsumer(IDiscoverClient discoverClient) {
        this.httpCaller = new HTTPCaller();
        this.gson = new Gson();
        this.discoverClient = discoverClient;
    }

    @Override
    public RegisterUserDTO registerUser(String name, String password) throws IOException {
        return null;
    }

    @Override
    public UserTokenDTO getAuthenticationToken(String name, String password) throws IOException {

        // preconditions-check
        if(name == null){
            throw new IllegalArgumentException("name must not be null");
        }

        if(password == null){
            throw new IllegalArgumentException("password must not be null");
        }

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        discoverClient.getBlackboardURL().toString() + PATH_LOGIN,
                        EnumHTTPMethod.GET
                );
        httpRequest.setBasicAuth(name, password);

        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        System.out.println(response.toString());

        // Antwort prüfen
        UserTokenDTO userTokenDTO = null;

        if (response.getCode() != 200) {
            ErrorDTO errorDTO = gson.fromJson(response.getBody(), ErrorDTO.class);
            // throw new illegal Argument
            System.out.println(errorDTO.toString());
            return null;

        } else {
            userTokenDTO = gson.fromJson(response.getBody(), UserTokenDTO.class);
            System.out.println(userTokenDTO.toString());
            return userTokenDTO;

        }


    }

    @Override
        public WhoamiDTO checkLogin(String token) throws IOException {

        // preconditions-check
        if(token == null){
            throw new IllegalArgumentException("token must not be null");
        }

        // TODO - token size muss grüßer 0 sein

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        discoverClient.getBlackboardURL().toString() + PATH_WHOAMI,
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
