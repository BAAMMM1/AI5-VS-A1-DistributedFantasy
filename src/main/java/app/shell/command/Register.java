package app.shell.command;

import app.shell.command.Exception.ParameterIsMissingException;
import app.shell.command.Exception.ParameterTooManyParameterException;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Register implements Command {

    private static final String COMMAND_NAME = "reg";
    private static final int NUMBER_OF_PARAMETER = 2;

    private List<String> parameter;

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public void setParameter(List<String> param) throws ParameterTooManyParameterException, ParameterIsMissingException {

        if(param.size() < NUMBER_OF_PARAMETER){
            throw new ParameterIsMissingException();
        }

        if(param.size() > NUMBER_OF_PARAMETER){
            throw new ParameterTooManyParameterException();
        }

        // Parameter auf korrekte eingabe hier überprüfen

        this.parameter = param;

    }

    @Override
    public void execute() {

        System.out.println("register user: " + parameter.get(0) + " password: " + parameter.get(1));

    }
}
