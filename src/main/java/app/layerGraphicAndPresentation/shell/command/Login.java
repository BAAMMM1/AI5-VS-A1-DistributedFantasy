package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.exception.ParameterIsMissingException;
import app.layerGraphicAndPresentation.shell.exception.ParameterTooManyException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.state.State;
import app.layerLogic.cmpAccount.IAccountClient;
import app.layerPersistence.restConsumer.Exception.NotOKValueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Login implements Command {

    private IAccountClient client;

    private static final String COMMAND_NAME = "login";
    private static final int NUMBER_OF_PARAMETER = 2;

    private List<String> parameter;
    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.NOT_LOGIN));

    public Login(IAccountClient client) {
        this.client = client;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void setParameter(List<String> param) throws ParameterTooManyException, ParameterIsMissingException {

        if(param.size() < NUMBER_OF_PARAMETER){
            throw new ParameterIsMissingException();
        }

        if(param.size() > NUMBER_OF_PARAMETER){
            throw new ParameterTooManyException();
        }

        // Parameter auf korrekte eingabe hier überprüfen

        this.parameter = param;

    }

    @Override
    public void checkState(State state) throws UnAcceptedStateException {

        if(!acceptedStates.contains(state)){
            throw new UnAcceptedStateException();
        }

    }


    @Override
    public State execute(State state) {

        System.out.println("execute login with: user: " + parameter.get(0) + " with password: " + parameter.get(1));

        // Je nachdem was hier zurück kommt, entweder Ok oder nicht ok, ändere Status
        try {
            this.client.getAuthenticationToken(parameter.get(0), parameter.get(1));
            return State.LOGIN;

        } catch (NotOKValueException e) {
            System.out.println(e.getMessage());
            return state;

        }

    }
}
