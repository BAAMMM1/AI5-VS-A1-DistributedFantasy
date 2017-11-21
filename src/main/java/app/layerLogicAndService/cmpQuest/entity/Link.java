package app.layerLogicAndService.cmpQuest.entity;

/**
 * @author Chris on 21.11.2017
 */
public class Link{
    /*
    Bei quest 1
    "_links": {
      "deliveries": "/blackboard/quests/1/deliveries",
      "self": "/blackboard/quests/1",
      "tasks": "/blackboard/quests/1/tasks"
    },

    Bei task 1
     "_links": {
      "self": "/blackboard/tasks/1"
    },


     */

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
