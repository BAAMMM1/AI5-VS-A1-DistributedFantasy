package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.Exit;
import app.layerGraphicAndPresentation.shell.command.Help;
import app.layerGraphicAndPresentation.shell.command.Login;
import app.layerGraphicAndPresentation.shell.command.Register;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerGraphicAndPresentation.shell.state.State;
import app.layerLogic.cmpBlackboard.BlackboardListener;
import app.layerLogic.cmpAccount.IAccountClient;
import app.layerLogic.cmpAccount.AccountClientImp;
import app.layerPersistence.restConsumer.account.IAccountConsumer;
import app.layerPersistence.restConsumer.account.AccountConsumer;

import java.util.Scanner;

/**
 * @author Christian G. on 17.11.2017
 */
public class Main {

    public static void main(String[] args){

        BlackboardListener listener = new BlackboardListener(24000); // - TODO - Interface draus machen?

        IAccountConsumer registerConsumer = new AccountConsumer();
        IAccountClient registerClient = new AccountClientImp(registerConsumer);

        Context context = new Context(State.NOT_LOGIN);

        CommandHandler handler = new CommandHandler(
                context,
                new Help(),
                new Login(registerClient),
                new Register(),
                new Exit());


        listener.receive();

        Scanner reader = new Scanner(System.in);

        // Initializte

        /*

        while (true){

            System.out.print(context.getPromptState());

            String in = reader.nextLine();

            handler.handleCommand(in);



        }

        */
        handler.handleCommand("login MeinSufqfqwfqwfqwperTestUser test1234");

        //handler.handleCommand("login MeinSuperTefwqwffqwfwqstUser test1234");

        handler.handleCommand("help");
        handler.handleCommand("exit");

    }
}
