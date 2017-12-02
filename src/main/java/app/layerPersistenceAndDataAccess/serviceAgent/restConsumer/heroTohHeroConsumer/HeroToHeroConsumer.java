package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpHero.entity.Hiring;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer.PathTaverna;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.tavernaConsumer.TavernaConsumer;
import com.google.gson.Gson;

/**
 * @author Chris on 02.12.2017
 */
public class HeroToHeroConsumer implements IHeroToHeroConsumer {

    private HTTPCaller httpCaller;
    private Gson gson;

    public HeroToHeroConsumer() {
        this.httpCaller = new HTTPCaller();
        this.gson = new Gson();
    }

    @Override
    public Service getHeroService(String heroServiceUrl) throws ErrorCodeException {

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        heroServiceUrl,
                        EnumHTTPMethod.GET
                );


        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new ErrorCodeException(errorCodeDTO);

        } else {
            Service service = gson.fromJson(response.getBody(), Service.class);

            return service;
        }

    }

    @Override
    public String hiringHero(Hiring hiring, String herHiringUrl) throws ErrorCodeException {


        HTTPRequest httpRequest =
                new HTTPRequest(
                        herHiringUrl,
                        EnumHTTPMethod.POST,
                        gson.toJson(hiring)
                );

        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() < 200 || response.getCode() >= 300) {

            throw new ErrorCodeException(new ErrorCodeDTO("error", response.getBody()));

        } else {

            return response.getBody();
        }
    }

}
