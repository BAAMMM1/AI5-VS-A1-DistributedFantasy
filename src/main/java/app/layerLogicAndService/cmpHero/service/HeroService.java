package app.layerLogicAndService.cmpHero.service;


import app.Application;
import app.layerGraphicAndPresentation.controller.config.PathHeroservice;
import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpHero.entity.Assignment;
import app.layerLogicAndService.cmpHero.entity.Hiring;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerLogicAndService.cmpHero.service.exception.AlreadyInGroupException;

import java.util.List;

/**
 * @author Chris on 03.12.2017
 */
@org.springframework.stereotype.Service
public class HeroService implements IHeroService {

    @Override
    public app.layerLogicAndService.cmpHero.entity.Service getService() {

        // TODO IP und Port korrekt rausziehen
        Service service = new Service(
                Blackboard.getInstance().getUser().get_links().getSelf(),
                false,
                Blackboard.getInstance().getUser().getGroup(),
                "http://" + Application.IP + ":8080" + PathHeroservice.HIRINGS,
                "http://" + Application.IP + ":8080" + PathHeroservice.ASSIGNMENTS,
                "http://" + Application.IP + ":8080" + PathHeroservice.MESSAGES);

        return service;
    }

    @Override
    public void addHiring(Hiring hiring) throws AlreadyInGroupException {

        if(Blackboard.getInstance().getUser().getGroup() == null){
            throw new AlreadyInGroupException("ehhmm ohh ehmm Nein! oder vlt ehm Nein! Ne doch nicht Nein! Ne Nein!");
        }

        // TODO - Testen ob es die Group auch gibt
        Blackboard.getInstance().getUser().setGroup(hiring.getGroup());
        // TODO - in die Groupe eintretren in der taverna

    }

    @Override
    public void addAssignment(Assignment assignment) {

        // TODO - Do here something with the assignment

    }

    @Override
    public void addMessage(Message message) {

        Blackboard.getInstance().getUser().addMessage(message);

    }

    @Override
    public List<Message> getMessages() {

        List<Message> messages = Blackboard.getInstance().getUser().getMessages();
        Blackboard.getInstance().getUser().removeMessage();

        return messages;
    }
}
