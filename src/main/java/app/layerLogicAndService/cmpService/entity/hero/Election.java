package app.layerLogicAndService.cmpService.entity.hero;

/**
 * @author Chris on 09.12.2017
 */
public class Election {

    // TODO - add here something
    String algorithm;   // name of the algorithm used
    String payload;     // he payload for the current state of the algorithm> - election, coordinator
    String user;        // uri of the user sending this request
    Assignment job;         // JSON description of the job to do
    String message;     // something you want to tell the other one

    public Election(String algorithm, String payload, String user, Assignment job, String message) {
        this.algorithm = algorithm;
        this.payload = payload;
        this.user = user;
        this.job = job;
        this.message = message;
    }

    public Election() {
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Assignment getJob() {
        return job;
    }

    public void setJob(Assignment job) {
        this.job = job;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Election{" +
                "algorithm='" + algorithm + '\'' +
                ", payload='" + payload + '\'' +
                ", user='" + user + '\'' +
                ", job=" + job +
                ", message='" + message + '\'' +
                '}';
    }
}
