package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.questConsumer.dto;

import java.util.List;

/**
 * @author Chris on 26.11.2017
 */
public class DeliverTaskPartDTO {

    /*
    200
{
  "message": "\nYou lie in a stinking pool of blood, not shure how mutch of it is yours.\nBut all rats lie still now.\n        ",
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
  ],
  "token": "Z0FBQUFBQmFIQVRhTEdXUW5QQnRfbXItUS12d25qano5WEM1UkxIZHltNkFvMGVhUXZ5WVdUMkZMbGtaa0MzQ01pdVRYUVNPODlxNWYtN3VVNDlqdjFWZVhvUjdGRlBSaXBwY2tZLXlyOXZabUlraHRKeVo1TE8zSHdiV3dTdTFOYmp3OHkxOXJ3MnpJUFpPYTJBVERRM0dudjNOOHZjdVEzeENKYjlBUVIwWVhBb00yWVNZWHA0TDM5ZmhQVHNQSVBZaEZKUjh3dDBhRFpJUEFNWkRFOUpqS01xaVl1Y1ZVaDI1dkV5b1BwdllZbTFJNTc3M2FfZVZ3LTZfNG5nSjF2VklmRnlJeFZfWkJuWjcwVU5rNGJHVjMxMEVnZGc4Yi1hMEJEa196Q2hkQkdfam05S09GX3pYM2t0Y2dkUVZlWTNPWHphRVpDemRfQUVndkw2Ri01S2VkblU5TWEyYVdFZEhkeGlYSWtrd0lMMkhGSmEyY0t4UFpkdXgtWEpvUVdJYTB6UndwWUhNMVpmMWZiVFBaellPcUJPQS15eFlIUG94TjRBTWVlb1pvLXVSS0JrS3Q0eWFPMUxaeVJVMzl6UlFUWHVVWDJocVJJemRjUTl6VnU4d0wwcFY4bUt6ZmE0eHQ4Um1ORWwyQ05pajZONlBpUDNRbW91c3RSbGREaV93LVBGWDB3emVBR1pycG8wZ0R5NlVoMWNCUXk0WlBXOWlOS1hOVVJnN1laNnBoYzhRR1VZak5wdlRHYUFsMWhhZG5iejVaSzNWdHZGcmE1RFhrM1A3NW1nMkZHN2E3Q0JFUEZZWUhYakdJaHFLVVBpSTdyZ0h4a1gyZmM2Z3ZTUllYak9iRjlDaTVXcUp5OGlDUkpWTlgzOHY3bDFBZXQ4Rm9lZUg3VFZ0QkJqVlhucVBjQnRGUTlpY1NrSzliRlJodHREQkRfaEw=",
  "token_name": "Token:Kill rats"
}

     */

    private String message;
    private int required_players;
    private List<String> required_tokens;
    private List<String> steps_todo;
    private String token;
    private String token_name;

    public DeliverTaskPartDTO(String message, int required_players, List<String> required_tokens, List<String> steps_todo, String token, String token_name) {
        this.message = message;
        this.required_players = required_players;
        this.required_tokens = required_tokens;
        this.steps_todo = steps_todo;
        this.token = token;
        this.token_name = token_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_name() {
        return token_name;
    }

    public void setToken_name(String token_name) {
        this.token_name = token_name;
    }

    @Override
    public String toString() {
        return "DeliverTaskPartDTO{" +
                "\nmessage='" + message + '\'' +
                ", \nrequired_players=" + required_players +
                ", \nrequired_tokens=" + required_tokens +
                ", \nsteps_todo=" + steps_todo +
                ", \ntoken='" + token + '\'' +
                ", \ntoken_name='" + token_name + '\'' +
                '}';
    }
}
