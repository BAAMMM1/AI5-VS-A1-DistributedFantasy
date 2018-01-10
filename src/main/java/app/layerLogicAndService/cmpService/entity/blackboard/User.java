package app.layerLogicAndService.cmpService.entity.blackboard;

import app.layerLogicAndService.cmpService.entity.hero.Assignment;
import app.layerLogicAndService.cmpService.entity.hero.AssignmentDeliver;
import app.layerLogicAndService.cmpService.entity.hero.Message;
import app.layerLogicAndService.cmpService.entity.hero.mutex.Mutex;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexRequest;
import app.layerLogicAndService.cmpService.entity.hero.mutex.MutexRequestWrapper;
import app.layerLogicAndService.cmpService.entity.quest.Link;
import app.layerLogicAndService.cmpService.entity.quest.questing.Questing;
import app.layerLogicAndService.cmpService.entity.taverna.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 24.11.2017
 */
public class User{

    /*
    "user": {
    "_links": {
      "encryption_key": "/users/MeinSuperTestUser/encryption_key",
      "self": "/users/MeinSuperTestUser"
    },
    "deliverables_done": [
      23
    ],
    "delivered": [
      32
    ],
    "ip": null,
    "location": null,
    "name": "MeinSuperTestUser"
  }

     */


    private String name;
    private String userToken;
    private String userTokenValidTime;
    private Link _links;
    private List<Integer> deliverables_done;
    private List<Integer> delivered;
    private String ip;
    private String location;

    // TODO - Hard bis wir wissen wo es herkommt
    private String heroclass = "WiZzArD";
    private String capabilities ="group, election, bully, mutex";
    private String group = null;

    private Group currentGroup;

    private List<Message> messages;

    private Questing currentQuesting;

    private List<Assignment> assignmentList;

    private List<AssignmentDeliver> assignmentDeliverList;

    private List<Assignment> sendetAssignmentList;

    private boolean electionWinFlag = false;

    private boolean isDead = false;

    private Mutex mutex;

    private List<MutexRequest> receiveMutexRequestQueue;

    private List<MutexRequestWrapper> sendMutexRequestList;

    private int timeFromRequest = 0;

    public User(String name, String userToken, String userTokenValidTime) {
        this.name = name;
        this.userToken = userToken;
        this.userTokenValidTime = userTokenValidTime;
        this.messages = new ArrayList<>();
        this.assignmentDeliverList = new ArrayList<AssignmentDeliver>();
        this.assignmentList = new ArrayList<Assignment>();
        this.sendetAssignmentList = new ArrayList<Assignment>();
        this.mutex = new Mutex();
        this.receiveMutexRequestQueue = new ArrayList<MutexRequest>();
        this.sendMutexRequestList = new ArrayList<MutexRequestWrapper>();
    }

    public int getMutexRequestTime() {
        return timeFromRequest;
    }

    public void setMutexRquestedTimeStamp(int timeFromRequest) {
        this.timeFromRequest = timeFromRequest;
    }

    public List<MutexRequestWrapper> getSendMutexRequestList() {
        return sendMutexRequestList;
    }

    public List<MutexRequest> getReceiveMutexRequestQueue() {
        return receiveMutexRequestQueue;
    }

    public Mutex getMutex() {
        return mutex;
    }

    public void removeAllSendetAssignments(){
        this.sendetAssignmentList = new ArrayList<Assignment>();
    }

    public List<Assignment> getSendetAssignmentList() {
        return sendetAssignmentList;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public List<AssignmentDeliver> getAssignmentDeliverList() {
        return assignmentDeliverList;
    }

    public void addAssignmentDerliver(AssignmentDeliver assignmentDeliver){
        this.assignmentDeliverList.add(assignmentDeliver);
    }

    public void removeAllAssignmentDerliver(){
        this.assignmentDeliverList = new ArrayList<AssignmentDeliver>();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isElectionWinFlag() {
        return electionWinFlag;
    }

    public void setElectionWinFlag(boolean electionWinFlag) {
        this.electionWinFlag = electionWinFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserTokenValidTime() {
        return userTokenValidTime;
    }

    public void setUserTokenValidTime(String userTokenValidTime) {
        this.userTokenValidTime = userTokenValidTime;
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

    public String getHeroclass() {
        return heroclass;
    }

    public void setHeroclass(String heroclass) {
        this.heroclass = heroclass;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void removeMessage(){
        this.messages = new ArrayList<>();
    }

    public List<Message> getMessages(){
        return this.messages;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Questing getCurrentQuesting() {
        return currentQuesting;
    }

    public void setCurrentQuesting(Questing currentQuesting) {
        this.currentQuesting = currentQuesting;
    }

    public Group getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(Group currentGroup) {
        this.currentGroup = currentGroup;
    }


    @Override
    public String toString() {
        return "User{" +
                "\nname='" + name + '\'' +
                ", \nuserToken='" + userToken + '\'' +
                ", \nuserTokenValidTime='" + userTokenValidTime + '\'' +
                ", \n_links=" + _links +
                ", \ndeliverables_done=" + deliverables_done +
                ", \ndelivered=" + delivered +
                ", \nip='" + ip + '\'' +
                ", \nlocation='" + location + '\'' +
                ", \nheroclass='" + heroclass + '\'' +
                ", \ncapabilities=" + capabilities +
                '}';
    }
}
