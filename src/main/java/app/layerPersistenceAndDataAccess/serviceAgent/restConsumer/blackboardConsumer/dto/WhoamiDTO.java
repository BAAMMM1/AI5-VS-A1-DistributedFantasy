package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.blackboardConsumer.dto;

import java.util.List;

/**
 * @author Christian G. on 04.11.2017
 */
public class WhoamiDTO {

    String message;
    User user;

    public WhoamiDTO(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WhoamiDTO{" +
                "message='" + message + '\'' +
                ", user=" + user +
                '}';
    }

    public class User{
        private Link _links;
        List<Integer> deliverables_done;
        List<Integer> delivered;
        String ip;
        String location;
        String name;

        public User(Link _links, List<Integer> deliverables_done, List<Integer> delivered, String ip, String location, String name) {
            this._links = _links;
            this.deliverables_done = deliverables_done;
            this.delivered = delivered;
            this.ip = ip;
            this.location = location;
            this.name = name;
        }

        public Link get_links() {
            return _links;
        }

        public void set_links(Link _links) {
            this._links = _links;
        }

        public List<Integer> getDeliverables_done() {
            return deliverables_done;
        }

        public void setDeliverables_done(List<Integer> deliverables_done) {
            this.deliverables_done = deliverables_done;
        }

        public List<Integer> getDelivered() {
            return delivered;
        }

        public void setDelivered(List<Integer> delivered) {
            this.delivered = delivered;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
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

        @Override
        public String toString() {
            return "User{" +
                    "_links=" + _links +
                    ", deliverables_done=" + deliverables_done +
                    ", delivered=" + delivered +
                    ", ip='" + ip + '\'' +
                    ", location='" + location + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public class Link{

        private String encryption_key;
        private String self;

        public Link(String encryption_key, String self) {
            this.encryption_key = encryption_key;
            this.self = self;
        }

        public String getEncryption_key() {
            return encryption_key;
        }

        public void setEncryption_key(String encryption_key) {
            this.encryption_key = encryption_key;
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "encryption_key='" + encryption_key + '\'' +
                    ", self='" + self + '\'' +
                    '}';
        }
    }

}
