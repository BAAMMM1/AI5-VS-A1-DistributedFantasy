package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonDto.ErrorDTO;

/**
 * @author Christian G. on 17.11.2017
 */
public class ErrorCodeException extends Throwable {

    private ErrorDTO errorDTO;

    public ErrorCodeException(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }
}
