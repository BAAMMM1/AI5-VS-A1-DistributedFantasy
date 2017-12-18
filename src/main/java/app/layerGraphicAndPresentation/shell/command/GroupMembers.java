package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 02.12.2017
 */
public class GroupMembers extends Command {

    ITavernaService tavernaService;

    public GroupMembers(ITavernaService tavernaService) {
        this.tavernaService = tavernaService;
    }

    @Override
    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }

        Integer.parseInt(param.get(0));

    }


    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        List<Adventurer> adventurerList = this.tavernaService.getGroupMembers(Integer.parseInt(this.getParameter().get(0)));

        for (Adventurer adventurer : adventurerList) {
            System.out.println(adventurer.getUser());
            System.out.println(adventurer.getHeroclass());
            System.out.println(adventurer.getCapabilities());
            System.out.println(adventurer.getUrl());
        }


        return null;
    }

    @Override
    int parameterSize() {
        return 1;
    }

    @Override
    String description() {
        return "  -groupmembers\t\t\t\t\t\tshows all members of a group";
    }
}
