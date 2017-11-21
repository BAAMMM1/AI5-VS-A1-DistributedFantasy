package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpBlackboard.service.IListenerService;
import app.layerLogicAndService.cmpBlackboard.service.ListenerServiceImpl;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorDeliverCodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 21.11.2017
 */
public class Listen extends Command {

    // TODO - Dependency Injection

    private static final int PARAMETER_SIZE = 1;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.UNCONNECTED));

    /**
     * Interepreter muss übergeben werden, damit ein Command weiß, bei wem es sich registrieren soll
     *
     * @param inputInterpreter
     */
    public Listen(InputInterpreter inputInterpreter) {
        super(inputInterpreter);
    }

    @Override
    State instruction() throws ErrorCodeException, IOException, InterruptedException, ErrorDeliverCodeException {
        IListenerService listener = new ListenerServiceImpl(Integer.valueOf(this.getParameter().get(0)));

        listener.receive();

        return State.NOT_LOGIN;

    }

    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException  {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }

        Integer.parseInt(param.get(0));

    }



    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {



        return "  -listen       [port]                           listen to a port for the Blackboard";
    }
}
