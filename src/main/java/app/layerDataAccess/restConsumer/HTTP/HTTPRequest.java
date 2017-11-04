package app.layerDataAccess.restConsumer.HTTP;

import java.net.URL;

/**
 * @author Christian G. on 04.11.2017
 */
public class HTTPRequest {

    private final URL url;
    private final EnumHTTPMethod method;
    private final String body;

    public HTTPRequest(URL url, EnumHTTPMethod method, String body) {
        this.url = url;
        this.method = method;
        this.body = body;
    }

    public HTTPRequest(URL url, EnumHTTPMethod method) {
        this.url = url;
        this.method = method;
        this.body = null;
    }

    public URL getUrl() {
        return url;
    }

    public EnumHTTPMethod getMethod() {
        return method;
    }

    public String getBody() {
        return body;
    }
}
