package app.layerLogicAndService.cmpQuest.entity.besuch;

import app.layerLogicAndService.cmpQuest.entity.Task;

/**
 * @author Chris on 26.11.2017
 */
public class Besuch {

    private Task task;
    private String host;
    private String next;
    private String currentUri;

    // Falls quest mehrer TaskPart besitzen, hier eine Liste draus machen
    private TaskPart part;

    public Besuch(Task task, String host, String currentUri) {
        this.task = task;
        this.host = host;
        this.currentUri = currentUri;
    }

    public Task getTask() {
        return task;
    }

    public String getHost() {
        return host;
    }

    public String getNext() {
        return next;
    }

    public String getCurrentUri() {
        return currentUri;
    }

    public TaskPart getPart() {
        return part;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setCurrentUri(String currentUri) {
        this.currentUri = currentUri;
    }

    public void setPart(TaskPart part) {
        this.part = part;
    }
}
