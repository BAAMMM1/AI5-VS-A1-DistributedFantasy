package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 18.12.2017
 */
public class Live extends Command{

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException, NotInGroupException {

        Blackboard.getInstance().getUser().setDead(false);
        System.out.println("you live! you will participate in an elction");

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
