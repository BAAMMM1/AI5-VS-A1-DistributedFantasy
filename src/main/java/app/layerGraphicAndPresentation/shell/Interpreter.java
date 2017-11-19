package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.Command;
import app.layerGraphicAndPresentation.shell.exception.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Interpreter {

    List<Command> registerExecutables;

    public Interpreter() {
        this.registerExecutables = new ArrayList<Command>();
    }


    public Command interpret(String in) throws CommandNotExistsException, ParameterIncorrectException {

        String befehl;
        List<String> param = new ArrayList<String>();

        if (in.indexOf(" ") != -1) {
            befehl = in.substring(0, in.indexOf(" "));

            /*
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
            */


        } else {
            befehl = in;
        }


        //System.out.println("command substring: " + befehl);
        //System.out.println("parameter substring: " + param.toString());

        Command result = this.registerExecutables.stream().filter(c -> c.getCommandName().equals(befehl)).findFirst().orElse(null);

        if (result != null) {
            //result.setParam(param);
            //System.out.println("command: " + result.getClass());

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

    public void registerCommand(Command command){
        this.registerExecutables.add(command);
    }
}
