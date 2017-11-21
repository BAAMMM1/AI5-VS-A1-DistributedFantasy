package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.exception;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.dto.ErrorDTO;

/**
 * @author Christian G. on 17.11.2017
 */
public class ErrorCodeException extends Throwable {

    private ErrorDTO errorDTO;

    public ErrorCodeException(ErrorDTO errorDTO) {

        if(errorDTO == null){
            throw new IllegalArgumentException("errorDTO must not be null");
        }

        this.errorDTO = errorDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }
}