package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 20.11.2017
 */
public class Task extends Command {

    private IQuestService client;

    private static final int PARAMETER_SIZE = 1;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public Task(InputInterpreter inputInterpreter, IQuestService client) {
        super(inputInterpreter);
        this.client = client;
    }

    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException  {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }

        Integer.parseInt(param.get(0));

    }

    @Override
    public void checkState() throws UnAcceptedStateException {

        if (!acceptedStates.contains(Context.getInstance().getState())) {
            throw new UnAcceptedStateException();
        }

    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        app.layerLogicAndService.cmpQuest.entity.Task task = this.client.getTask(Integer.valueOf(this.getParameter().get(0)));

        System.out.println("task-id: " + task.getId());
        System.out.println("quest-id: " + task.getQuest());
        System.out.println("name: " + task.getName().toString());
        System.out.println("name: " + task.getDescription().toString());
        System.out.println("location: " + task.getLocation().toString());
        System.out.println("required_players: " + task.getRequired_players());
        System.out.println("requirements: " + task.getRequirements());

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -task         [task-id]                        displays a task based on a id";
    }
}
