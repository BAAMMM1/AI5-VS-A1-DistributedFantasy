package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto;

/**
 * @author Christian G. on 04.11.2017
 */
public class WhoamiDTO {

    String message;
    // user
    String deliverables_done;
    String delivered;
    String ip;
    String location;
    String name;

    // TODO - user fehlt
    /*
    {
  "message": "You are authenticated",
  "user": {
    "_links": {
      "encryption_key": "/users/MeinSuperTestUser/encryption_key",
      "self": "/users/MeinSuperTestUser"
    },
    "deliverables_done": [],
    "delivered": [],
    "ip": null,
    "location": null,
    "name": "MeinSuperTestUser"
  }
     */

    // TODO - Ist hier null korrekt als Rückgabe oder müssen hier leere Arrays zurückkommen?
    /*
    WhoamiDTO{message='You are authenticated', deliverables_done='null', delivered='null', ip='null', location='null', name='null'}
    You are authenticated
    null
    null
    null
    null
    null
     */

    public WhoamiDTO(String message, String deliverables_done, String delivered, String ip, String location, String name) {
        this.message = message;
        this.deliverables_done = deliverables_done;
        this.delivered = delivered;
        this.ip = ip;
        this.location = location;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeliverables_done() {
        return deliverables_done;
    }

    public void setDeliverables_done(String deliverables_done) {
        this.deliverables_done = deliverables_done;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WhoamiDTO{" +
                "message='" + message + '\'' +
                ", deliverables_done='" + deliverables_done + '\'' +
                ", delivered='" + delivered + '\'' +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
