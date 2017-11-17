package app.shell.command;

import app.shell.UnAcceptedStateException;
import app.shell.command.exception.CommandNotExistsException;
import app.shell.command.exception.ParameterIsMissingException;
import app.shell.command.exception.ParameterSyntaxIncorrektException;
import app.shell.command.exception.ParameterTooManyException;
import app.shell.command.state.Context;
import app.shell.command.state.State;

import java.util.Arrays;

/**
 * @author Christian G. on 17.11.2017
 */
public class CommandHandler {

    Context context;

    Interpreter interpreter = new Interpreter();


    public CommandHandler(Context context) {
        this.context = context;
        interpreter.registerCommands(Arrays.asList(new Help(), new Register(), new Exit()));

    }

    public void handleCommand(String in) {

        try {
            Command command = interpreter.interpret(in);

            command.checkState(context.getState());

            context.setState(command.execute(context.getState()));

            System.out.println(this.context.getPromptState() + "State after command: " + context.getState());

        } catch (CommandNotExistsException e) {
            System.out.println(e.getMessage());

        } catch (ParameterTooManyException e) {
            System.out.println(e.getMessage());

        } catch (ParameterIsMissingException e) {
            System.out.println(e.getMessage());

        } catch (ParameterSyntaxIncorrektException e) {
            System.out.println(e.getMessage());

        } catch (UnAcceptedStateException e) {
            System.out.println(e.getMessage());
        }

    }
}
