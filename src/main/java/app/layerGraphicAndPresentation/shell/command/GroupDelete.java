package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerLogicAndService.cmpService.service.ITavernaService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public class GroupDelete extends Command {

    ITavernaService tavernaService;

    public GroupDelete(ITavernaService tavernaService) {
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

        String result = this.tavernaService.deleteGroup(Integer.valueOf(this.getParameter().get(0)));

        System.out.println(result);

        return null;
    }

    @Override
    int parameterSize() {
        return 1;
    }

    @Override
    String description() {
        return "  -groupdelete\t\t[groupID]\t\t\tdelete a group dependent on his id and ownership";
    }
}
