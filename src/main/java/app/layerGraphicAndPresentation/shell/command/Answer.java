package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerGraphicAndPresentation.shell.state.State;
import app.layerLogicAndService.cmpQuest.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.AnswerDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.MapDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Chris on 20.11.2017
 */
public class Answer extends Command {

    private IQuestService client;

    private static final int PARAMETER_SIZE = 4;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param interpreter
     */
    public Answer(Interpreter interpreter, IQuestService client) {
        super(interpreter);
        this.client = client;
    }

    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException  {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }


        if(!Pattern.matches("/.*", param.get(2))){
            throw new ParameterIncorrectException("ressource must be started with '/'");
        }


    }

    @Override
    public void checkState() throws UnAcceptedStateException {

        if (!acceptedStates.contains(Context.getInstance().getState())) {
            throw new UnAcceptedStateException();
        }

    }

    @Override
    State instruction() throws ErrorCodeException, IOException, InterruptedException {

        AnswerDTO dto = this.client.post(
                this.getParameter().get(0),
                Integer.valueOf(this.getParameter().get(1)),
                this.getParameter().get(2),
                this.getParameter().get(3)
        );

        System.out.println(dto.toString());

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -answer          [ip] [port] [ressource] [body]         answer to a quest"; // TODO - richtigen Text hier
    }
}
