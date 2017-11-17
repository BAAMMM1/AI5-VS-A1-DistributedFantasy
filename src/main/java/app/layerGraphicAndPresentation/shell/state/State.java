package app.layerGraphicAndPresentation.shell.state;

/**
 * @author Christian G. on 17.11.2017
 */
public enum State {

    NOT_LOGIN("not login"),
    LOGIN("login");

    private String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
