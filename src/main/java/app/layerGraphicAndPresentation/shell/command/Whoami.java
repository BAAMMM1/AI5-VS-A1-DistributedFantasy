package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpBlackboard.service.IBlackboardService;
import app.layerLogicAndService.cmpBlackboard.Exception.NotAuthenticatedException;
import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto.WhoamiDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.dto.ErrorDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.ErrorCodeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 18.11.2017
 */
public class Whoami extends Command {

    private IBlackboardService client;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    private static final int PARAMETER_SIZE = 0;

    public Whoami(InputInterpreter inputInterpreter, IBlackboardService client) {
        super(inputInterpreter);
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
            WhoamiDTO dto = this.client.checkLogin(Blackboard.getInstance().getUserToken());

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
