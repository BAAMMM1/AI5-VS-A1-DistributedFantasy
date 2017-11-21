package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

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



    private Object object;
    private String status;

    public QuestDTO(Object object, String status) {
        this.object = object;
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
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

    // TODO - Class Object Codeduplizierung
    public class Object{

        private QuestsDTO.Link _links;
        private List<Integer> deliveries;
        private String description;
        private List<Integer> followups;
        private List<Integer> prerequisites;
        private String requirements;
        private int id;
        private String name;
        private int reward;
        private List<String> tasks;

        public Object(QuestsDTO.Link _links, List<Integer> deliveries, String description, List<Integer> followups, List<Integer> prerequisites, String requirements, int id, String name, int reward, List<String> tasks) {
            this._links = _links;
            this.deliveries = deliveries;
            this.description = description;
            this.followups = followups;
            this.prerequisites = prerequisites;
            this.requirements = requirements;
            this.id = id;
            this.name = name;
            this.reward = reward;
            this.tasks = tasks;
        }

        public QuestsDTO.Link get_links() {
            return _links;
        }

        public void set_links(QuestsDTO.Link _links) {
            this._links = _links;
        }

        public List<Integer> getDeliveries() {
            return deliveries;
        }

        public void setDeliveries(List<Integer> deliveries) {
            this.deliveries = deliveries;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Integer> getFollowups() {
            return followups;
        }

        public void setFollowups(List<Integer> followups) {
            this.followups = followups;
        }

        public List<Integer> getPrerequisites() {
            return prerequisites;
        }

        public void setPrerequisites(List<Integer> prerequisites) {
            this.prerequisites = prerequisites;
        }

        public String getRequirements() {
            return requirements;
        }

        public void setRequirements(String requirements) {
            this.requirements = requirements;
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
                    ", \nfollowups=" + followups +
                    ", \nprerequisites=" + prerequisites +
                    ", \nrequirements='" + requirements + '\'' +
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
