package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.heroTohHeroConsumer;

import app.layerLogicAndService.cmpBlackboard.util.JSONUtil;
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

        HTTPResponse response = this.httpCaller.get(heroServiceUrl);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Service.class);

    }

    @Override
    public String hiringHero(Hiring hiring, String herHiringUrl) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.post(herHiringUrl, gson.toJson(hiring));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

        return response.getBody();
    }

    @Override
    public void sendMessage(Message message, String heroMessageUrl) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.post(heroMessageUrl, gson.toJson(message));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

    }

    @Override
    public void sendAssignment(String heroAssignmentUrl, Assignment assignment) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.post(heroAssignmentUrl, gson.toJson(assignment));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

    }

    @Override
    public void sendAssignmentDeliver(String assignmentDeliverUrl, AssignmentDerliver assignmentDeliver) throws UnexpectedResponseCodeException {

        HTTPResponse response = this.httpCaller.post(assignmentDeliverUrl, gson.toJson(assignmentDeliver));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

    }

}
