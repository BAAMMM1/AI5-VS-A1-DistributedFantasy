package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerLogicAndService.cmpService.service.IToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 02.12.2017
 */
public class Invite extends Command {

    IToHeroService heroToHeroService;

    public Invite(IToHeroService heroToHeroService) {
        this.heroToHeroService = heroToHeroService;
    }

    @Override
    public void checkParam(List<String> param) throws ParameterIncorrectException {

        if (param.size() < this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }

    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        String message = "";

        for(int i = 3; i < this.getParameter().size(); i++){
            message = message + this.getParameter().get(i) + " ";
        }

        message.substring(0, message.length()-1);


        String invite = this.heroToHeroService.sendHiringForGroupToHero(this.getParameter().get(0),
                Integer.valueOf(this.getParameter().get(1)),
                Integer.valueOf(this.getParameter().get(2)),
                message)
                ;
        System.out.print(invite);

        return null;
    }

    @Override
    int parameterSize() {
        return 4;
    }

    @Override
    String description() {
        return "  -invite\t\t[heroname][grpid][taskid][msg]\tinvites adventurerer to group for task with message";
    }
}
