package app.shell.command;

import app.shell.UnAcceptedStateException;
import app.shell.command.exception.ParameterIsMissingException;
import app.shell.command.exception.ParameterTooManyException;
import app.shell.command.state.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Register implements Command {

    private static final String COMMAND_NAME = "reg";
    private static final int NUMBER_OF_PARAMETER = 2;

    private List<String> parameter;
    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.NOT_LOGIN));

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

        System.out.println("register user: " + parameter.get(0) + " password: " + parameter.get(1));

        // return not null, if context change
        return State.LOGIN;
    }
}
