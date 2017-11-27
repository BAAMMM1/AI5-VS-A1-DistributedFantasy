package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import app.layerLogicAndService.cmpQuest.entity.Task;

/**
 * @author Christian G. on 02.11.2017
 */
public class ErrorDelivorDTO {

    private String error;
    private String message;
    private Task object;

    public ErrorDelivorDTO(String error, String message, Task object) {
        this.error = error;
        this.message = message;
        this.object = object;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Task object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ErrorDelivorDTO{" +
                "\nerror='" + error + '\'' +
                ", \nmessage='" + message + '\'' +
                ", \nobject=" + object +
                '}';
    }




}
