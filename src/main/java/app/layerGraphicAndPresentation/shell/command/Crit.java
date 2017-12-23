package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerLogicAndService.cmpService.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.QuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 20.11.2017
 */
public class Crit extends Command {

    private QuestConsumer questConsumer = new QuestConsumer();

    private static final int PARAMETER_SIZE = 0;

    public Crit() {

    }


    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        this.questConsumer.postToCritial();

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
