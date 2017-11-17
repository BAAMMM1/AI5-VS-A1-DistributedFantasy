package app.shell;

/**
 * @author Christian G. on 17.11.2017
 */
public class UnAcceptedStateException extends Throwable {

    public UnAcceptedStateException() {
        super("state unaccepted for command");
    }
}
