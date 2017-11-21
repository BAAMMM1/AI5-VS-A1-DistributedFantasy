package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerGraphicAndPresentation.shell.state.State;
import app.layerLogicAndService.cmpQuest.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.QuestsDTO;

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

    public Quests(Interpreter interpreter, IQuestService client) {
        super(interpreter);
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

        QuestsDTO dto = this.client.getQuests();

        System.out.println(dto.toString());

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
