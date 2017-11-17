package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.Command;
import app.layerGraphicAndPresentation.shell.command.Exit;
import app.layerGraphicAndPresentation.shell.command.Help;
import app.layerGraphicAndPresentation.shell.command.Register;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.exception.CommandNotExistsException;
import app.layerGraphicAndPresentation.shell.exception.ParameterIsMissingException;
import app.layerGraphicAndPresentation.shell.exception.ParameterSyntaxIncorrektException;
import app.layerGraphicAndPresentation.shell.exception.ParameterTooManyException;
import app.layerGraphicAndPresentation.shell.state.Context;

import java.util.Arrays;

/**
 * @author Christian G. on 17.11.2017
 */
public class CommandHandler {

    Context context;

    CommandInterpreter commandInterpreter = new CommandInterpreter();


    public CommandHandler(Context context, Command ...commands) {
        this.context = context;
        commandInterpreter.registerCommands(Arrays.asList(commands));

    }

    public void handleCommand(String in) {

        try {
            Command command = commandInterpreter.interpret(in);

            command.checkState(context.getState());

            context.setState(command.execute(context.getState()));

            //System.out.println(this.context.getPromptState() + "State after command: " + context.getState());

        } catch (CommandNotExistsException e) {
            System.out.println(context.getPromptState() + e.getMessage());

        } catch (ParameterTooManyException e) {
            System.out.println(context.getPromptState() + e.getMessage());

        } catch (ParameterIsMissingException e) {
            System.out.println(context.getPromptState() + e.getMessage());

        } catch (ParameterSyntaxIncorrektException e) {
            System.out.println(context.getPromptState() + e.getMessage());

        } catch (UnAcceptedStateException e) {
            System.out.println(context.getPromptState() + e.getMessage());
        }

    }
}
