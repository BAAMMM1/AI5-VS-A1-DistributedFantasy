package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import app.layerLogicAndService.cmpQuest.entity.Delivery;

import java.util.List;

/**
 * @author Chris on 20.11.2017
 */
public class DeliverDTO {

        /*

{
  "message": "Created Delivery",
  "object": [
    {
      "deliverables": [
        193
      ],
      "id": 434,
      "quest": 1,
      "timestamp": "2017-11-21T16:11:39.834529+00:00",
      "user": "x2"
    }
  ],
  "status": "success"
}

     */

    private String message;
    private List<Delivery> object;
    private String status;

    public DeliverDTO(String message, List<Delivery> object, String status) {
        this.message = message;
        this.object = object;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Delivery> getObject() {
        return object;
    }

    public void setObject(List<Delivery> object) {
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
        return "DeliverDTO{" +
                "\nmessage='" + message + '\'' +
                ", \nobject=" + object +
                ", \nstatus='" + status + '\'' +
                '}';
    }



}
