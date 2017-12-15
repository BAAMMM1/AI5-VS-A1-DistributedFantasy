package app.layerGraphicAndPresentation.shell.command;

import app.layerGraphicAndPresentation.shell.context.State;
import app.layerLogicAndService.cmpService.entity.quest.Visit;
import app.layerLogicAndService.cmpService.service.IQuestService;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.io.IOException;

/**
 * @author Chris on 05.12.2017
 */
public class Assignment extends Command {

    IQuestService questService;

    public Assignment(IQuestService questService) {
        this.questService = questService;
    }

    @Override
    State instruction() throws UnexpectedResponseCodeException, IOException, InterruptedException {

        Visit visit = this.questService.doAssignment();

        System.out.println("message:" + visit.getMessage());

        if (visit.getNext() != null) {
            System.out.println("next: " + visit.getNext());
        }

        if (visit.getSteps_todo() != null) {
            System.out.println("steps_todo: " + visit.getSteps_todo());
        }

        System.out.println("required_players: " + visit.getRequired_players());
        System.out.println("required_tokens: " + visit.getRequired_tokens());

        if (visit.getToken_name() != null) {
            System.out.println("token_name: " + visit.getToken_name());
        }

        return null;
    }

    @Override
    int parameterSize() {
        return 0;
    }

    @Override
    String description() {
        return null;
    }
}
