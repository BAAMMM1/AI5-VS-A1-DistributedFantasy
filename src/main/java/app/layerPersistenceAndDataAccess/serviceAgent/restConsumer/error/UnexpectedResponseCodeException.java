package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error;

import app.layerLogicAndService.cmpBlackboard.util.JSONUtil;
import app.layerPersistenceAndDataAccess.serviceAgent.httpAccess.HTTPResponse;

/**
 * @author Christian G. on 17.11.2017
 */
public class UnexpectedResponseCodeException extends Exception {

    HTTPResponse response;

    private int code;

    public UnexpectedResponseCodeException(HTTPResponse response) {
        super(JSONUtil.getObject(response.getBody(), "message", String.class));
        this.response = response;

    }

    public int getCode() {
        return response.getCode();
    }

    public HTTPResponse getResponse() {
        return response;
    }

}
