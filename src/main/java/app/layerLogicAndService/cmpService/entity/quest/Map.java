package app.layerLogicAndService.cmpService.entity.quest;

import java.util.List;

/**
 * @author Chris on 21.11.2017
 */
public class Map{
    private String host;
    private String name;
    private List<Integer> tasks;
    private List<Integer> visitors;

    public Map(String host, String name, List<Integer> tasks, List<Integer> visitors) {
        this.host = host;
        this.name = name;
        this.tasks = tasks;
        this.visitors = visitors;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getTasks() {
        return tasks;
    }

    public void setTasks(List<Integer> tasks) {
        this.tasks = tasks;
    }

    public List<Integer> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Integer> visitors) {
        this.visitors = visitors;
    }

    @Override
    public String toString() {
        return "\nMap{" +
                "\nhost='" + host + '\'' +
                ", \nname='" + name + '\'' +
                ", \ntasks=" + tasks +
                ", \nvisitors=" + visitors +
                '}';
    }
}
