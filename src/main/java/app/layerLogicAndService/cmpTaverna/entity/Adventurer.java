package app.layerLogicAndService.cmpTaverna.entity;

/**
 * @author Chris on 01.12.2017
 */
public class Adventurer {

    private String heroclass;
    private String capabilities;
    private String url;
    private String user;

    public Adventurer(String heroclass, String capabilities, String url, String user) {
        this.heroclass = heroclass;
        this.capabilities = capabilities;
        this.url = url;
        this.user = user;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Adventurer{" +
                "heroclass='" + heroclass + '\'' +
                ", capabilities='" + capabilities + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
