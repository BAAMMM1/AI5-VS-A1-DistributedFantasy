package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.QuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 20.11.2017
 */
public class Crit extends Command {

    private IQuestService questConsumer;

    private static final int PARAMETER_SIZE = 0;

    public Crit(IQuestService questConsumer) {
        this.questConsumer = questConsumer;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        this.questConsumer.goCrit();

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -crit";
    }
}
