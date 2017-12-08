package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer;

import app.layerLogicAndService.cmpHero.entity.*;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.EnumHTTPMethod;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPCaller;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;
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
    public Service getHeroService(String heroServiceUrl) throws UnexpectedResponseCodeException {

        // Erstellen der Anfrage
        HTTPRequest httpRequest =
                new HTTPRequest(
                        heroServiceUrl,
                        EnumHTTPMethod.GET
                );


        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() != 200) {
            ErrorCodeDTO errorCodeDTO = gson.fromJson(response.getBody(), ErrorCodeDTO.class);

            throw new UnexpectedResponseCodeException(errorCodeDTO);

        } else {
            Service service = gson.fromJson(response.getBody(), Service.class);

            return service;
        }

    }

    @Override
    public String hiringHero(Hiring hiring, String herHiringUrl) throws UnexpectedResponseCodeException {


        HTTPRequest httpRequest =
                new HTTPRequest(
                        herHiringUrl,
                        EnumHTTPMethod.POST,
                        gson.toJson(hiring)
                );

        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() < 200 || response.getCode() >= 300) {

            throw new UnexpectedResponseCodeException(new ErrorCodeDTO("error", response.getBody()));

        } else {

            return response.getBody();
        }
    }

    @Override
    public void sendMessage(Message message, String heroMessageUrl) throws UnexpectedResponseCodeException {

        HTTPRequest httpRequest =
                new HTTPRequest(
                        heroMessageUrl,
                        EnumHTTPMethod.POST,
                        gson.toJson(message)
                );

        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() < 200 || response.getCode() >= 300) {

            throw new UnexpectedResponseCodeException(new ErrorCodeDTO("error", response.getBody()));

        } else {

        }

    }

    @Override
    public void sendAssignment(String heroAssignmentUrl, Assignment assignment) throws UnexpectedResponseCodeException {

        HTTPRequest httpRequest =
                new HTTPRequest(
                        heroAssignmentUrl,
                        EnumHTTPMethod.POST,
                        gson.toJson(assignment)
                );

        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() < 200 || response.getCode() >= 300) {

            throw new UnexpectedResponseCodeException(new ErrorCodeDTO("error", response.getBody()));

        } else {

        }

    }

    @Override
    public void sendAssignmentDeliver(String assignmentDeliverUrl, AssignmentDerliver assignmentDeliver) throws UnexpectedResponseCodeException {

        HTTPRequest httpRequest =
                new HTTPRequest(
                        assignmentDeliverUrl,
                        EnumHTTPMethod.POST,
                        gson.toJson(assignmentDeliver)
                );

        HTTPResponse response = this.httpCaller.call(httpRequest);


        if (response.getCode() < 200 || response.getCode() >= 300) {

            throw new UnexpectedResponseCodeException(new ErrorCodeDTO("error", response.getBody()));

        } else {

        }

    }

}
