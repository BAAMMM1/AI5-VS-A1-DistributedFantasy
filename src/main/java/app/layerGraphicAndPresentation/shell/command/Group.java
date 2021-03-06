package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerLogicAndService.cmpService.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 02.12.2017
 */
public class Group extends Command{

    ITavernaService tavernaService;

    public Group(ITavernaService tavernaService) {
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
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        app.layerLogicAndService.cmpService.entity.taverna.Group group = this.tavernaService.getGroup(Integer.parseInt(this.getParameter().get(0)));


            System.out.println("id: " + group.getId());
            System.out.println("owner: " + group.getOwner());
            System.out.println("members: " + group.getMembers());
            //System.out.println("links: " + group.get_links());


        return null;
    }

    @Override
    int parameterSize() {
        return 1;
    }

    @Override
    String description() {
        return "  -group\t\t[groupID]\t\t\tshows information about a group";
    }
}
