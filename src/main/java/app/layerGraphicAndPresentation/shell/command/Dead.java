package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 18.12.2017
 */
public class Dead extends Command{

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException, NotInGroupException {

        Blackboard.getInstance().getUser().setDead(true);
        System.out.println("you are dead! you will no longer participate in an elction");

        return null;
    }

    @Override
    int parameterSize() {
        return 0;
    }

    @Override
    String description() {
        return "  -dead\t\t\t\t\t\t\tset the current user to dead";
    }

}
