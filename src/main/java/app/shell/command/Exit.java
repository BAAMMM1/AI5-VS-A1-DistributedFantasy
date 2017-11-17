package app.shell.command;

import app.shell.command.Exception.ParameterTooManyParameterException;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Exit implements Command {

    private static final String COMMAND_NAME = "exit";
    private static final int NUMBER_OF_PARAMETER = 0;

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public void setParameter(List<String> param) throws ParameterTooManyParameterException {

        if (param.size() != NUMBER_OF_PARAMETER) {
            throw new ParameterTooManyParameterException();
        }

    }

    @Override
    public void execute() {

        System.exit(1);

    }
}
