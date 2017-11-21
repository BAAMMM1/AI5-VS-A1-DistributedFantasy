package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class QuestsDTO {

    List<Object> objects;
    String status;

    public QuestsDTO(List<Object> objects, String status) {
        this.objects = objects;
        this.status = status;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
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

    public class Object{

        private Link _links;
        private List<Integer> deliveries;
        private String description;
        private int id;
        private String name;
        private int reward;
        private List<String> tasks;

        public Object(Link _links, List<Integer> deliveries, String description, int id, String name, int reward, List<String> tasks) {
            this._links = _links;
            this.deliveries = deliveries;
            this.description = description;
            this.id = id;
            this.name = name;
            this.reward = reward;
            this.tasks = tasks;
        }

        public Link get_links() {
            return _links;
        }

        public void set_links(Link _links) {
            this._links = _links;
        }

        public List<Integer> getDeliveries() {
            return deliveries;
        }

        public void setDeliveries(List<Integer> deliveries) {
            this.deliveries = deliveries;
        }

        public String getDescription()
 {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getReward() {
            return reward;
        }

        public void setReward(int reward) {
            this.reward = reward;
        }

        public List<String> getTasks() {
            return tasks;
        }

        public void setTasks(List<String> tasks) {
            this.tasks = tasks;
        }

        @Override
        public String toString() {
            return "\nObject{" +
                    "\n_links=" + _links +
                    ", \ndeliveries=" + deliveries +
                    ", \ndescription='" + description + '\'' +
                    ", \nid=" + id +
                    ", \nname='" + name + '\'' +
                    ", \nreward=" + reward +
                    ", \ntasks=" + tasks +
                    '}';
        }
    }

    // TODO - Ist das hier immer ein Linkt auf die nächste Quest?
    public class Link{

        private String deliveries;
        private String self;
        private String tasks;

        public Link(String deliveries, String self, String tasks) {
            this.deliveries = deliveries;
            this.self = self;
            this.tasks = tasks;
        }

        public String getDeliveries() {
            return deliveries;
        }

        public void setDeliveries(String deliveries) {
            this.deliveries = deliveries;
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getTasks() {
            return tasks;
        }

        public void setTasks(String tasks) {
            this.tasks = tasks;
        }

        @Override
        public String toString() {
            return "\nLink{" +
                    "\ndeliveries='" + deliveries + '\'' +
                    ", \nself='" + self + '\'' +
                    ", \ntasks='" + tasks + '\'' +
                    '}';
        }
    }
}
