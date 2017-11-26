package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import app.layerLogicAndService.cmpQuest.entity.Map;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class MapDTO {

    private Map object;
    private String status;

    public MapDTO(Map object, String status) {
        this.object = object;
        this.status = status;
    }

    public Map getObject() {
        return object;
    }

    public void setObject(Map object) {
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
        return "MapDTO{" +
                "\nobject=" + object +
                ", \nstatus='" + status + '\'' +
                '}';
    }

}
