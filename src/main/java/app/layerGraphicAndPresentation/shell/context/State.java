package app.layerGraphicAndPresentation.shell.context;

/**
 * @author Christian G. on 17.11.2017
 */
public enum State {

    NOT_LOGIN("not login"),
    LOGIN("login"),
    UNCONNECTED("unconnected");

    private String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
