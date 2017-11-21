package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.state.State;

/**
 * @author Christian G. on 17.11.2017
 */
public class Exit extends Command {

    private static final int PARAMETER_SIZE = 0;


    public Exit(Interpreter interpreter) {
        super(interpreter);
    }


    @Override
    State instruction() {

        System.exit(1);

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -exit                                          terminates the application";
    }
}
