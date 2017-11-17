package app.shell.command.exception;

/**
 * @author Christian G. on 17.11.2017
 */
public class ParameterIsMissingException extends Throwable {

    public ParameterIsMissingException() {
        super("parameter is missing");
    }
}
