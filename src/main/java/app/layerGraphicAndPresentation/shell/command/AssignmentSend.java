package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 05.12.2017
 */
public class AssignmentSend extends Command {

    IToHeroService heroToHeroService;

    public AssignmentSend(IToHeroService heroToHeroService) {
        this.heroToHeroService = heroToHeroService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        // TODO - Message richtig entgegen nehmen - example(statt helloStranger -> hello Stranger)
        this.heroToHeroService.sendAssignment(this.getParameter().get(0), this.getParameter().get(1));

        return null;
    }

    @Override
    int parameterSize() {
        return 2;
    }

    @Override
    String description() {
        return null;
    }
}
