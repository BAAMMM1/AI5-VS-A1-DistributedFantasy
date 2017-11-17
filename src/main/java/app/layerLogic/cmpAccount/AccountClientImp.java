package app.layerLogic.cmpAccount;

import app.layerPersistence.restConsumer.account.dto.UserTokenDTO;
import app.layerPersistence.restConsumer.account.IAccountConsumer;
import app.layerPersistence.restConsumer.Exception.NotOKValueException;

/**
 * @author Christian G. on 02.11.2017
 */
public class AccountClientImp implements IAccountClient {

    private IAccountConsumer registerConsumer;

    public AccountClientImp(IAccountConsumer registerConsumer) {
        this.registerConsumer = registerConsumer;
    }

    @Override
    public void registerUser(String name, String password) {
        //RegisterUserDTO dto = this.registerConsumer.registerUser(getCommandName, password);

    }

    @Override
    public void getAuthenticationToken(String name, String password) throws NotOKValueException {



        UserTokenDTO dto = registerConsumer.getAuthenticationToken(name, password);

        // Username, Password und Token im System hinterlegen
        Account.getInstance().setName(name);
        Account.getInstance().setPassword(password);
        Account.getInstance().setToken(dto.getToken());

        // Prompt-Ausgabe
        System.out.println("username: " + Account.getInstance().getName());
        System.out.println("password: " + Account.getInstance().getPassword());
        System.out.println("token: " + Account.getInstance().getToken());

    }

    @Override
    public void checkLogin(String Token) {

    }
}
