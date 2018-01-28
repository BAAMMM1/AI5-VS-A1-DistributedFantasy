package app.layerLogicAndService.cmpService.entity.hero.mutex;

/**
 * @author Chris on 22.12.2017
 */
public class MutexRequestWrapper {

    String name;
    String uuid;
    MutexRequest mutexRequest;
    String pathMutexState;


    public MutexRequestWrapper(String name, String uuid, MutexRequest mutexRequest, String pathMutexState) {
        this.name = name;
        this.uuid = uuid;
        this.mutexRequest = mutexRequest;
        this.pathMutexState = pathMutexState;
    }

    public MutexRequestWrapper(String name, String uuid, String pathMutexState) {
        this.name = name;
        this.uuid = uuid;
        this.pathMutexState = pathMutexState;
    }

    public MutexRequestWrapper() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public MutexRequest getMutexRequest() {
        return mutexRequest;
    }

    public void setMutexRequest(MutexRequest mutexRequest) {
        this.mutexRequest = mutexRequest;
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
        return "MutexRequestWrapper{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", mutexRequest=" + mutexRequest +
                ", pathMutexState='" + pathMutexState + '\'' +
                '}';
    }

}
