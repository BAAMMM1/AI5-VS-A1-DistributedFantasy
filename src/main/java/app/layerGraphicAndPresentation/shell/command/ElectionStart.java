package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.exception.NotInGroupException;
import app.layerLogicAndService.cmpService.service.IToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 11.12.2017
 */
public class ElectionStart extends Command {

    IToHeroService toHeroService;

    public ElectionStart(IToHeroService toHeroService) {
        this.toHeroService = toHeroService;
    }

    @Override
        State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException, NotInGroupException {

        this.toHeroService.startElection();

        return null;
    }

    @Override
    int parameterSize() {
        return 0;
    }

    @Override
    String description() {
        return "  -electionstart\t\t\t\t\tstarts an election for new coordinator in a group";
    }
}
