package app.layerLogicAndService.cmpService.entity.hero.mutex;

/**
 * @author Chris on 22.12.2017
 */
public enum MutexMsg {

    REQUEST("request"),
    REPLYOK("reply-ok");

    private String name;

    MutexMsg(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
