package app.xlayerMiddleware.logicalClock;

import java.util.Random;

/**
 * @author Chris on 28.01.2018
 */
public class LamportClock {

    private static final int tickRate = 1;
    private int time;

    private static LamportClock instance;

    public static LamportClock getInstance(){

        if (instance == null){
            return new LamportClock();
        } else {
            return instance;
        }
    }

    private LamportClock() {
        this.time = new Random().nextInt(150);
    }

    public void tick(){
        this.time += tickRate;

    }

    // happened-beforce
    public void tick(int incomeValue){
        time = Math.max(this.time, incomeValue) + 1;

    }

    public int getTime() {
        return time;
    }
}
