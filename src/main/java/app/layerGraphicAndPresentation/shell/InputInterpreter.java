package app.layerGraphicAndPresentation.shell;

import app.layerGraphicAndPresentation.shell.command.*;
import app.layerGraphicAndPresentation.shell.exception.*;
import app.layerLogicAndService.cmpService.service.BlackboardService;
import app.layerLogicAndService.cmpService.service.IBlackboardService;
import app.layerLogicAndService.cmpService.service.FromHeroService;
import app.layerLogicAndService.cmpService.service.IFromHeroService;
import app.layerLogicAndService.cmpService.service.ToHeroService;
import app.layerLogicAndService.cmpService.service.IToHeroService;
import app.layerLogicAndService.cmpService.service.IQuestService;
import app.layerLogicAndService.cmpService.service.QuestService;
import app.layerLogicAndService.cmpService.service.ITavernaService;
import app.layerLogicAndService.cmpService.service.TavernaService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian G. on 17.11.2017
 */
public class InputInterpreter {

    private IBlackboardService blackboardService;
    private IQuestService questService;
    private ITavernaService tavernaService;
    private IToHeroService toHeroService;
    private IFromHeroService heroService;

    List<Command> registerCommands;

    public InputInterpreter() {
        blackboardService = new BlackboardService();
        questService = new QuestService();
        tavernaService = new TavernaService();
        this.toHeroService = new ToHeroService();
        this.heroService = new FromHeroService();

        this.registerCommands = new ArrayList<Command>();

        this.addCommands();
    }


    public Command interpret(String in) throws CommandNotExistsException, ParameterIncorrectException {

        String befehl;

        if (in.indexOf(" ") != -1) {
            befehl = in.substring(0, in.indexOf(" "));

        } else {
            befehl = in;
        }


        Command result = this.registerCommands.stream().filter(c -> c.getCommandName().equals(befehl)).findFirst().orElse(null);

        if (result != null) {
            return result;

        } else {
            throw new CommandNotExistsException(befehl);
        }
    }

    public List<String> interpretParam(String in){

        List<String> param = new ArrayList<String>();

        if (in.indexOf(" ") != -1) {

            do {

                in = in.substring(in.indexOf(" ") + 1);

                if (in.indexOf(" ") != -1) {
                    param.add(in.substring(0, in.indexOf(" ")));
                    in = in.substring(in.indexOf(" "));
                } else {
                    param.add(in);
                }

            }
            while (in.indexOf(" ") != -1);

            return param;


        } else {
            return param;
        }

    }

    public List<Command> getRegisterCommands() {
        return registerCommands;
    }

    public void registerCommand(Command command){
        this.registerCommands.add(command);
    }


    private void addCommands(){

        registerCommands.add(new Help(this));
        registerCommands.add(new Clear());
        registerCommands.add(new Exit());
        registerCommands.add(new Register(blackboardService));
        registerCommands.add(new Login(blackboardService));
        registerCommands.add(new Quests(questService));
        registerCommands.add(new Quest(questService));
        registerCommands.add(new Task(questService));
        registerCommands.add(new Map(questService));
        registerCommands.add(new Visit(questService));
        registerCommands.add(new Post(questService));
        registerCommands.add(new Deliver(questService));
        registerCommands.add(new Test(blackboardService));
        registerCommands.add(new Test2(blackboardService));
        registerCommands.add(new Next(questService));
        registerCommands.add(new Step(questService));
        registerCommands.add(new DeliverSteps(questService));
        registerCommands.add(new GroupCreate(tavernaService));
        registerCommands.add(new GroupDelete(tavernaService));
        registerCommands.add(new Groups(tavernaService));
        registerCommands.add(new Group(tavernaService));
        registerCommands.add(new GroupEnter(tavernaService));
        registerCommands.add(new GroupMembers(tavernaService));
        registerCommands.add(new Adventurers(tavernaService));
        registerCommands.add(new Adventurer(tavernaService));
        registerCommands.add(new Invite(toHeroService));
        registerCommands.add(new Messages(heroService));
        registerCommands.add(new GroupOwn());
        registerCommands.add(new GroupLeave());
        registerCommands.add(new Message(toHeroService));
        registerCommands.add(new Assignment(questService));
        registerCommands.add(new AssignmentDeliver(toHeroService));
        registerCommands.add(new AssignmentSend(toHeroService));
        registerCommands.add(new ElectionStart(toHeroService));
        registerCommands.add(new Dead());
        registerCommands.add(new Live());
        registerCommands.add(new AssignmentSendEnd(toHeroService));
        registerCommands.add(new Services(toHeroService));
        registerCommands.add(new AdventurersMutex(tavernaService));
        registerCommands.add(new Mutexwant(toHeroService));
        registerCommands.add(new Wanting());

    }



}
