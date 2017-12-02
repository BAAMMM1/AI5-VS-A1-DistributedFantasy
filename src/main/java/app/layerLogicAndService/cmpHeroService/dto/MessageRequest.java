package app.layerLogicAndService.cmpHeroService.dto;

/**
 * @author Chris on 01.12.2017
 */
public class MessageRequest {

    private String message;
    private String status;
    private String typ;

    public MessageRequest(String message, String status, String typ) {
        this.message = message;
        this.status = status;
        this.typ = typ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", typ='" + typ + '\'' +
                '}';
    }
}
