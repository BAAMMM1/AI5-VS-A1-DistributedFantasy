package app.layerLogicAndService.cmpService.entity.hero.mutex;

/**
 * @author Chris on 22.12.2017
 */
public class MutexMessage {

    private String msg;     // request oder reply-ok
    private int time;
    private String reply;
    private String user;

    public MutexMessage(String msg, int time, String reply, String user) {
        this.msg = msg;
        this.time = time;
        this.reply = reply;
        this.user = user;
    }

    public MutexMessage() {
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
}
