package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IBlackboardService;
import app.layerLogicAndService.cmpService.entity.blackboard.User;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Register extends Command {

    private IBlackboardService client;

    private static final int PARAMETER_SIZE = 2;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.NOT_LOGIN));


    public Register(IBlackboardService client) {
        this.client = client;
    }

    @Override
    public void checkState() throws UnAcceptedStateException {

        if (!acceptedStates.contains(Context.getInstance().getState())) {
            throw new UnAcceptedStateException();
        }

    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, UnknownHostException {

        User user = this.client.registerUser(this.getParameter().get(0), this.getParameter().get(1));

        System.out.println("register successful");
        System.out.println("login successful: " + user.getName());
        System.out.println("deliverables done: " + user.getDeliverables_done());
        System.out.println("delivered: " + user.getDelivered());


        return State.LOGIN;

    }


    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -register\t\t[user] [password]\t\tregistered a new user";
    }


}
