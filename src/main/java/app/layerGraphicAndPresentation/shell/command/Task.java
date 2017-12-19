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
public class Task extends Command {

    private IQuestService client;

    private static final int PARAMETER_SIZE = 1;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));


    public Task(IQuestService client) {
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

        app.layerLogicAndService.cmpService.entity.quest.Task task = this.client.getTask(Integer.valueOf(this.getParameter().get(0)));

        System.out.print("\n");
        System.out.println("######################################################################################\n");
        System.out.println("\t\t\t\t   " + task.getName().toString() + "\n");
        System.out.println("######################################################################################");
        System.out.print("\n");
        System.out.println(task.getDescription().toString());
        System.out.print("\n");
        System.out.println("location: " + task.getLocation().toString().replace("/map/", ""));
        System.out.print("\n");
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
        return "  -task\t\t\t[task-id]\t\t\tdisplays a task based on a id";
    }
}
