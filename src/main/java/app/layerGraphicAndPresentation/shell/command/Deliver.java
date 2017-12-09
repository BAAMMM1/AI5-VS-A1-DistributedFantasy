package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.quest.Delivery;
import app.layerLogicAndService.cmpService.service.quest.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 20.11.2017
 */
public class Deliver extends Command {

    private IQuestService client;

    // TODO - Deliveries funktioniert noch nicht
    private static final int PARAMETER_SIZE = 0;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    public Deliver(IQuestService client) {
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

        List<Delivery> list = this.client.deliverTask();

        for(Delivery delivery: list){
            System.out.println("id: " + delivery.getId());
            System.out.println("quest-id: " + delivery.getQuest());
            System.out.println("user: " + delivery.getUser());
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
