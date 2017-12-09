package app.layerLogicAndService.cmpService.entity.blackboard;

import java.util.List;

/**
 * @author Christian G. on 02.11.2017
 */
public class Register {

    private String message;
    private Encryption encryption;
    private List<User> object;
    private String status;

    public Register(String message, Encryption encryption, List<User> object, String status) {
        this.message = message;
        this.encryption = encryption;
        this.object = object;
        this.status = status;
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

    public List<User> getObject() {
        return object;
    }

    public void setObject(List<User> object) {
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
        return "Register{" +
                "\nmessage='" + message + '\'' +
                ", \nencryption=" + encryption +
                ", \nobject=" + object +
                ", \nstatus='" + status + '\'' +
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

        @Override
        public String toString() {
            return "Encryption{" +
                    "\nHMACK='" + HMACK + '\'' +
                    ", \nalgorithm='" + algorithm + '\'' +
                    ", \nencryption='" + encryption + '\'' +
                    ", \nkey='" + key + '\'' +
                    ", \nkey_encoding='" + key_encoding + '\'' +
                    ", \nkeylength=" + keylength +
                    ", \nmode='" + mode + '\'' +
                    ", \npadding='" + padding + '\'' +
                    '}';
        }

    }
}
