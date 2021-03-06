package app.layerGraphicAndPresentation.shell;

import app.Application;
import app.layerGraphicAndPresentation.shell.command.Command;
import app.layerGraphicAndPresentation.shell.exception.*;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class InputHandler {

    public final static Logger log = Logger.getLogger(new Object() { }.getClass().getEnclosingClass());

    InputInterpreter inputInterpreter;


    public InputHandler(InputInterpreter inputInterpreter) {
        this.inputInterpreter = inputInterpreter;

    }

    public void handleCommand(String in) {

        try {

            // TODO - KeyEvent

            Command command = inputInterpreter.interpret(in);
            List<String> param = inputInterpreter.interpretParam(in);

            log.info("execute command: " + command.getCommandName());
            command.execute(param);

        } catch (CommandNotExistsException | UnAcceptedStateException | ParameterIncorrectException | IllegalArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());

        } catch (UnexpectedResponseCodeException e) {
            System.out.println(e.getClass().getSimpleName() + ": code: " + e.getCode() + " message: " + e.getMessage());

        } catch (Exception e){
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
