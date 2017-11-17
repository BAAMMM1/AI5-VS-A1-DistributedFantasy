package app.layerBusinessLogic.cmpRegister;

import app.layerDataAccess.restConsumer.registerConsume.IRegisterConsumer;

/**
 * @author Christian G. on 02.11.2017
 */
public class RegisterClientImp implements IRegisterClient {

    private IRegisterConsumer registerConsumer;
    private Account account;

    public RegisterClientImp(IRegisterConsumer registerConsumer) {
        this.registerConsumer = registerConsumer;
        this.account = new Account();
    }

    @Override
    public void registerUser(String name, String password) {
        //RegisterUserDTO dto = this.registerConsumer.registerUser(getName, password);

    }

    @Override
    public void getAuthenticationToken(String name, String password) {
        //Token hinterlegen im System
    }

    @Override
    public void checkLogin(String Token) {

    }
}
