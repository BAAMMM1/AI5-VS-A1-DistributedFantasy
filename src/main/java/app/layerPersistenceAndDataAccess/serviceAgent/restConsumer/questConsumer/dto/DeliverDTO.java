package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

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
    private List<Object> object;
    private String status;

    public DeliverDTO(String message, List<Object> object, String status) {
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

    public List<Object> getObject() {
        return object;
    }

    public void setObject(List<Object> object) {
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

    public class Object{

        private List<Integer> deliverables;
        private int id;
        private int quest;
        private String timestamp;
        private String user;

        public Object(List<Integer> deliverables, int id, int quest, String timestamp, String user) {
            this.deliverables = deliverables;
            this.id = id;
            this.quest = quest;
            this.timestamp = timestamp;
            this.user = user;
        }

        public List<Integer> getDeliverables() {
            return deliverables;
        }

        public void setDeliverables(List<Integer> deliverables) {
            this.deliverables = deliverables;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuest() {
            return quest;
        }

        public void setQuest(int quest) {
            this.quest = quest;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "Object{" +
                    "\ndeliverables=" + deliverables +
                    ", \nid=" + id +
                    ", \nquest=" + quest +
                    ", \ntimestamp='" + timestamp + '\'' +
                    ", \nuser='" + user + '\'' +
                    '}';
        }
    }


}
