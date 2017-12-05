package app.layerLogicAndService.cmpHero.service;

import app.layerLogicAndService.cmpHero.entity.Assignment;
import app.layerLogicAndService.cmpHero.entity.Hiring;
import app.layerLogicAndService.cmpHero.entity.Message;
import app.layerLogicAndService.cmpHero.entity.Service;
import app.layerLogicAndService.cmpHero.service.exception.AlreadyInGroupException;

import java.util.List;

/**
 * @author Chris on 03.12.2017
 */
public interface IHeroService {

    Service getService();
    void addHiring(Hiring hiring) throws AlreadyInGroupException;
    void addAssignment(Assignment assignment);
    void addMessage(Message message);
    List<Message> getMessages();

}
