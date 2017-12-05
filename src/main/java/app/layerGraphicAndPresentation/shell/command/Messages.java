package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.service.IHeroService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 05.12.2017
 */
public class Messages extends Command{

    IHeroService heroService;

    public Messages(InputInterpreter inputInterpreter, IHeroService heroService) {
        super(inputInterpreter);
        this.heroService = heroService;
    }

    @Override
    State instruction() throws ErrorCodeException, IOException, InterruptedException {

        List<Message> messageList = this.heroService.getMessages();

        for(Message message: messageList){
            System.out.print(message.getMessage());
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
