package app.layerLogicAndService.cmpService.entity.hero.mutex;

/**
 * @author Chris on 22.12.2017
 */
public class MutexRequest {

    private String msg;     // request oder reply-ok
    private int time;
    private String reply;
    private String user;

    public MutexRequest(String msg, int time, String reply, String user) {
        this.msg = msg;
        this.time = time;
        this.reply = reply;
        this.user = user;
    }

    public MutexRequest() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MutexRequest{" +
                "msg='" + msg + '\'' +
                ", time=" + time +
                ", reply='" + reply + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
