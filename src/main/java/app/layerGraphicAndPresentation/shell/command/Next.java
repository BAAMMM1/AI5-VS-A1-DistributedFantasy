package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;
import app.layerLogicAndService.cmpService.entity.quest.Visit;

import java.io.IOException;

/**
 * @author Chris on 25.11.2017
 */
public class Next extends Command{

    private IQuestService client;

    private static final int PARAMETER_SIZE = 0;

    public Next(IQuestService client) {
        this.client = client;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        Visit visit = this.client.next();

        System.out.print("\n");
        System.out.println(visit.getMessage());
        System.out.print("\n");
        System.out.println("required_players: " + visit.getRequired_players());
        System.out.println("required_tokens: " + visit.getRequired_tokens());
        System.out.print("\n");

        if (visit.getNext() != null) {
            System.out.println("next: " + visit.getNext());
        }

        if (visit.getSteps_todo() != null) {
            System.out.println("steps_todo: " + visit.getSteps_todo());
        }

        if (visit.getToken_name() != null) {
            System.out.println("token_name: " + visit.getToken_name());
        }

        if(visit.getCritical_section() != null) {
            System.out.println("critical_section: " + visit.getCritical_section());
        }

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -next\t\t\t\t\t\t\tgo to next quest location";
    }
}
