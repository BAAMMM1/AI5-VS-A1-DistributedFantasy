package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 02.12.2017
 */
public class Adventurers extends Command{

    ITavernaService tavernaService;

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public Adventurers(InputInterpreter inputInterpreter, ITavernaService tavernaService) {
        super(inputInterpreter);
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
