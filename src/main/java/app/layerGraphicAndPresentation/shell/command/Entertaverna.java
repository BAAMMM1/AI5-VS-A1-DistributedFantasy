package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 01.12.2017
 */
public class Entertaverna extends Command{

    ITavernaService tavernaService;

    public Entertaverna(InputInterpreter inputInterpreter, ITavernaService tavernaService) {
        super(inputInterpreter);
        this.tavernaService = tavernaService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        Adventurer response = this.tavernaService.addHeroServiceToTaverna();

        System.out.println("user:" + response.getUser());
        System.out.println("heroclass: " + response.getHeroclass());
        System.out.println("capabilities: " + response.getCapabilities());
        System.out.println("url: " + response.getUrl());

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
