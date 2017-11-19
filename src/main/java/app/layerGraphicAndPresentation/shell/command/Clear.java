package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.state.State;

/**
 * @author Christian G. on 17.11.2017
 */
public class Clear extends Command {


    private static final int PARAMETER_SIZE = 0;

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }


    public Clear(Interpreter interpreter) {
        super(interpreter);
    }


    @Override
    State instruction() {

        System.out.flush();

        return null;
    }
}
