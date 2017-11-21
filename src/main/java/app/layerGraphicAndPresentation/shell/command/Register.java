package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerGraphicAndPresentation.shell.state.State;
import app.layerLogicAndService.cmpAccount.IAccountService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Register extends Command {

    private IAccountService client;

    private static final int PARAMETER_SIZE = 2;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.NOT_LOGIN));


    public Register(Interpreter interpreter, IAccountService client) {
        super(interpreter);
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

        RegisterUserDTO dto = this.client.registerUser(this.getParameter().get(0), this.getParameter().get(1));

        System.out.println(dto.toString());


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
