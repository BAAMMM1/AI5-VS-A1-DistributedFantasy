package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.state.State;

/**
 * @author Christian G. on 17.11.2017
 */
public class Help extends Command {

    private static final int PARAMETER_SIZE = 0;


    public Help(Interpreter interpreter) {
        super(interpreter);
    }


    @Override
    State instruction() {

        System.out.println("");
        System.out.println("# command --- # parameter ------- # description -------------------------------------- # accepted State -------");

        for (Command command : this.getInterpreter().getRegisterCommands()) {
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
        return "  -help                             displays all possible commands";
    }


}
