package app.layerLogicAndService.cmpService.entity.quest;

import java.util.List;

/**
 * @author Chris on 21.11.2017
 */
public class Task{

    private Link _links;
    private List<Integer> deliverables;
    private String description;
    private int id;
    private String location;
    private String name;
    private int quest;
    private int required_players;
    private String resource;
    private String token;
    private String requirements;


    public Task(Link _links, List<Integer> deliverables, String description, int id, String location, String name, int quest, int required_players, String resource, String token, String requirements) {
        this._links = _links;
        this.deliverables = deliverables;
        this.description = description;
        this.id = id;
        this.location = location;
        this.name = name;
        this.quest = quest;
        this.required_players = required_players;
        this.resource = resource;
        this.token = token;
        this.requirements = requirements;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }

    public List<Integer> getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(List<Integer> deliverables) {
        this.deliverables = deliverables;
    }

    public String getDescription() {
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

    public int getQuest() {
        return quest;
    }

    public void setQuest(int quest) {
        this.quest = quest;
    }

    public int getRequired_players() {
        return required_players;
    }

    public void setRequired_players(int required_players) {
        this.required_players = required_players;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Task{" +
                "\n_links=" + _links +
                ", \ndeliverables=" + deliverables +
                ", \ndescription='" + description + '\'' +
                ", \nid=" + id +
                ", \nlocation='" + location + '\'' +
                ", \name='" + name + '\'' +
                ", \nquest=" + quest +
                ", \nrequired_players=" + required_players +
                ", \nresource='" + resource + '\'' +
                ", \ntoken='" + token + '\'' +
                ", \nrequirements='" + requirements + '\'' +
                '}';
    }
}
