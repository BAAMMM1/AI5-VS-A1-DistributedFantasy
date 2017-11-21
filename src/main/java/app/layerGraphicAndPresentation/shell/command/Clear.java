package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.state.State;

import java.io.IOException;

/**
 * @author Christian G. on 17.11.2017
 */
public class Clear extends Command {


    private static final int PARAMETER_SIZE = 0;


    public Clear(Interpreter interpreter) {
        super(interpreter);
    }


    @Override
    State instruction() throws IOException, InterruptedException {

        final String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        } else {

            System.out.print("\b\b\b\b\b");
            System.out.flush();

        }

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -clear                                         clear the console";
    }

}
