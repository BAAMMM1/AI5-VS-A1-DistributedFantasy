package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerLogicAndService.cmpService.service.IToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 05.12.2017
 */
public class Services extends Command {

    IToHeroService heroToHeroService;

    public Services(IToHeroService heroToHeroService) {
        this.heroToHeroService = heroToHeroService;
    }


    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {


        this.heroToHeroService.getHerosServices();


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
