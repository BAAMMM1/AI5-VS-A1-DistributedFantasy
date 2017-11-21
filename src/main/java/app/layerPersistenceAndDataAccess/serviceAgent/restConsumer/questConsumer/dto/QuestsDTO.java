package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import app.layerLogicAndService.cmpQuest.entity.Quest;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class QuestsDTO {

    List<Quest> objects;
    String status;

    public QuestsDTO(List<Quest> objects, String status) {
        this.objects = objects;
        this.status = status;
    }

    public List<Quest> getObjects() {
        return objects;
    }

    public void setObjects(List<Quest> objects) {
        this.objects = objects;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QuestsDTO{" +
                "\nobjects=" + objects +
                ", \nstatus='" + status + '\'' +
                '}';
    }

}
