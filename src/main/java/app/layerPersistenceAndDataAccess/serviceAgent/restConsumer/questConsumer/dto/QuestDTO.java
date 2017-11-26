package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import app.layerLogicAndService.cmpQuest.entity.Quest;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class QuestDTO {

    // TODO - requires_tokens fehlt noch
    /*
    {
  "object": {
    "_links": {
      "deliveries": "/blackboard/quests/3/deliveries",
      "self": "/blackboard/quests/3",
      "tasks": "/blackboard/quests/3/tasks"
    },
    "deliveries": [],
    "description": "\nWhile you where away the Throneroom got attacked.\nWe were able to fight off the scum, but there are still a lot of wounded.\nPlease help to carry the wounded to the infirmary.",
    "followups": [],
    "id": 3,
    "name": "Help the Wounded",
    "prerequisites": [],
    "requirements": "group",
    "requires_tokens": {
      "/blackboard/tasks/4": "Token:Carry a wounded person" // TODO - warum?
    },
    "reward": 1,
    "tasks": [
      "/blackboard/tasks/4"
    ]
  },
  "status": "success"
}

     */

    private app.layerLogicAndService.cmpQuest.entity.Quest object;
    private String status;

    public QuestDTO(app.layerLogicAndService.cmpQuest.entity.Quest object, String status) {
        this.object = object;
        this.status = status;
    }

    public app.layerLogicAndService.cmpQuest.entity.Quest getObject() {
        return object;
    }

    public void setObject(app.layerLogicAndService.cmpQuest.entity.Quest object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QuestDTO{" +
                "\nobject=" + object +
                ", \nstatus='" + status + '\'' +
                '}';
    }


}
