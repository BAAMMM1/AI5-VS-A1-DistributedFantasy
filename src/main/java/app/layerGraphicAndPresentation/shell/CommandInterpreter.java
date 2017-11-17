package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.Command;
import app.layerGraphicAndPresentation.shell.exception.CommandNotExistsException;
import app.layerGraphicAndPresentation.shell.exception.ParameterIsMissingException;
import app.layerGraphicAndPresentation.shell.exception.ParameterSyntaxIncorrektException;
import app.layerGraphicAndPresentation.shell.exception.ParameterTooManyException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class CommandInterpreter {

    List<Command> registerCommands;

    public CommandInterpreter() {
        this.registerCommands = new ArrayList<Command>();
    }

    public void registerCommands(List<Command> commands) {

        for (Command command : commands) {
            this.registerCommands.add(command);
        }

    }

    public Command interpret(String in) throws CommandNotExistsException, ParameterTooManyException, ParameterIsMissingException, ParameterSyntaxIncorrektException {

        String befehl;
        List<String> param = new ArrayList<String>();
        if (in.indexOf(" ") != -1) {
            befehl = in.substring(0, in.indexOf(" "));


            do {

                in = in.substring(in.indexOf(" ") + 1);

                if (in.indexOf(" ") != -1) {
                    param.add(in.substring(0, in.indexOf(" ")));
                    in = in.substring(in.indexOf(" "));
                } else {
                    param.add(in);
                }

            }
            while (in.indexOf(" ") != -1);


        } else {
            befehl = in;
        }


        //System.out.println("command substring: " + befehl);
        //System.out.println("parameter substring: " + param.toString());

        Command result = this.registerCommands.stream().filter(c -> c.getCommandName().equals(befehl)).findFirst().orElse(null);

        if (result != null) {
            result.setParameter(param);
            //System.out.println("command: " + result.getClass());

            return result;
        } else {
            throw new CommandNotExistsException(befehl);
        }
    }
}
