package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 05.12.2017
 */
public class Services extends Command {

    IToHeroService toHeroService;

    public Services(IToHeroService toHeroService) {
        this.toHeroService = toHeroService;
    }


    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {


        this.toHeroService.getHerosServices();


        return null;
    }

    @Override
    int parameterSize() {
        return 0;
    }

    @Override
    String description() {
        return "  -services";
    }
}
