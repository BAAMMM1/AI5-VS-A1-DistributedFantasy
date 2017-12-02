package app.layerLogicAndService.cmpHeroService.dto;

/**
 * @author Chris on 01.12.2017
 */
public class ServiceResponse {

    private String user;
    private boolean idle;
    private String group;
    private String hirings;
    private String assignments;
    private String messages;

    public ServiceResponse(String user, boolean idle, String group, String hirings, String assignments, String messages) {
        this.user = user;
        this.idle = idle;
        this.group = group;
        this.hirings = hirings;
        this.assignments = assignments;
        this.messages = messages;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getHirings() {
        return hirings;
    }

    public void setHirings(String hirings) {
        this.hirings = hirings;
    }

    public String getAssignments() {
        return assignments;
    }

    public void setAssignments(String assignments) {
        this.assignments = assignments;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "user='" + user + '\'' +
                ", idle=" + idle +
                ", group='" + group + '\'' +
                ", hirings='" + hirings + '\'' +
                ", assignments='" + assignments + '\'' +
                ", messages='" + messages + '\'' +
                '}';
    }
}
