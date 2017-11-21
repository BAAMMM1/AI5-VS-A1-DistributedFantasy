package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.Interpreter;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerGraphicAndPresentation.shell.state.State;
import app.layerLogicAndService.cmpAccount.IAccountService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto.RegisterUserDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException.ErrorCodeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class Register extends Command {

    private IAccountService client;

    private static final int PARAMETER_SIZE = 2;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.NOT_LOGIN));


    public Register(Interpreter interpreter, IAccountService client) {
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

        RegisterUserDTO dto = this.client.registerUser(this.getParameter().get(0), this.getParameter().get(1));
        System.out.println("message: " + dto.getMessage());
        System.out.println("algorithm:" + dto.getEncryption().getAlgorithm());
        System.out.println("encryption: " + dto.getEncryption().getEncryption());
        System.out.println("hmack: " + dto.getEncryption().getHMACK());
        System.out.println("key: " + dto.getEncryption().getKey());
        System.out.println("key_enconding: " + dto.getEncryption().getKey_encoding());
        System.out.println("key_length: " + dto.getEncryption().getKeylength());
        System.out.println("mode: " + dto.getEncryption().getMode());
        System.out.println("padding: " + dto.getEncryption().getPadding());
            /*
            System.out.println("encryption_key: " + dto.getObject().get_links().getEncryption_key());
            System.out.println("self: " + dto.getObject().get_links().getSelf());
            System.out.println("deliverables_done: "+ dto.getObject().getDeliverables_done());
            System.out.println("delivered: " + dto.getObject().getDelivered());
            System.out.println("ip: " + dto.getObject().getIp());
            System.out.println("location: " + dto.getObject().getLocation());
            System.out.println("name: " + dto.getObject().getName());
            */

        return null;

    }


    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -register     [user] [password]                registered a new user";
    }


}
