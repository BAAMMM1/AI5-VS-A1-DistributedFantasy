package app.layerLogicAndService.cmpQuest.entity;

import java.util.List;

/**
 * @author Chris on 19.11.2017
 */
public class Visit {

    /*

{
  "message": "\nYou enter the dungeon and find a total mess.\nIn the far back you hear the shuffling of rats.\n        ",
  "next": "/floor_u1/rats"
}


{
  "message": "\nYou approach the rats and they obviously do not fear you.\nRemember you are not done until all rats are gone or you are gone.\n(Resolve all steps and answerToCurrentUri the tokens here afterwards)\n        ",
  "required_players": 1,
  "required_tokens": [
    "Token:Rat Tail",
    "Token:Rat Eye",
    "Token:Rat Leg"
  ],
  "steps_todo": [
    "/floor_u1/rats/1",
    "/floor_u1/rats/2",
    "/floor_u1/rats/3"
  ]
}




     */

    private String message;
    private String next;
    private int required_players;
    private List<String> required_tokens;
    private List<String> steps_todo;

    public Visit(String message, String next, int required_players, List<String> required_tokens, List<String> steps_todo) {
        this.message = message;
        this.next = next;
        this.required_players = required_players;
        this.required_tokens = required_tokens;
        this.steps_todo = steps_todo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getRequired_players() {
        return required_players;
    }

    public void setRequired_players(int required_players) {
        this.required_players = required_players;
    }

    public List<String> getRequired_tokens() {
        return required_tokens;
    }

    public void setRequired_tokens(List<String> required_tokens) {
        this.required_tokens = required_tokens;
    }

    public List<String> getSteps_todo() {
        return steps_todo;
    }

    public void setSteps_todo(List<String> steps_todo) {
        this.steps_todo = steps_todo;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "\nmessage='" + message + '\'' +
                ", \nnext='" + next + '\'' +
                ", \nrequired_players=" + required_players +
                ", \nrequired_tokens=" + required_tokens +
                ", \nsteps_todo=" + steps_todo +
                '}';
    }
}
