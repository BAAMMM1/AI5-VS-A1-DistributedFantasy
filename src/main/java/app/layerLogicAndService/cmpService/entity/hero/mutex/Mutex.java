package app.layerLogicAndService.cmpService.entity.hero.mutex;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import org.apache.log4j.Logger;

/**
 * @author Chris on 09.12.2017
 */
public class Mutex {

    public final static Logger logger = Logger.getLogger(new Object() {
    }.getClass().getEnclosingClass());


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

    public void incrementRequestTime(int requestTime){

        logger.info("increment time");

        if(requestTime > time){
            logger.info("incoming time is higher");

            this.time = requestTime;
            this.time++;

            logger.info("time ist now at: " + Blackboard.getInstance().getUser().getMutex().getTime());

        } else {
            logger.info("incoming time is lower");

            this.time++;

            logger.info("time ist now at: " + Blackboard.getInstance().getUser().getMutex().getTime());

        }

    }

    public void incrementSendTime(){
        this.time++;
    }

    @Override
    public String toString() {
        return "Mutex{" +
                "state=" + state +
                ", time=" + time +
                '}';
    }

}
