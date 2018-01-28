package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer;

import app.Application;
import app.configuration.API;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.*;
import app.layerLogicAndService.cmpService.entity.hero.mutex.Mutex;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexMessage;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexRequest;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.service.HttpAccess;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpResponse;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.xlayerMiddleware.logicalClock.LamportClock;
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

    @Override
    public void sendMutexMessage(String mutexUrl, MutexRequest mutexRequest) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.post(mutexUrl, gson.toJson(mutexRequest));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }

    }

    @Override
    public void sendMutexMessage(String url, MutexMessage type, int fixedTime, String reply) throws UnexpectedResponseCodeException {

        LamportClock.getInstance().tick();

        MutexRequest message = new MutexRequest(
                type.toString(),
                fixedTime,
                reply,
                API.USERS + "/" + Blackboard.getInstance().getUser().getName()
        );

        HttpResponse response = this.httpAccess.post(url, gson.toJson(message));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }
    }

    @Override
    public void sendMutexMessage(String url, MutexMessage type, String reply) throws UnexpectedResponseCodeException {

        LamportClock.getInstance().tick();

        MutexRequest message = new MutexRequest(
                type.toString(),
                LamportClock.getInstance().getTime(),
                reply,
                API.USERS + "/" + Blackboard.getInstance().getUser().getName()
        );

        HttpResponse response = this.httpAccess.post(url, gson.toJson(message));

        if (response.getCode() < 200 || response.getCode() >= 300) {
            throw new UnexpectedResponseCodeException(response);
        }
    }

    @Override
    public Mutex getMutexState(String heroMutexStateUrl) throws UnexpectedResponseCodeException {

        HttpResponse response = this.httpAccess.get(heroMutexStateUrl);

        if (response.getCode() != 200) {
            throw new UnexpectedResponseCodeException(response);
        }

        return gson.fromJson(response.getBody(), Mutex.class);

    }

}
