package app.layerLogicAndService.cmpHero.service;


import app.layerGraphicAndPresentation.controller.config.PathHeroservice;
import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import app.layerLogicAndService.cmpHero.entity.Assignment;
import app.layerLogicAndService.cmpHero.entity.Hiring;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.entity.Service;

import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Chris on 03.12.2017
 */
@org.springframework.stereotype.Service
public class HeroService implements IHeroService {

    @Override
    public app.layerLogicAndService.cmpHero.entity.Service getService() {

        String self = "";

        // TODO - Self bestimmung auslagern in die Config und die URL der Services als Konstante rausziehen
        try {
            self = "http://" + java.net.InetAddress.getLocalHost().getHostAddress().toString() + ":8080/";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Service service = new Service(Blackboard.getInstance().getUser().get_links().getSelf(),
                false,
                null,
                self + PathHeroservice.HIRINGS,
                self + PathHeroservice.ASSIGNMENTS,
                self + PathHeroservice.MESSAGES);

        return service;
    }

    @Override
    public void addHiring(Hiring hiring) {

        // TODO - Do here something with the Hiring

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
        return Blackboard.getInstance().getUser().getMessages();
    }
}
