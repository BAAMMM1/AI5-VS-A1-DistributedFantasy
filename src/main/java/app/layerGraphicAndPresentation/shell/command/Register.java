package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpBlackboard.service.IBlackboardService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

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


    public Register(InputInterpreter inputInterpreter, IBlackboardService client) {
        super(inputInterpreter);
        this.client = client;
    }

    @Override
    public void checkState() throws UnAcceptedStateException {

        if (!acceptedStates.contains(Context.getInstance().getState())) {
            throw new UnAcceptedStateException();
        }

    }

    @Override
    State instruction() throws ErrorCodeException {

        app.layerLogicAndService.cmpBlackboard.entity.Register register = this.client.registerUser(this.getParameter().get(0), this.getParameter().get(1));

        System.out.println("name: " + register.getObject().get(0).getName());
        System.out.println("location: " + register.getObject().get(0).getLocation());
        System.out.println("ip: " + register.getObject().get(0).getIp());
        System.out.println("deliverables_done: " + register.getObject().get(0).getDeliverables_done());
        System.out.println("delivered: " + register.getObject().get(0).getDelivered());


        return null;

    }


    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -register     [user] [password]                registered a new user";
    }


}
