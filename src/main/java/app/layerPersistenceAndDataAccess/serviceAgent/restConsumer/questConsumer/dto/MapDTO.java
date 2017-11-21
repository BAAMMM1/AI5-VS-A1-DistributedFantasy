package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class MapDTO {

    private Object object;
    private String status;

    public MapDTO(Object object, String status) {
        this.object = object;
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MapDTO{" +
                "\nobject=" + object +
                ", \nstatus='" + status + '\'' +
                '}';
    }

    public class Object{
        private String host;
        private String name;
        private List<Integer> tasks;
        private List<Integer> visitors;

        public Object(String host, String name, List<Integer> tasks, List<Integer> visitors) {
            this.host = host;
            this.name = name;
            this.tasks = tasks;
            this.visitors = visitors;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getTasks() {
            return tasks;
        }

        public void setTasks(List<Integer> tasks) {
            this.tasks = tasks;
        }

        public List<Integer> getVisitors() {
            return visitors;
        }

        public void setVisitors(List<Integer> visitors) {
            this.visitors = visitors;
        }

        @Override
        public String toString() {
            return "\nObject{" +
                    "\nhost='" + host + '\'' +
                    ", \nname='" + name + '\'' +
                    ", \ntasks=" + tasks +
                    ", \nvisitors=" + visitors +
                    '}';
        }
    }
}
