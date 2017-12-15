package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 20.11.2017
 */
public class Answer extends Command {

    private IQuestService client;

    private static final int PARAMETER_SIZE = 1;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    public Answer(IQuestService client) {
        this.client = client;
    }

    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException  {

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

        app.layerLogicAndService.cmpService.entity.quest.Answer answer = this.client.answerToCurrentUri(
                this.getParameter().get(0)
        );

        System.out.println("message: " + answer.getMessage());

        if(answer.getToken_name() != null){
            System.out.println("token_name: " + answer.getToken_name());
        }


        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -answer                               answer to the current questing"; // TODO - richtigen Text hier
    }
}
