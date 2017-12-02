package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerLogicAndService.cmpTaverna.entity.Adventurer;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 02.12.2017
 */
public class GroupEnter extends Command{

    ITavernaService tavernaService;

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public GroupEnter(InputInterpreter inputInterpreter, ITavernaService tavernaService) {
        super(inputInterpreter);
        this.tavernaService = tavernaService;
    }

    @Override
    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException  {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }

        Integer.parseInt(param.get(0));

    }


    @Override
    State instruction() throws ErrorCodeException, IOException, InterruptedException {

        String response = this.tavernaService.enterGroup(Integer.parseInt(this.getParameter().get(0)));

        /*
        for(Adventurer adventurer: adventurerList){
            System.out.println(adventurer.getUser());
            System.out.println(adventurer.getHeroclass());
            System.out.println(adventurer.getCapabilities());
            System.out.println(adventurer.getUrl());
        }
        */

        System.out.println(response);


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