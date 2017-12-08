package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpTaverna.entity.Group;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 01.12.2017
 */
public class GroupCreate extends Command {

    ITavernaService tavernaService;

    public GroupCreate(InputInterpreter inputInterpreter, ITavernaService tavernaService) {
        super(inputInterpreter);
        this.tavernaService = tavernaService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        Group group = this.tavernaService.createGroup();

        System.out.println(group.getId());
        System.out.println(group.getOwner());
        System.out.println(group.getMembers());
        System.out.println(group.get_links().toString());

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
