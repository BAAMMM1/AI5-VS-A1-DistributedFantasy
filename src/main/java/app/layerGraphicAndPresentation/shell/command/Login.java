package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.blackboard.User;
import app.layerLogicAndService.cmpService.service.blackboard.IBlackboardService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

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
    State instruction() throws UnexpectedResponseCodeException {

        //System.out.println("instruction login with: user: " + this.getParameter().get(0) + " with password: " + this.getParameter().get(1));

        // Je nachdem was hier zurück kommt, entweder Ok oder nicht ok, ändere Status

        User user = this.client.login(this.getParameter().get(0), this.getParameter().get(1));

        // Prompt-Ausgabe
        System.out.println("name: " + user.getName());
        System.out.println("location: " + user.getLocation());
        System.out.println("ip: " + user.getIp());
        System.out.println("deliverables_done: " + user.getDeliverables_done());
        System.out.println("delivered: " + user.getDelivered());
        System.out.println("_links: " + user.get_links().toString());


        return State.LOGIN;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }


    @Override
    String description() {
        return "  -login        [user] [password]                login with user and password";
    }


}
