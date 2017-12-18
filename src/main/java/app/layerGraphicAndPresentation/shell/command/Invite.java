package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IToHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 02.12.2017
 */
public class Invite extends Command {

    IToHeroService heroToHeroService;

    public Invite(IToHeroService heroToHeroService) {
        this.heroToHeroService = heroToHeroService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        String invite = this.heroToHeroService.sendHiringForGroupToHero(this.getParameter().get(0),
                Integer.valueOf(this.getParameter().get(1)),
                Integer.valueOf(this.getParameter().get(2)),
                this.getParameter().get(3))
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
        return "  -invite\t[adventurer][groupid][taskid][message]\tinvites adventurere to group for task with message";
    }
}
