package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 02.12.2017
 */
public class Adventurer extends Command {

    ITavernaService tavernaService;

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public Adventurer(InputInterpreter inputInterpreter, ITavernaService tavernaService) {
        super(inputInterpreter);
        this.tavernaService = tavernaService;
    }


    @Override
    State instruction() throws ErrorCodeException, IOException, InterruptedException {

        app.layerLogicAndService.cmpTaverna.entity.Adventurer adventurer = this.tavernaService.getAdventure(this.getParameter().get(0));

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
