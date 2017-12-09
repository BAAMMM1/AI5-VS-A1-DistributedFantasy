package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.Command;
import app.layerGraphicAndPresentation.shell.exception.*;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class InputHandler {

    InputInterpreter inputInterpreter;


    public InputHandler(InputInterpreter inputInterpreter) {
        this.inputInterpreter = inputInterpreter;

    }

    public void handleCommand(String in) {

        try {

            // TODO - KeyEvent

            Command command = inputInterpreter.interpret(in);
            List<String> param = inputInterpreter.interpretParam(in);

            command.execute(param);

        } catch (CommandNotExistsException | UnAcceptedStateException | ParameterIncorrectException | IllegalArgumentException e) {
            System.out.println(Context.getInstance().getPromptState() + e.getMessage());

        } catch (UnexpectedResponseCodeException e) {
            System.out.println(e.getClass().getSimpleName() + ": code: " + e.getCode() + " message: " + e.getMessage());

        } catch (Exception e){
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
