package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto;

import java.util.List;

/**
 * @author Christian G. on 02.11.2017
 */
public class RegisterUserDTO {

    private String message;
    private Encryption encryption;
    private List<Encryption.Object> object;

    public RegisterUserDTO(String message, Encryption encryption, List<Encryption.Object> object) {
        this.message = message;
        this.encryption = encryption;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public List<Encryption.Object> getObject() {
        return object;
    }

    public void setObject(List<Encryption.Object> object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "RegisterUserDTO{" +
                "message='" + message + '\'' +
                ", encryption=" + encryption +
                ", object=" + object +
                '}';
    }

    public class Encryption {

        private String HMACK;
        private String algorithm;
        private String encryption;
        private String key;
        private String key_encoding;
        private int keylength;
        private String mode;
        private String padding;

        public Encryption(String HMACK, String algorithm, String encryption, String key, String key_encoding, int keylength, String mode, String padding) {
            this.HMACK = HMACK;
            this.algorithm = algorithm;
            this.encryption = encryption;
            this.key = key;
            this.key_encoding = key_encoding;
            this.keylength = keylength;
            this.mode = mode;
            this.padding = padding;
        }

        public String getHMACK() {
            return HMACK;
        }

        public void setHMACK(String HMACK) {
            this.HMACK = HMACK;
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getEncryption() {
            return encryption;
        }

        public void setEncryption(String encryption) {
            this.encryption = encryption;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey_encoding() {
            return key_encoding;
        }

        public void setKey_encoding(String key_encoding) {
            this.key_encoding = key_encoding;
        }

        public int getKeylength() {
            return keylength;
        }

        public void setKeylength(int keylength) {
            this.keylength = keylength;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getPadding() {
            return padding;
        }

        public void setPadding(String padding) {
            this.padding = padding;
        }

        public class Object {
            private WhoamiDTO.Link _links;
            List<Integer> deliverables_done;
            List<Integer> delivered;
            String ip;
            String location;
            String name;

            public Object(WhoamiDTO.Link _links, List<Integer> deliverables_done, List<Integer> delivered, String ip, String location, String name) {
                this._links = _links;
                this.deliverables_done = deliverables_done;
                this.delivered = delivered;
                this.ip = ip;
                this.location = location;
                this.name = name;
            }

            public WhoamiDTO.Link get_links() {
                return _links;
            }

            public void set_links(WhoamiDTO.Link _links) {
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

        public class Link {

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
}
