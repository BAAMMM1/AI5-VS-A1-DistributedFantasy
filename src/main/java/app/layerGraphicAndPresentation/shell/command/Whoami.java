package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.InputInterpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpBlackboard.entity.User;
import app.layerLogicAndService.cmpBlackboard.service.IBlackboardService;
import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;

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
            User user = this.client.checkLogin(Blackboard.getInstance().getUser().getUserToken());

            System.out.println("name: " + user.getName());
            System.out.println("location: " + user.getLocation());
            System.out.println("ip: " + user.getIp());
            System.out.println("deliverables_done: " + user.getDeliverables_done());
            System.out.println("delivered: " + user.getDelivered());
            System.out.println("_links: " + user.get_links().toString());

            return null;

        } catch (ErrorCodeException e) {
            ErrorCodeDTO dto = e.getErrorCodeDTO();

            System.out.println("message: " + dto.getMessage());
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
