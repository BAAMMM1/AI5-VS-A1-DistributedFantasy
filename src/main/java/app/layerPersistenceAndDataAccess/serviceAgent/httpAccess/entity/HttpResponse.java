package app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity;

/**
 * @author Christian G. on 03.11.2017
 */
public class HttpResponse {

    private final int code;
    private final String body;

    public HttpResponse(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "code=" + code +
                ", body='" + body + '\'' +
                '}';
    }
}
