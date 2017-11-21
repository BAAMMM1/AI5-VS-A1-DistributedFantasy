package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.Command;
import app.layerGraphicAndPresentation.shell.exception.*;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;

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

        } catch (ErrorCodeException e) {
            System.out.println("message: " + e.getErrorDTO().getMessage());

        } catch (ErrorDeliverCodeException e) {
            System.out.println("message: " + e.getErrorDelivorDTO().toString());

        } catch (Exception e){
            System.out.println("message: " + e.getMessage());
        }
    }
}
