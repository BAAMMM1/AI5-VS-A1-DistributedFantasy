package app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity;

import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.enums.EnumHTTPMethod;

/**
 * @author Christian G. on 04.11.2017
 */
public class HttpRequest {

    private final String url;
    private final EnumHTTPMethod method;
    private final String body;
    private boolean basicAuth = false;
    private String basicAuthUser;
    private String basicAuthPw;
    private boolean authorization = false;
    private String authorizationToken;
    private boolean acceptText = false;

    public HttpRequest(String url, EnumHTTPMethod method, String body) {
        this.url = url;
        this.method = method;
        this.body = body;
        this.basicAuthUser = null;
    }

    public HttpRequest(String url, EnumHTTPMethod method) {
        this.url = url;
        this.method = method;
        this.body = null;
    }

    public String getUrl() {
        return url;
    }

    public EnumHTTPMethod getMethod() {
        return method;
    }

    public String getBody() {
        return body;
    }

    public String getBasicAuthUser() {
        return basicAuthUser;
    }

    public String getBasicAuthPw() {
        return basicAuthPw;
    }

    public void setBasicAuth(String user, String password){
        this.basicAuthUser = user;
        this.basicAuthPw = password;
        this.basicAuth = true;
    }

    public boolean isBasicAuth() {
        return basicAuth;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
        this.authorization = true;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public boolean isAcceptText() {
        return acceptText;
    }

    public void setAcceptText(boolean acceptText) {
        this.acceptText = acceptText;
    }
}
