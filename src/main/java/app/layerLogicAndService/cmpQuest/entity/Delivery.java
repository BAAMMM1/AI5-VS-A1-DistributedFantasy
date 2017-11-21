package app.layerLogicAndService.cmpQuest.entity;

import java.util.List;

/**
 * @author Chris on 21.11.2017
 */
public class Delivery{

    private List<Integer> deliverables;
    private int id;
    private int quest;
    private String timestamp;
    private String user;

    public Delivery(List<Integer> deliverables, int id, int quest, String timestamp, String user) {
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
        return "Delivery{" +
                "\ndeliverables=" + deliverables +
                ", \nid=" + id +
                ", \nquest=" + quest +
                ", \ntimestamp='" + timestamp + '\'' +
                ", \nuser='" + user + '\'' +
                '}';
    }
}
