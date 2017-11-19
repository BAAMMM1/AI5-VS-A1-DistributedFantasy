package app.layerGraphicAndPresentation.shell.state;

import app.layerLogicAndService.cmpBlackboard.Blackboard;

/**
 * @author Christian G. on 17.11.2017
 */
public class Context {

   private State state;

    private static Context instance;

    private Context(State state) {
        this.state = state;
    }


    public static Context getInstance () {

        if (Context.instance == null) {
            Context.instance = new Context(State.NOT_LOGIN);

        }

        return Context.instance;

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
            return ">> Distributed Fantasy@" + Blackboard.getInstance().getName() +" >> ";

        } else {
            return ">> Distributed Fantasy >> ";
        }
    }

    /*
    Falls nötig auf static ändern, damit man den Prompt überall erreicht.
     */

}
