package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerGraphicAndPresentation.shell.state.State;
import app.layerLogicAndService.cmpAccount.IAccountService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.LoginTokenDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonDto.ErrorDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Login extends Command {

    private IAccountService client;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.NOT_LOGIN));

    private static final int PARAMETER_SIZE = 2;


    public Login(Interpreter interpreter, IAccountService client) {
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

        System.out.println("instruction login with: user: " + this.getParameter().get(0) + " with password: " + this.getParameter().get(1));

        // Je nachdem was hier zurück kommt, entweder Ok oder nicht ok, ändere Status

        LoginTokenDTO dto = this.client.getAuthenticationToken(this.getParameter().get(0), this.getParameter().get(1));

        // Prompt-Ausgabe
        System.out.println("message: " + dto.getMessage());
        System.out.println("token: " + dto.getToken());
        System.out.println("valid till: " + dto.getValid_till());

        return State.LOGIN;


    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }


    @Override
    String description() {
        return "  -login        [user] [password]   login with user and password";
    }


}
