package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.*;
import app.layerLogicAndService.cmpBlackboard.BlackboardListener;
import app.layerLogicAndService.cmpAccount.IAccountService;
import app.layerLogicAndService.cmpAccount.AccountServiceImp;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.IAccountConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.AccountConsumer;

/**
 * @author Christian G. on 17.11.2017
 */
public class Main {

    public static void main(String[] args) {

        BlackboardListener listener = new BlackboardListener(24000); // - TODO - Interface draus machen?

        IAccountConsumer registerConsumer = new AccountConsumer();
        IAccountService registerClient = new AccountServiceImp(registerConsumer);

        Interpreter interpreter = new Interpreter();
        CommandHandler handler = new CommandHandler(interpreter);

        new Help(interpreter);
        new Login(interpreter, registerClient);
        new Register(interpreter, registerClient);
        new Clear(interpreter);
        new Exit(interpreter);
        new Whoami(interpreter, registerClient);


        /*

        listener.receive();

        Scanner reader = new Scanner(System.in);

        while (true) {

            System.out.print(Context.getInstance().getPromptState());

            String in = reader.nextLine();

            handler.handleCommand(in);

            System.out.println();


        }
        */


        listener.receive();

        handler.handleCommand("register MeinSuperTestUser test1234");
        System.out.println();
        //handler.handleCommand("register MeinSuperTestUserTest test1234");
        //System.out.println();

        handler.handleCommand("login MeinSufqfqwfqwfqwperTestUser test1234");
        System.out.println();
        handler.handleCommand("login MeinSuperTestUser test1234");
        System.out.println();
        handler.handleCommand("whoami");
        System.out.println();
        handler.handleCommand("help");
        System.out.println();
        handler.handleCommand("exit");


    }
}
