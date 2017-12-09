package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;

/**
 * @author Christian G. on 17.11.2017
 */
public class Help extends Command {

    InputInterpreter inputInterpreter;

    private static final int PARAMETER_SIZE = 0;


    public Help(InputInterpreter inputInterpreter) {
        this.inputInterpreter = inputInterpreter;
    }


    @Override
    State instruction() {

        System.out.println("");
        System.out.println("# command --- # parameter -------------------- # description --------------------------------------");

        for (Command command : this.inputInterpreter.getRegisterCommands()) {
            System.out.println(command.description());
        }

        System.out.println("");

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -help                                          displays all possible commands";
    }


}
