package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpHeroToHero.service.IHeroToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 05.12.2017
 */
public class AssignmentDeliver extends Command {

    IHeroToHeroService heroToHeroService;

    public AssignmentDeliver(InputInterpreter inputInterpreter, IHeroToHeroService heroToHeroService) {
        super(inputInterpreter);
        this.heroToHeroService = heroToHeroService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        this.heroToHeroService.sendAssignmentDeliver();

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
