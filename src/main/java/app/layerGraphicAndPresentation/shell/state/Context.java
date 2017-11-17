package app.layerGraphicAndPresentation.shell.state;

/**
 * @author Christian G. on 17.11.2017
 */
public class Context {

   private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {

        this.state = state;
    }

    public String getPromptState(){

        if(state.equals(State.LOGIN)){

            // TODO - login durch den login namen ersetzen
            return ">> Distributed Fantasy >> login >> ";

        } else {
            return ">> Distributed Fantasy >> ";
        }
    }

    /*
    Falls nötig auf static ändern, damit man den Prompt überall erreicht.
     */

}
