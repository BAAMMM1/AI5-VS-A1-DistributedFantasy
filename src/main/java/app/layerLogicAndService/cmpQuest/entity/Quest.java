package app.layerLogicAndService.cmpQuest.entity;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto.QuestsDTO;

import java.util.List;

/**
 * @author Chris on 21.11.2017
 */
public class Quest{

    private Link _links;
    private List<Integer> deliveries;
    private String description;
    private List<Integer> followups;
    private List<Integer> prerequisites;
    private String requirements;
    private int id;
    private String name;
    private int reward;
    private List<String> tasks;

    // TODO - Wird nicht entgegen genommen
    /*
    "requires_tokens": {
        "/blackboard/tasks/5": "Token:Carry a wounded person"
    },
    */


    public Quest(Link _links, List<Integer> deliveries, String description, List<Integer> followups, List<Integer> prerequisites, String requirements, int id, String name, int reward, List<String> tasks) {
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
        return "Quest{" +
                "\n_links=" + _links +
                ", \ndeliveries=" + deliveries +
                ", \ndescription='" + description + '\'' +
                ", \nfollowups=" + followups +
                ", \nprerequisites=" + prerequisites +
                ", \nrequirements='" + requirements + '\'' +
                ", \nid=" + id +
                ", \name='" + name + '\'' +
                ", \nreward=" + reward +
                ", \ntasks=" + tasks +
                '}';
    }
}
