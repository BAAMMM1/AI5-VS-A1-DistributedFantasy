package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpHeroToHero.service.IHeroToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 02.12.2017
 */
public class Invite extends Command {

    IHeroToHeroService heroToHeroService;

    public Invite(InputInterpreter inputInterpreter, IHeroToHeroService heroToHeroService) {
        super(inputInterpreter);
        this.heroToHeroService = heroToHeroService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        String invite = this.heroToHeroService.invite(this.getParameter().get(0),
                Integer.valueOf(this.getParameter().get(1)),
                Integer.valueOf(this.getParameter().get(2)),
                this.getParameter().get(3))
                ;
        System.out.print(invite);

        return null;
    }

    @Override
    int parameterSize() {
        return 4;
    }

    @Override
    String description() {
        return null;
    }
}
