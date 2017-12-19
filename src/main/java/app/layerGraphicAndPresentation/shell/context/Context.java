package app.layerGraphicAndPresentation.shell.context;

import app.layerLogicAndService.cmpService.entity.blackboard.Blackboard;

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

            return "\n>> Distributed Fantasy @ " + Blackboard.getInstance().getUser().getName() +" # ";

        } else {
            return "\n>> Distributed Fantasy # ";
        }
    }

    /*
    Falls nötig auf static ändern, damit man den Prompt überall erreicht.
     */

}
