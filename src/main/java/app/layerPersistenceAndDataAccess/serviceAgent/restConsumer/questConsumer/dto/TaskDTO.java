package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import app.layerLogicAndService.cmpQuest.entity.Task;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class TaskDTO {

    private Task object;
    private String status;

    public TaskDTO(Task object, String status) {
        this.object = object;
        this.status = status;
    }

    public Task getObject() {
        return object;
    }

    public void setObject(Task object) {
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
        return "TaskDTO{" +
                "\nobject=" + object +
                ", \nstatus='" + status + '\'' +
                '}';
    }

}
