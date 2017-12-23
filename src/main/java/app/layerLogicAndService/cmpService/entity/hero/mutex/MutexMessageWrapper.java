package app.layerLogicAndService.cmpService.entity.hero.mutex;

/**
 * @author Chris on 22.12.2017
 */
public class MutexMessageWrapper {

    String name;
    String uuid;
    MutexMessage mutexMessage;
    String pathMutexState;


    public MutexMessageWrapper(String name, String uuid, MutexMessage mutexMessage, String pathMutexState) {
        this.name = name;
        this.uuid = uuid;
        this.mutexMessage = mutexMessage;
        this.pathMutexState = pathMutexState;
    }

    public MutexMessageWrapper() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public MutexMessage getMutexMessage() {
        return mutexMessage;
    }

    public void setMutexMessage(MutexMessage mutexMessage) {
        this.mutexMessage = mutexMessage;
    }

    public String getPathMutexState() {
        return pathMutexState;
    }

    public void setPathMutexState(String pathMutexState) {
        this.pathMutexState = pathMutexState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MutexMessageWrapper{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", mutexMessage=" + mutexMessage +
                ", pathMutexState='" + pathMutexState + '\'' +
                '}';
    }

}
