package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.layerLogicAndService.cmpService.entity.quest.Visit;

import java.io.IOException;

/**
 * @author Chris on 25.11.2017
 */
public class Step extends Command{

    private IQuestService client;

    private static final int PARAMETER_SIZE = 1;


    public Step(IQuestService client) {
        this.client = client;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        Visit visit = this.client.step(Integer.valueOf(this.getParameter().get(0)));

        System.out.println("message:" + visit.getMessage());

        if (visit.getNext() != null) {
            System.out.println("next: " + visit.getNext());
        }

        if (visit.getSteps_todo() != null) {
            System.out.println("steps_todo: " + visit.getSteps_todo());
        }

        System.out.println("required_players: " + visit.getRequired_players());
        System.out.println("required_tokens: " + visit.getRequired_tokens());

        if (visit.getToken_name() != null) {
            System.out.println("token_name: " + visit.getToken_name());
        }

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -step\t\t\t\t\t\t\tdo next queststep";
    }
}
