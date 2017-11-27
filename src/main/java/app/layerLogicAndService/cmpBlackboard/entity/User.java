package app.layerLogicAndService.cmpBlackboard.entity;

import app.layerLogicAndService.cmpQuest.entity.Link;
import app.layerLogicAndService.cmpQuest.entity.Map;
import app.layerLogicAndService.cmpQuest.entity.Quest;
import app.layerLogicAndService.cmpQuest.entity.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 24.11.2017
 */
public class User {

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
    private List<String> capabilities = new ArrayList<String>();
    private String url = "";

    public User(String name, String userToken, String userTokenValidTime) {
        this.name = name;
        this.userToken = userToken;
        this.userTokenValidTime = userTokenValidTime;
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

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                ", \nurl='" + url + '\'' +
                '}';
    }
}
