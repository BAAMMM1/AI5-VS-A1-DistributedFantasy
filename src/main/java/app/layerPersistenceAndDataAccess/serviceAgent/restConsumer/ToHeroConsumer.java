package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.service.HttpAccess;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import com.google.gson.Gson;

/**
 * @author Chris on 02.12.2017
 */
public class ToHeroConsumer implements IToHeroConsumer {

    private HttpAccess httpAccess;
    private Gson gson;

    public ToHeroConsumer() {
        this.httpAccess = new HttpAccess();
        this.gson = new Gson();
    }

    @Override
    public Service getHeroService(String heroServiceUrl) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(heroServiceUrl);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Service.class);

    }

    @Override
    public String sendHiring(Hiring hiring, String herHiringUrl) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(herHiringUrl, gson.toJson(hiring));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

        return response.getBody();
    }

    @Override
    public void sendMessage(Message message, String heroMessageUrl) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(heroMessageUrl, gson.toJson(message));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

    }

    @Override
    public void sendAssignment(String heroAssignmentUrl, Assignment assignment) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(heroAssignmentUrl, gson.toJson(assignment));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

    }

    @Override
    public void sendAssignmentDeliver(String assignmentDeliverUrl, AssignmentDeliver assignmentDeliver) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(assignmentDeliverUrl, gson.toJson(assignmentDeliver));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

    }

    @Override
    public void sendElection(String electionUrl, Election election) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(electionUrl, gson.toJson(election));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

    }

}
