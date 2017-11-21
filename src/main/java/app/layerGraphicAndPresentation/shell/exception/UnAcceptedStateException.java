package app.layerGraphicAndPresentation.shell.exception;

/**
 * @author Christian G. on 17.11.2017
 */
public class UnAcceptedStateException extends Throwable {

    public UnAcceptedStateException() {
        super("context unaccepted for command");
    }
}
