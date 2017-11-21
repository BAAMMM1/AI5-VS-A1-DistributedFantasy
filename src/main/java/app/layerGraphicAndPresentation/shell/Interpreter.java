package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.Command;
import app.layerGraphicAndPresentation.shell.exception.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Interpreter {

    List<Command> registerCommands;

    public Interpreter() {
        this.registerCommands = new ArrayList<Command>();
    }


    public Command interpret(String in) throws CommandNotExistsException, ParameterIncorrectException {

        String befehl;

        if (in.indexOf(" ") != -1) {
            befehl = in.substring(0, in.indexOf(" "));

        } else {
            befehl = in;
        }


        Command result = this.registerCommands.stream().filter(c -> c.getCommandName().equals(befehl)).findFirst().orElse(null);

        if (result != null) {
            return result;

        } else {
            throw new CommandNotExistsException(befehl);
        }
    }

    public List<String> interpretParam(String in){

        List<String> param = new ArrayList<String>();

        if (in.indexOf(" ") != -1) {

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

            return param;


        } else {
            return param;
        }

    }

    public List<Command> getRegisterCommands() {
        return registerCommands;
    }

    public void registerCommand(Command command){
        this.registerCommands.add(command);
    }
}
