package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexState;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 18.12.2017
 */
public class Wanting extends Command{

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException, NotInGroupException {

        if(Blackboard.getInstance().getUser().getMutex().getState().equals(MutexState.RELEASED.toString())){
            System.out.println("set mutex-state to: " + MutexState.WANTING.toString());
            Blackboard.getInstance().getUser().getMutex().setState(MutexState.WANTING);
        } else {
            System.out.println("set mutex-state to: " + MutexState.RELEASED.toString());
            Blackboard.getInstance().getUser().getMutex().setState(MutexState.RELEASED);
        }


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
