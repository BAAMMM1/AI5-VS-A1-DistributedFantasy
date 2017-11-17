package app.layerPersistence.httpAccess;

/**
 * @author Christian G. on 03.11.2017
 */
public class HTTPResponse {

    private final int code;
    private final String body;

    public HTTPResponse(int code, String body) {
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
        return "HTTPResponse{" +
                "code=" + code +
                ", body='" + body + '\'' +
                '}';
    }
}
