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
public class Quest extends Command {

    private IQuestService client;

    private static final int PARAMETER_SIZE = 1;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    public Quest(IQuestService client) {
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

        app.layerLogicAndService.cmpService.entity.quest.Quest quest = this.client.getQuest(Integer.valueOf(this.getParameter().get(0)));

        System.out.print("\n");
        System.out.println("######################################################################################\n");
        System.out.println("\t\t\t\t   " + quest.getName().toString() + "\n");
        System.out.println("######################################################################################");
        System.out.println(quest.getDescription().toString());
        System.out.print("\n");
        System.out.print("task to do: [");
        String taskIds = "";
        for(String string: quest.getTasks()){
            taskIds = taskIds + string.substring(string.length()-1) + ", ";
        }
        taskIds = taskIds.substring(0, taskIds.length()-2);
        taskIds = taskIds + "]";
        System.out.print(taskIds);
        System.out.print("\n");

        System.out.println("prerequisites: " + quest.getPrerequisites().toString());
        System.out.println("followups: " + quest.getFollowups());

        System.out.print("\n");
        System.out.println("reward: " + quest.getReward());

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -quest\t\t[quest-id]\t\t\tdisplays a quest based on a id";
    }
}
