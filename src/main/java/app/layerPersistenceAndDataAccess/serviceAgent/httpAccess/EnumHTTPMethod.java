package app.layerPersistenceAndDataAccess.serviceAgent.httpAccess;

/**
 * @author Christian G. on 04.11.2017
 */
public enum EnumHTTPMethod {

    DELETE("DELETE"),
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    private String name;

    EnumHTTPMethod(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
