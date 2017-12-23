package app.layerLogicAndService.cmpService.entity.hero.mutex;

/**
 * @author Chris on 22.12.2017
 */
public class MutexMessageWrapper {

    String uuid;
    MutexMessage mutexMessage;
    String pathMutexState;

    public MutexMessageWrapper(String uuid, MutexMessage mutexMessage, String pathMutexState) {
        this.uuid = uuid;
        this.mutexMessage = mutexMessage;
        this.pathMutexState = pathMutexState;
    }

    public String getUuid() {
        return uuid;
    }


    public MutexMessage getMutexMessage() {
        return mutexMessage;
    }


    public String getPathMutexState() {
        return pathMutexState;
    }


    @Override
    public String toString() {
        return "MutexMessageWrapper{" +
                "uuid='" + uuid + '\'' +
                ", mutexMessage=" + mutexMessage +
                ", pathMutexState='" + pathMutexState + '\'' +
                '}';
    }
}
