package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.blackboard.User;
import app.layerLogicAndService.cmpService.service.IBlackboardService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Login extends Command {

    private IBlackboardService client;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.NOT_LOGIN));

    private static final int PARAMETER_SIZE = 2;


    public Login(IBlackboardService client) {
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

        //System.out.println("instruction login with: user: " + this.getParameter().get(0) + " with password: " + this.getParameter().get(1));

        // Je nachdem was hier zurück kommt, entweder Ok oder nicht ok, ändere Status

        User user = this.client.login(this.getParameter().get(0), this.getParameter().get(1));

        // Prompt-Ausgabe
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
        return "  -login\t\t[user] [password]\t\tlogin with user and password";
    }


}
