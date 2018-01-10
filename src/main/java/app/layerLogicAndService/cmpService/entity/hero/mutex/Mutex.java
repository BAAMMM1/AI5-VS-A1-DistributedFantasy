package app.layerLogicAndService.cmpService.entity.hero.mutex;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;
import org.apache.log4j.Logger;

import java.util.Random;

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
        Random random = new Random();
        this.time = random.nextInt(150);
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

        logger.info("time: " + this.time + " - income time: " + requestTime);


        if(requestTime > time){
            logger.info("income time is higher");

            this.time = requestTime + 1;

            logger.info("set time to: " + Blackboard.getInstance().getUser().getMutex().getTime());

        } else {
            logger.info("income time is lower");

            this.time++;

            logger.info("set time to: " + Blackboard.getInstance().getUser().getMutex().getTime());

        }


    }

    public void incrementTimeStampSend(){
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
