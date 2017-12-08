package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.UnexpectedResponseCodeException;
import app.layerLogicAndService.cmpQuest.entity.Visit;

import java.io.IOException;

/**
 * @author Chris on 25.11.2017
 */
public class Step extends Command{

    private IQuestService client;

    private static final int PARAMETER_SIZE = 1;

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public Step(InputInterpreter inputInterpreter, IQuestService client) {
        super(inputInterpreter);
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
        return null;
    }
}
