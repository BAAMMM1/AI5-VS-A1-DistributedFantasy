package app.layerLogicAndService.cmpService.entity.hero.mutex;

/**
 * @author Chris on 22.12.2017
 */
public enum MutexState {

    RELEASED("released"),   // Client hält keinen lock, will keinen lock
    WANTING("wanting"),     // Client hält keinen lock, will aber einen lock
    HOLD("hold");           // Client hält einen lock und krann den kritischen Abschnitt betreten

    private String name;

    MutexState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
