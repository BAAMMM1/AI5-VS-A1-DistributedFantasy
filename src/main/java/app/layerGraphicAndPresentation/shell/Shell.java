package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.*;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerLogicAndService.cmpService.service.blackboard.IBlackboardService;
import app.layerLogicAndService.cmpService.service.hero.IHeroService;
import app.layerLogicAndService.cmpService.service.heroToHero.IHeroToHeroService;
import app.layerLogicAndService.cmpService.service.quest.IQuestService;
import app.layerLogicAndService.cmpService.service.taverna.ITavernaService;

import java.util.Scanner;

/**
 * @author Chris on 24.11.2017
 */
public class Shell {

    private Scanner scanner;

    private InputInterpreter inputInterpreter;
    private InputHandler handler;

    /**
     * Standard Shell-Constructor ohne Programmabhängigkeiten
     */
    public Shell() {
        this.scanner = new Scanner(System.in);
        inputInterpreter = new InputInterpreter();
        handler = new InputHandler(inputInterpreter);

    }


    public void start(){

        while (true) {

            System.out.print(Context.getInstance().getPromptState());

            String in = scanner.nextLine();

            handler.handleCommand(in);

        }

    }

}
