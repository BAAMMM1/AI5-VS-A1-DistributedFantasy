package app.shell.command;

import app.shell.command.Exception.ParameterIsMissingException;
import app.shell.command.Exception.ParameterTooManyParameterException;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public interface Command {

    String getName();

    void setParameter(List<String> param) throws ParameterTooManyParameterException, ParameterIsMissingException;

    void execute();

}
