package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.heroToHero.IHeroToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 05.12.2017
 */
public class Message extends Command{

    IHeroToHeroService heroToHeroService;

    public Message(IHeroToHeroService heroToHeroService) {
        this.heroToHeroService = heroToHeroService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        String message = "";

        for(int i = 1; i < this.getParameter().size(); i++){
            message = message + this.getParameter().get(i);
        }

        this.heroToHeroService.sendMessage(this.getParameter().get(0), message);

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
