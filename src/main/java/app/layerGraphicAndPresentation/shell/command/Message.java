package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerLogicAndService.cmpService.service.IToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 05.12.2017
 */
public class Message extends Command{

    IToHeroService heroToHeroService;

    public Message(IToHeroService heroToHeroService) {
        this.heroToHeroService = heroToHeroService;
    }

    public void checkParam(List<String> param) throws ParameterIncorrectException {

        if (param.size() < this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }

    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        String message = "";

        for(int i = 1; i < this.getParameter().size(); i++){
            message = message + this.getParameter().get(i) + " ";
        }

        message.substring(0, message.length()-1);

        this.heroToHeroService.sendMessage(this.getParameter().get(0), message);

        return null;
    }

    @Override
    int parameterSize() {
        return 2;
    }

    @Override
    String description() {
        return null;
    }
}
