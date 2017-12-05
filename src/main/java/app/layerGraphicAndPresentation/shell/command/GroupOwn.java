package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.io.IOException;

/**
 * @author Chris on 05.12.2017
 */
public class GroupOwn extends Command {
    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public GroupOwn(InputInterpreter inputInterpreter) {
        super(inputInterpreter);
    }

    @Override
    State instruction() throws ErrorCodeException, IOException, InterruptedException {

        System.out.println(Blackboard.getInstance().getUser().getGroup());

        return null;
    }

    @Override
    int parameterSize() {
        return 0;
    }

    @Override
    String description() {
        return null;
    }
}
