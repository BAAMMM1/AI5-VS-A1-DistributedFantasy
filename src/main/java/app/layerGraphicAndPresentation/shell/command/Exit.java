package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.exception.ParameterTooManyException;
import app.layerGraphicAndPresentation.shell.state.State;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Exit implements Command {

    private static final String COMMAND_NAME = "exit";
    private static final int NUMBER_OF_PARAMETER = 0;

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void setParameter(List<String> param) throws ParameterTooManyException {

        if (param.size() != NUMBER_OF_PARAMETER) {
            throw new ParameterTooManyException();
        }

    }

    @Override
    public void checkState(State state) throws UnAcceptedStateException {


    }

    @Override
    public State execute(State state) {

        System.exit(1);

        return state;

    }
}
