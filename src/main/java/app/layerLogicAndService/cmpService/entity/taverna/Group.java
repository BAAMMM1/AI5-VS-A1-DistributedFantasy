package app.layerLogicAndService.cmpService.entity.taverna;

import app.layerLogicAndService.cmpService.entity.quest.Link;

import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public class Group {

    private Link _links;
    private int id;
    private List<String> members;
    private String owner;
    private String coordinator;

    public Group(Link _links, int id, List<String> members, String owner) {
        this._links = _links;
        this.id = id;
        this.members = members;
        this.owner = owner;
        this.coordinator = owner;
    }

    public Group() {
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Group{" +
                "_links=" + _links +
                ", id=" + id +
                ", members=" + members +
                ", owner='" + owner + '\'' +
                '}';
    }
}
