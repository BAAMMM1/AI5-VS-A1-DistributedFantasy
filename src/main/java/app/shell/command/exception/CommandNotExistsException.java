package app.shell.command.exception;

/**
 * @author Christian G. on 17.11.2017
 */
public class CommandNotExistsException extends Throwable {

    public CommandNotExistsException(String command) {
        super("command not exists: " + command);
    }
}
