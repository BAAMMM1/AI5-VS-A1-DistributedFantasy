package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerLogicAndService.cmpService.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 02.12.2017
 */
public class Groups extends Command{

    ITavernaService tavernaService;

    public Groups(ITavernaService tavernaService) {
        this.tavernaService = tavernaService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        List<Group> groups = this.tavernaService.getGroups();

        for(Group group: groups){
            System.out.println("id: " + group.getId());
            System.out.println("owner: " + group.getOwner());
            System.out.println("members: " + group.getMembers());
            //System.out.println("links: " + group.get_links());
        }

        return null;
    }

    @Override
    int parameterSize() {
        return 0;
    }

    @Override
    String description() {
        return "  -groups\t\t\t\t\t\t\t\t\t\tshows all groups registered in taverna";
    }
}
