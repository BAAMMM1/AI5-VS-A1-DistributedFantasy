package app.layerLogicAndService.cmpService.entity.hero;

/**
 * @author Chris on 22.12.2017
 */
public enum MutexState {

    RELEASED("released"),
    WANTING("wanting"),
    HOLD("hold");

    private String name;

    MutexState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
