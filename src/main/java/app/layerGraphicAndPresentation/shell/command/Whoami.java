package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerGraphicAndPresentation.shell.state.State;
import app.layerLogicAndService.cmpAccount.IAccountService;
import app.layerLogicAndService.Exception.NotAuthenticatedException;
import app.layerLogicAndService.cmpBlackboard.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.WhoamiDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonDto.ErrorDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 18.11.2017
 */
public class Whoami extends Command {

    private IAccountService client;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    private static final int PARAMETER_SIZE = 0;

    public Whoami(Interpreter interpreter, IAccountService client) {
        super(interpreter);
        this.client = client;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }


    public Whoami(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void checkState() throws UnAcceptedStateException {

        if(!acceptedStates.contains(Context.getInstance().getState())){
            throw new UnAcceptedStateException();
        }

    }


    @Override
    State instruction() {

        try {
            WhoamiDTO dto = this.client.checkLogin(Blackboard.getInstance().getToken());

            System.out.println(dto.getMessage());
            System.out.println(dto.getDeliverables_done());
            System.out.println(dto.getDelivered());
            System.out.println(dto.getIp());
            System.out.println(dto.getLocation());
            System.out.println(dto.getName());

            return null;
        } catch (ErrorCodeException e) {
            ErrorDTO dto = e.getErrorDTO();

            System.out.println("message: " + dto.getMessage());
            return State.NOT_LOGIN;
        } catch (NotAuthenticatedException e) {

            System.out.println("message: " + e.getMessage());
            return State.NOT_LOGIN;
        }


    }
}
