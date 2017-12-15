package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 02.12.2017
 */
public class Adventurers extends Command{

    ITavernaService tavernaService;

    public Adventurers(ITavernaService tavernaService) {
        this.tavernaService = tavernaService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        List<Adventurer>  adventurers = this.tavernaService.getAdventurers();

        for(Adventurer adventurer: adventurers){
            System.out.println(adventurer.getUser());
            System.out.println(adventurer.getHeroclass());
            System.out.println(adventurer.getCapabilities());
            System.out.println(adventurer.getUrl());
        }

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
