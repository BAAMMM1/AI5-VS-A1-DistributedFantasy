package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class TaskDTO {

    private Object object;
    private String status;

    public TaskDTO(Object object, String status) {
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
        return "TaskDTO{" +
                "\nobject=" + object +
                ", \nstatus='" + status + '\'' +
                '}';
    }

    public class Object{

        private Link _links;
        private List<Integer> deliverables;
        private String description;
        private int id;
        private String location;
        private String name;
        private int quest;
        private int required_players;
        private String resource;
        private String token;;

        public Object(Link _links, List<Integer> deliverables, String description, int id, String location, String name, int quest, int required_players, String resource, String token) {
            this._links = _links;
            this.deliverables = deliverables;
            this.description = description;
            this.id = id;
            this.location = location;
            this.name = name;
            this.quest = quest;
            this.required_players = required_players;
            this.resource = resource;
            this.token = token;
        }

        public Link get_links() {
            return _links;
        }

        public void set_links(Link _links) {
            this._links = _links;
        }

        public List<Integer> getDeliverables() {
            return deliverables;
        }

        public void setDeliverables(List<Integer> deliverables) {
            this.deliverables = deliverables;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuest() {
            return quest;
        }

        public void setQuest(int quest) {
            this.quest = quest;
        }

        public int getRequired_players() {
            return required_players;
        }

        public void setRequired_players(int required_players) {
            this.required_players = required_players;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "\nObject{" +
                    "\n_links=" + _links +
                    ", \ndeliverables=" + deliverables +
                    ", \ndescription='" + description + '\'' +
                    ", \nid=" + id +
                    ", \nlocation='" + location + '\'' +
                    ", \nname='" + name + '\'' +
                    ", \nquest=" + quest +
                    ", \nrequired_players=" + required_players +
                    ", \nresource='" + resource + '\'' +
                    ", \ntoken='" + token + '\'' +
                    '}';
        }
    }


    // TODO - Ist das hier immer ein Linkt auf die nächste Quest?
    public class Link{

        private String self;

        public Link(String self) {
            this.self = self;
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }


        @Override
        public String toString() {
            return "\nLink{" +
                    ", \nself='" + self + '\'' +
                    '}';
        }
    }
}
