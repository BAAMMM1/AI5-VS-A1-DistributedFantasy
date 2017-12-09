package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception;

import app.layerLogicAndService.cmpService.util.JSONUtil;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.entity.HttpResponse;

/**
 * @author Christian G. on 17.11.2017
 */
public class UnexpectedResponseCodeException extends Exception {

    HttpResponse response;

    private int code;

    public UnexpectedResponseCodeException(HttpResponse response) {
        super(JSONUtil.getObject(response.getBody(), "message", String.class));
        this.response = response;

    }

    public int getCode() {
        return response.getCode();
    }

    public HttpResponse getResponse() {
        return response;
    }

}
