package app.layerLogicAndService.cmpQuest.service;

import app.layerLogicAndService.cmpQuest.entity.*;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error.ErrorCodeException;


import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public interface IQuestService {

    List<Quest> getQuests() throws ErrorCodeException;
    Quest getQuest(int index) throws ErrorCodeException;
    Task getTask(int index) throws ErrorCodeException;
    Map getMap(String location) throws ErrorCodeException;

    // TODO - Im moment für currentQuesting, später für übergebenen Questing
    Visit visitLocationForTask(String location, int taskId) throws ErrorCodeException;
    Answer answerToCurrentUri(String body) throws ErrorCodeException;
    Visit next() throws ErrorCodeException;
    Visit step(int step) throws ErrorCodeException;

    List<Delivery> deliverTask() throws ErrorCodeException;
    Visit deliverTaskPart() throws ErrorCodeException;

}
