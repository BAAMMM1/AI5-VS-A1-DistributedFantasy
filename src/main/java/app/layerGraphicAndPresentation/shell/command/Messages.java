package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.hero.Message;
import app.layerLogicAndService.cmpService.service.fromHero.IFromHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 05.12.2017
 */
public class Messages extends Command{

    IFromHeroService heroService;

    public Messages(IFromHeroService heroService) {
        this.heroService = heroService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        List<Message> messageList = this.heroService.getMessages();

        for(Message message: messageList){
            System.out.println(message.getMessage());
        }

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
