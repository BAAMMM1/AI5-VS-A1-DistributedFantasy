package app.shell.command.exception;

/**
 * @author Christian G. on 17.11.2017
 */
public class ParameterTooManyException extends Throwable {

    public ParameterTooManyException() {
        super("too many parameter");
    }
}
