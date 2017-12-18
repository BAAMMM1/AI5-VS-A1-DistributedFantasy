package app.layerLogicAndService.cmpService.entity.quest.questing;

import app.layerLogicAndService.cmpService.entity.quest.Map;
import app.layerLogicAndService.cmpService.entity.quest.Task;

/**
 * @author Chris on 26.11.2017
 */
public class Questing {

    private Task task;
    private Map map;
    private String next;
    private String currentUri;

    // Falls quest mehrer TaskPart besitzen, hier eine Liste draus machen
    private TaskPart part;

    private String ringToken;

    public Questing(Task task, Map map, String currentUri) {
        this.task = task;
        this.map = map;
        this.currentUri = currentUri;
    }

    public String getRingToken() {
        return ringToken;
    }

    public void setRingToken(String ringToken) {
        this.ringToken = ringToken;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getCurrentUri() {
        return currentUri;
    }

    public void setCurrentUri(String currentUri) {
        this.currentUri = currentUri;
    }

    public TaskPart getPart() {
        return part;
    }

    public void setPart(TaskPart part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return "Questing{" +
                "\ntask=" + task +
                ", \nmap=" + map +
                ", \nnext='" + next + '\'' +
                ", \ncurrentUri='" + currentUri + '\'' +
                ", \npart=" + part +
                '}';
    }
}
