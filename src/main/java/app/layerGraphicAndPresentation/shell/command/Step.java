package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.VisitDTO;

import java.io.IOException;

/**
 * @author Chris on 25.11.2017
 */
public class Step extends Command{

    private IQuestService client;

    private static final int PARAMETER_SIZE = 1;

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public Step(InputInterpreter inputInterpreter, IQuestService client) {
        super(inputInterpreter);
        this.client = client;
    }

    @Override
    State instruction() throws ErrorCodeException, IOException, InterruptedException, ErrorDeliverCodeException {

        VisitDTO dto = this.client.step(Integer.valueOf(this.getParameter().get(0)));

        System.out.println(dto.toString());

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return null;
    }
}
