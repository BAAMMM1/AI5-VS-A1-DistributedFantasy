package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.*;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerLogicAndService.cmpBlackboard.service.IBlackboardService;
import app.layerLogicAndService.cmpQuest.service.IQuestService;
import app.layerLogicAndService.cmpTaverna.service.ITavernaService;

import java.util.Scanner;

/**
 * @author Chris on 24.11.2017
 */
public class Shell {

    private Scanner scanner;

    private InputInterpreter inputInterpreter;
    private InputHandler handler;

    private IBlackboardService blackboardService;
    private IQuestService questService;

    private ITavernaService tavernaService;

    /**
     * Standard Shell-Constructor ohne Programmabhängigkeiten
     */
    public Shell() {
        this.scanner = new Scanner(System.in);
        inputInterpreter = new InputInterpreter();
        handler = new InputHandler(inputInterpreter);

    }

    /**
     * Constructor mit Dependencies für dieses Programm
     * @param blackboardService
     * @param questService
     */
    public Shell(IBlackboardService blackboardService, IQuestService questService, ITavernaService tavernaService) {
        this();
        this.blackboardService = blackboardService;
        this.questService = questService;
        this.tavernaService = tavernaService;

    }

    public void start(){

        this.addCommands();

        while (true) {

            System.out.print(Context.getInstance().getPromptState());

            String in = scanner.nextLine();

            handler.handleCommand(in);


        }


        /*
        listener.run();

        handler.handleCommand("register MeinSuperTestUser test1234");
        System.out.println();
        //handler.handleCommand("register MeinSuperTestUserTest9 test1234");
        //System.out.println();

        //handler.handleCommand("login MeinSufqfqwfqwfqwperTestUser test1234");
        System.out.println();
        handler.handleCommand("login MeinSuperTestUser test1234");
        System.out.println();
        //handler.handleCommand("whoami");
        System.out.println();
        //handler.handleCommand("quests");
        System.out.println();
        //handler.handleCommand("quest 2");
        System.out.println();
        //handler.handleCommand("task 1");
        System.out.println();
        handler.handleCommand("deliverTask 1 1 1");
        System.out.println();
        //handler.handleCommand("deliverTask 2 1 1");
        System.out.println();
        //handler.handleCommand("deliverTask 3 1 1");
        System.out.println();
        //handler.handleCommand("map /map/Throneroom");
        System.out.println();
        //handler.handleCommand("map map/Throneroom");
        System.out.println();
        //handler.handleCommand("visit 172.19.0.32 5000");
        System.out.println();
        //handler.handleCommand("visit 172.19.0.4 5000");
        System.out.println();
        handler.handleCommand("help");
        System.out.println();
        handler.handleCommand("exit");
        */


    }

    private void addCommands(){

        new Help(inputInterpreter);
        new Clear(inputInterpreter);
        new Exit(inputInterpreter);
        new Register(inputInterpreter, blackboardService);
        new Login(inputInterpreter, blackboardService);
        new Whoami(inputInterpreter, blackboardService);
        new Quests(inputInterpreter, questService);
        new Quest(inputInterpreter, questService);
        new Task(inputInterpreter, questService);
        new Map(inputInterpreter, questService);
        new Visit(inputInterpreter, questService);
        new Answer(inputInterpreter, questService);
        new Deliver(inputInterpreter, questService);
        new Test(inputInterpreter, blackboardService);
        new Next(inputInterpreter, questService);
        new Step(inputInterpreter, questService);
        new DeliverSteps(inputInterpreter, questService);
        new Entertaverna(inputInterpreter, tavernaService);
        new GroupCreate(inputInterpreter, tavernaService);
        new GroupDelete(inputInterpreter, tavernaService);
        new Groups(inputInterpreter, tavernaService);
        new Group(inputInterpreter, tavernaService);
        new GroupEnter(inputInterpreter, tavernaService);

    }
}
