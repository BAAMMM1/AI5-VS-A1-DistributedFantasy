package app.layerLogicAndService.cmpService.entity.hero.mutex;

/**
 * @author Chris on 09.12.2017
 */
public class Mutex {

    private String state;
    private int time;

    public Mutex() {
        this.setState(MutexState.RELEASED);
        this.time = 0;
    }

    public String getState() {
        return state;
    }

    public void setState(MutexState state) {
        this.state = state.toString();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Mutex{" +
                "state=" + state +
                ", time=" + time +
                '}';
    }

}
