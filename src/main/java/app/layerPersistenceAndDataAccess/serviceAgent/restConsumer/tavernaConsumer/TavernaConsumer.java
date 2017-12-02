package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.entity.Hero;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
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
    public Adventurer addHeroService(Hero hero) throws ErrorCodeException {


        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.ADVENTURERS,
                        EnumHTTPMethod.POST,
                        gson.toJson(hero)
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 201) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            AdventurerDTO dto = gson.fromJson(response.getBody(), AdventurerDTO.class);

            return dto.object.get(0);
        }

    }

    @Override
    public Group createGroup() throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS,
                        EnumHTTPMethod.POST,
                        "{}"
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 201) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            GroupDTO dto = gson.fromJson(response.getBody(), GroupDTO.class);

            return dto.object.get(0);
        }
    }

    @Override
    public String deleteGroup(int id) throws ErrorCodeException {
        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        Blackboard.getInstance().getUrl().toString() + PathTaverna.GROUPS + "/" + id,
                        EnumHTTPMethod.DELETE
                );
        httpRequest.setAuthorizationToken(Blackboard.getInstance().getUser().getUserToken());


        // Aufrufen des API´s Pfad
        HTTPResponse response = this.httpCaller.call(httpRequest);

        // TODO - Wenn 500 Fehler, dann nehme nicht als ErrorCodeDTO, bzw erstelle es selber

        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            ErrorCodeDTO dto = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            return dto.getMessage();
        }
    }

    private class AdventurerDTO{

        private String message;
        private List<Adventurer> object;
        private String status;

        public AdventurerDTO(String message, List<Adventurer> object, String status) {
            this.message = message;
            this.object = object;
            this.status = status;
        }

        public List<Adventurer> getObject() {
            return object;
        }

    }


    private class GroupDTO{

        private String message;
        private List<Group> object;
        private String status;

        public GroupDTO(String message, List<Group> object, String status) {
            this.message = message;
            this.object = object;
            this.status = status;
        }

        public List<Group> getObject() {
            return object;
        }
    }



}
