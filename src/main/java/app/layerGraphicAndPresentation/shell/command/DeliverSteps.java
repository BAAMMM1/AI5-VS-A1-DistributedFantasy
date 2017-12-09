package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerLogicAndService.cmpService.service.quest.IQuestService;
import app.layerLogicAndService.cmpService.entity.quest.Visit;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 20.11.2017
 */
public class DeliverSteps extends Command {

    private IQuestService client;

    // TODO - Deliveries funktioniert noch nicht
    private static final int PARAMETER_SIZE = 0;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    public DeliverSteps(IQuestService client) {
        this.client = client;
    }

    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }


    }

    @Override
    public void checkState() throws UnAcceptedStateException {

        if (!acceptedStates.contains(Context.getInstance().getState())) {
            throw new UnAcceptedStateException();
        }

    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        Visit visit = this.client.deliverTaskPart();

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
        return "  -deliverTask      [quest-id] [task-uri] [token]    deliverTask a token for a quest";
    }
}
