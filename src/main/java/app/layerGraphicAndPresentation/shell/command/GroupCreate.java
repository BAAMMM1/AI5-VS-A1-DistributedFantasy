package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 01.12.2017
 */
public class GroupCreate extends Command {

    ITavernaService tavernaService;

    public GroupCreate(ITavernaService tavernaService) {
        this.tavernaService = tavernaService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        Group group = this.tavernaService.createGroup();

        System.out.print("\n");
        System.out.println("######################################################################################");
        System.out.println("you are created the group: " + group.getId());
        System.out.println("######################################################################################");
        System.out.print("\n");
        System.out.println("groupowner: " + group.getOwner());
        System.out.println("groupmembers: " + group.getMembers());

        return null;
    }

    @Override
    int parameterSize() {
        return 0;
    }

    @Override
    String description() {
        return "  -groupcreate\t\t\t\t\t\tcreates a new group and deliver a groupID";
    }
}
