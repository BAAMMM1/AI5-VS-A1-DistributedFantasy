package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpHeroToHero.service.IHeroToHeroService;
import app.layerLogicAndService.cmpQuest.entity.Visit;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

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
    State instruction() throws ErrorCodeException, IOException, InterruptedException {

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
