package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.VisitDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Chris on 20.11.2017
 */
public class Visit extends Command {

    private IQuestService client;

    private static final int PARAMETER_SIZE = 3;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public Visit(InputInterpreter inputInterpreter, IQuestService client) {
        super(inputInterpreter);
        this.client = client;
    }

    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException  {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }


        // IP wird im HTTPCaller geprüft, Port hier, Ressource wirft 404 falls nicht vorhanden;

        Integer.valueOf(param.get(1));

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

        System.out.println(Context.getInstance().getState());

        VisitDTO dto = this.client.visitHost(this.getParameter().get(0), Integer.valueOf(this.getParameter().get(1)), this.getParameter().get(2));

        System.out.println(dto.toString());

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -visit        [ip] [port] [/ressource]         visit a host based on a ip and port";
    }
}
