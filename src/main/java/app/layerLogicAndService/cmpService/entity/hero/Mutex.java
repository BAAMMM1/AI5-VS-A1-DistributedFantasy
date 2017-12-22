package app.layerLogicAndService.cmpService.entity.hero;

/**
 * @author Chris on 09.12.2017
 */
public class Mutex {

    MutexState state;
    int time;

    public Mutex() {
        this.state = MutexState.RELEASED;
        this.time = 0;
    }

    public MutexState getState() {
        return state;
    }

    public void setState(MutexState state) {
        this.state = state;
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
