package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;

/**
 * @author Christian G. on 17.11.2017
 */
public class Exit extends Command {

    private static final int PARAMETER_SIZE = 0;

    public Exit() {
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
        return "  -exit\t\t\t\t\t\t\tterminates the application";
    }
}
