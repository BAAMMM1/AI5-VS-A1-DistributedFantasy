package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Quests extends Command {

    private IQuestService client;

    private static final int PARAMETER_SIZE = 0;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    public Quests(InputInterpreter inputInterpreter, IQuestService client) {
        super(inputInterpreter);
        this.client = client;
    }

    @Override
    public void checkState() throws UnAcceptedStateException {

        if (!acceptedStates.contains(Context.getInstance().getState())) {
            throw new UnAcceptedStateException();
        }

    }

    @Override
    State instruction() throws ErrorCodeException {

        List<app.layerLogicAndService.cmpQuest.entity.Quest> quests = this.client.getQuests();

        for(app.layerLogicAndService.cmpQuest.entity.Quest quest : quests){
            System.out.println("quest-id: " + quest.getId());
            System.out.println("name: " + quest.getName().toString());
            System.out.println("description: " + quest.getDescription().toString());
            System.out.println("tasks: " + quest.getTasks().toString());
            System.out.println("prerequisites: " + quest.getPrerequisites().toString());
            System.out.println("followups: " + quest.getFollowups());
            System.out.println("reward: " + quest.getReward());
            System.out.println("\n######################################################################\n");
        }

        return null;

    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -quests                                        displays all avialable quests";
    }


}
