package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.Command;
import app.layerGraphicAndPresentation.shell.exception.*;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class CommandHandler {

    Interpreter interpreter;


    public CommandHandler(Interpreter interpreter) {
        this.interpreter = interpreter;

    }

    public void handleCommand(String in) {

        try {
            Command command = interpreter.interpret(in);
            List<String> param = interpreter.interpretParam(in);

            command.execute(param);

        } catch (CommandNotExistsException | UnAcceptedStateException | ParameterIncorrectException | IllegalArgumentException e) {
            System.out.println(Context.getInstance().getPromptState() + e.getMessage());

        } catch (ErrorCodeException e) {
            System.out.println("message: " + e.getErrorDTO().getMessage());

        } catch (Exception e){
            System.out.println("message: " + e.getMessage());
        }
    }
}
