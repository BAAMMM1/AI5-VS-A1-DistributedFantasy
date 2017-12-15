package app.layerLogicAndService.cmpService.service;

import app.layerLogicAndService.cmpService.entity.taverna.Adventurer;
import app.layerLogicAndService.cmpService.entity.taverna.Group;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;

import java.net.UnknownHostException;
import java.util.List;


/**
 * @author Chris on 01.12.2017
 */
public interface ITavernaService {

    // TODO - updateAdventurer
    Adventurer addHeroServiceToTaverna() throws UnexpectedResponseCodeException, UnknownHostException;
    List<Adventurer> getAdventurers() throws UnexpectedResponseCodeException;
    Adventurer getAdventure(String name) throws UnexpectedResponseCodeException;

    List<Group> getGroups() throws UnexpectedResponseCodeException;
    Group getGroup(int id) throws UnexpectedResponseCodeException;
    Group createGroup() throws UnexpectedResponseCodeException;
    String deleteGroup(int id) throws UnexpectedResponseCodeException;
    String enterGroup(int id) throws UnexpectedResponseCodeException;
    List<Adventurer> getGroupMembers(int id) throws UnexpectedResponseCodeException;



}
