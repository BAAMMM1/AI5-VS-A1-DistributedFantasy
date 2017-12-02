package app.layerLogicAndService.cmpHeroService.dto;

/**
 * @author Chris on 01.12.2017
 */
public class AssignmentRequest {

    private int id;
    private String task;
    private String resource;
    private String method;
    private String data;
    private String callback;
    private String message;

    public AssignmentRequest(int id, String task, String resource, String method, String data, String callback, String message) {
        this.id = id;
        this.task = task;
        this.resource = resource;
        this.method = method;
        this.data = data;
        this.callback = callback;
        this.message = message;
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

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AssignmentRequest{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", resource='" + resource + '\'' +
                ", method='" + method + '\'' +
                ", data='" + data + '\'' +
                ", callback='" + callback + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
