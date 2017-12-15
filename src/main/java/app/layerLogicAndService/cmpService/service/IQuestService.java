package app.layerLogicAndService.cmpService.service;

import app.layerLogicAndService.cmpService.entity.quest.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception.UnexpectedResponseCodeException;


import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public interface IQuestService {

    List<Quest> getQuests() throws UnexpectedResponseCodeException;
    Quest getQuest(int index) throws UnexpectedResponseCodeException;
    Task getTask(int index) throws UnexpectedResponseCodeException;
    Map getMap(String location) throws UnexpectedResponseCodeException;

    // TODO - Im moment für currentQuesting, später für übergebenen Questing
    Visit visitLocationForTask(String location, int taskId) throws UnexpectedResponseCodeException;
    Answer answerToCurrentUri(String body) throws UnexpectedResponseCodeException;
    Visit next() throws UnexpectedResponseCodeException;
    Visit step(int step) throws UnexpectedResponseCodeException;

    List<Delivery> deliverTask() throws UnexpectedResponseCodeException;
    Visit deliverTaskPart() throws UnexpectedResponseCodeException;

    Visit doAssignment() throws UnexpectedResponseCodeException;

}
