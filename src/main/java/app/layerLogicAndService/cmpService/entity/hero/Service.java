package app.layerLogicAndService.cmpService.entity.hero;

/**
 * @author Chris on 01.12.2017
 */
public class Service {

    private String user;
    private boolean idle;
    private String group;
    private String hirings;
    private String assignments;
    private String messages;
    private String election;
    private String mutex;
    private String mutexstate;

    public Service(String user, boolean idle, String group, String hirings, String assignments, String messages, String election, String mutex, String mutexstate) {
        this.user = user;
        this.idle = idle;
        this.group = group;
        this.hirings = hirings;
        this.assignments = assignments;
        this.messages = messages;
        this.election = election;
        this.mutex = mutex;
        this.mutexstate = mutexstate;
    }

    public Service() {

    }

    public void setElection(String election) {
        this.election = election;
    }

    public String getMutex() {
        return mutex;
    }

    public void setMutex(String mutex) {
        this.mutex = mutex;
    }

    public String getMutexstate() {
        return mutexstate;
    }

    public void setMutexstate(String mutexstate) {
        this.mutexstate = mutexstate;
    }

    public String getElection() {
        return election;
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
        return "Service{" +
                "\nuser='" + user + '\'' +
                "\n, idle=" + idle +
                "\n, group='" + group + '\'' +
                "\n, hirings='" + hirings + '\'' +
                "\n, assignments='" + assignments + '\'' +
                "\n, messages='" + messages + '\'' +
                "\n, election='" + election + '\'' +
                "\n, mutex='" + mutex + '\'' +
                "\n, mutexstate='" + mutexstate + '\'' +
                '}';
    }
}
