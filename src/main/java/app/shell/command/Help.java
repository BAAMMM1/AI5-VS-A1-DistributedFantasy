package app.shell.command;

import app.shell.UnAcceptedStateException;
import app.shell.command.exception.ParameterTooManyException;
import app.shell.command.state.State;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Help implements Command {

    private static final String COMMAND_NAME = "help";
    private static final int NUMBER_OF_PARAMETER = 0;

    private List<String> parameter;


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
    public void checkState(State state) throws UnAcceptedStateException{

        // Hier kann auf einen State, viele States oder gar keinen State verglichen werden.


    }


    @Override
    public State execute(State state) {

        // use businesslog

        System.out.println("");
        System.out.println("# command --- # parameter ------- # discription --------------------------------------");
        System.out.println("  -help                             displays all possible commands");
        System.out.println("  -reg          <user> <password>   registered a new user");
        System.out.println("  -exit                             terminates the application");
        System.out.println("");

        // return null, if appContext not Change
        return state;
    }
}
