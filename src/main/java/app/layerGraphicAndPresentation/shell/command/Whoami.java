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
    public void checkState() throws UnAcceptedStateException {

        if (!acceptedStates.contains(Context.getInstance().getState())) {
            throw new UnAcceptedStateException();
        }

    }


    /**
     * Falls Whoami einen ErrorCodeException wirft, wird hier der Application-Context zurück gesetzt.
     *
     * @return
     */
    @Override
    State instruction() {

        try {
            WhoamiDTO dto = this.client.checkLogin(Blackboard.getInstance().getToken());

            System.out.println("message: " + dto.getMessage());
            System.out.println("encryption_key:" + dto.getUser().get_links().getEncryption_key());
            System.out.println("self: " + dto.getUser().get_links().getSelf());
            System.out.println("deliverables_done: " + dto.getUser().getDeliverables_done());
            System.out.println("delivered: " + dto.getUser().getDelivered());
            System.out.println("ip: " + dto.getUser().getIp());
            System.out.println("location: " + dto.getUser().getLocation());
            System.out.println("name: " + dto.getUser().getName());

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

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -whoami                                        indicates if you are athentificated";
    }

}
