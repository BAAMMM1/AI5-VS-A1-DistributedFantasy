package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 05.12.2017
 */
public class GroupOwn extends Command {

    public GroupOwn() {
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

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
