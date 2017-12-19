package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.exception.ParameterIncorrectException;
import app.layerGraphicAndPresentation.shell.exception.UnAcceptedStateException;
import app.layerGraphicAndPresentation.shell.context.Context;
import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 20.11.2017
 */
public class Visit extends Command {

    private IQuestService questService;

    private static final int PARAMETER_SIZE = 2;

    private List<State> acceptedStates = new ArrayList<State>(Arrays.asList(State.LOGIN));

    public Visit(IQuestService questService) {
        this.questService = questService;
    }

    public void checkParam(List<String> param) throws ParameterIncorrectException, NumberFormatException  {

        if (param.size() != this.getNumberOfParameter()) {
            throw new ParameterIncorrectException("size of parameter incorrect");
        }


        // IP wird im HttpAccess geprüft, Port hier, Ressource wirft 404 falls nicht vorhanden;

        Integer.valueOf(param.get(1));

    }

    @Override
    public void checkState() throws UnAcceptedStateException {

        if (!acceptedStates.contains(Context.getInstance().getState())) {
            throw new UnAcceptedStateException();
        }

    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        app.layerLogicAndService.cmpService.entity.quest.Visit visit = this.questService.visitLocationForTask(this.getParameter().get(0), Integer.valueOf(this.getParameter().get(1)));

        System.out.print("\n");
        System.out.println("######################################################################################");
        System.out.println("yor are visit the " + this.getParameter().get(0) + " for task: " + Integer.valueOf(this.getParameter().get(1)));
        System.out.println("######################################################################################\n");
        System.out.println(visit.getMessage());
        System.out.print("\n");
        System.out.println("required players: " + visit.getRequired_players());
        System.out.println("required tokens: " + visit.getRequired_tokens());
        System.out.print("\n");
        if (visit.getNext() != null) {
            System.out.println("next: " + visit.getNext());
        }

        if (visit.getSteps_todo() != null) {
            System.out.println("steps_todo: " + visit.getSteps_todo());
        }



        if (visit.getToken_name() != null) {
            System.out.println("token_name: " + visit.getToken_name());
        }

        return null;
    }

    @Override
    int parameterSize() {
        return PARAMETER_SIZE;
    }

    @Override
    String description() {
        return "  -visit\t\t[location] [task-id]\t\tvisit a host based on a ip and port";
    }
}
