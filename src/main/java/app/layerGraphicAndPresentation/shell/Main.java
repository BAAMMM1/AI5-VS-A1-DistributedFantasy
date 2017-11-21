package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.*;
import app.layerGraphicAndPresentation.shell.state.Context;
import app.layerLogicAndService.cmpAccount.AccountServiceImp;
import app.layerLogicAndService.cmpAccount.IAccountService;
import app.layerLogicAndService.cmpBlackboard.BlackboardListener;
import app.layerLogicAndService.cmpQuest.IQuestService;
import app.layerLogicAndService.cmpQuest.QuestServiceImpl;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.AccountConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.IAccountConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.IQuestConsumer;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.QuestConsumerImpl;

import java.util.Scanner;

/**
 * @author Christian G. on 17.11.2017
 */
public class Main {

    public static void main(String[] args) {

        BlackboardListener listener = new BlackboardListener(24000); // - TODO - Interface draus machen?

        IAccountConsumer registerConsumer = new AccountConsumer();
        IAccountService registerClient = new AccountServiceImp(registerConsumer);

        IQuestConsumer questConsumer = new QuestConsumerImpl();
        IQuestService questService = new QuestServiceImpl(questConsumer);

        Interpreter interpreter = new Interpreter();
        CommandHandler handler = new CommandHandler(interpreter);

        new Help(interpreter);
        new Clear(interpreter);
        new Exit(interpreter);
        new Register(interpreter, registerClient);
        new Login(interpreter, registerClient);
        new Whoami(interpreter, registerClient);
        new Quests(interpreter, questService);
        new Quest(interpreter, questService);
        new Task(interpreter, questService);
        new Map(interpreter, questService);
        new Visit(interpreter, questService);
        new Answer(interpreter, questService);
        new Deliver(interpreter, questService);




        /*

        listener.receive();

        Scanner reader = new Scanner(System.in);

        while (true) {

            System.out.print(Context.getInstance().getPromptState());

            String in = reader.nextLine();

            handler.handleCommand(in);


        }

        */






        listener.receive();

        //handler.handleCommand("register MeinSuperTestUser test1234");
        System.out.println();
        //handler.handleCommand("register MeinSuperTestUserTest9 test1234");
        //System.out.println();

        //handler.handleCommand("login MeinSufqfqwfqwfqwperTestUser test1234");
        System.out.println();
        //handler.handleCommand("login MeinSuperTestUser test1234");
        System.out.println();
        //handler.handleCommand("whoami");
        System.out.println();
        //handler.handleCommand("quests");
        System.out.println();
        //handler.handleCommand("quest 2");
        System.out.println();
        //handler.handleCommand("task 1");
        System.out.println();
        //handler.handleCommand("map /map/Throneroom");
        System.out.println();
        //handler.handleCommand("map map/Throneroom");
        System.out.println();
        //handler.handleCommand("visit 172.19.0.32 5000");
        System.out.println();
        //handler.handleCommand("visit 172.19.0.4 5000"); // TODO - Fehler abfangen, wenn host nicht bekannt
        System.out.println();
        handler.handleCommand("help");
        System.out.println();
        handler.handleCommand("exit");





    }
}
