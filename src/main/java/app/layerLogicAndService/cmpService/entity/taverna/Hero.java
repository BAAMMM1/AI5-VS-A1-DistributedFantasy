package app.layerLogicAndService.cmpService.entity.taverna;

import java.util.List;

/**
 * @author Chris on 01.12.2017
 */
public class Hero {

    private String heroclass;
    private String capabilities;
    private String url;

    public Hero(String heroclass, String capabilities, String url) {
        this.heroclass = heroclass;
        this.capabilities = capabilities;
        this.url = url;
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

    @Override
    public String toString() {
        return "Hero{" +
                "heroclass='" + heroclass + '\'' +
                ", capabilities=" + capabilities +
                ", url='" + url + '\'' +
                '}';
    }
}
