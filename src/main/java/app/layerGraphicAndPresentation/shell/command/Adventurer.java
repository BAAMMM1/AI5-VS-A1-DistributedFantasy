package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 02.12.2017
 */
public class Adventurer extends Command {

    ITavernaService tavernaService;

    public Adventurer(ITavernaService tavernaService) {
        this.tavernaService = tavernaService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        app.layerLogicAndService.cmpService.entity.taverna.Adventurer adventurer = this.tavernaService.getAdventure(this.getParameter().get(0));

        System.out.println(adventurer.getUser());
        System.out.println(adventurer.getHeroclass());
        System.out.println(adventurer.getCapabilities());
        System.out.println(adventurer.getUrl());

        return null;
    }

    @Override
    int parameterSize() {
        return 1;
    }

    @Override
    String description() {
        return null;
    }
}
