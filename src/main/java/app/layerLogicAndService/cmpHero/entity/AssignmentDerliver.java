package app.layerLogicAndService.cmpHero.entity;

/**
 * @author Chris on 05.12.2017
 */
public class AssignmentDerliver {

    private int id;
    private String task;
    private String resource;
    private String method;
    private String data;
    private String user;
    private String message;

    public AssignmentDerliver(int id, String task, String resource, String method, String data, String user, String message) {
        this.id = id;
        this.task = task;
        this.resource = resource;
        this.method = method;
        this.data = data;
        this.user = user;
        this.message = message;

        Assignment.counter = Assignment.counter + 1;
    }

    public AssignmentDerliver() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String callback) {
        this.user = callback;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", resource='" + resource + '\'' +
                ", method='" + method + '\'' +
                ", data='" + data + '\'' +
                ", user='" + user + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
