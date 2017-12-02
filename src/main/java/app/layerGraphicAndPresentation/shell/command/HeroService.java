package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerLogicAndService.cmpHeroToHero.service.IHeroToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.io.IOException;

/**
 * @author Chris on 02.12.2017
 */
public class HeroService extends Command {

    IHeroToHeroService heroToHeroService;

    public HeroService(InputInterpreter inputInterpreter, IHeroToHeroService heroToHeroService) {
        super(inputInterpreter);
        this.heroToHeroService = heroToHeroService;
    }

    @Override
    State instruction() throws ErrorCodeException, IOException, InterruptedException {

        Service service = this.heroToHeroService.getHeroService(this.getParameter().get(0),
                Integer.valueOf(this.getParameter().get(1)),
                this.getParameter().get(2));

        return null;
    }

    @Override
    int parameterSize() {
        return 3;
    }

    @Override
    String description() {
        return null;
    }
}
