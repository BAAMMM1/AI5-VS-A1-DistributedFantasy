package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.state.State;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Help extends Command {

    private static final int PARAMETER_SIZE = 0;

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String discreption() {
        return "  -help                             displays all possible commands";
    }


    public Help(Interpreter interpreter) {
        super(interpreter);
    }


    @Override
    State instruction() {
        // use businesslog

        System.out.println("");
        System.out.println("# command --- # parameter ------- # discription -------------------------------------- # accepted State --------------------------");

        for(Command command : this.getInterpreter().getRegisterCommands()){
            System.out.println(command.discreption());
        }

        System.out.println("");

        // return null, if appContext not Change
        return null;
    }


}
