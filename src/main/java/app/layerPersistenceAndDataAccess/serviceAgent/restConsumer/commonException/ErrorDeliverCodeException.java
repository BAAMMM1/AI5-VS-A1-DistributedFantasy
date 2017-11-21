package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonException;

import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonDto.ErrorDTO;
import app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.commonDto.ErrorDelivorDTO;

/**
 * @author Christian G. on 17.11.2017
 */
public class ErrorDeliverCodeException extends Throwable {

    private ErrorDelivorDTO errorDelivorDTO;

    public ErrorDeliverCodeException(ErrorDelivorDTO errorDelivorDTO) {

        if(errorDelivorDTO == null){
            throw new IllegalArgumentException("errorDelivorDTO must not be null");
        }

        this.errorDelivorDTO = errorDelivorDTO;
    }

    public ErrorDelivorDTO getErrorDelivorDTO() {
        return errorDelivorDTO;
    }
}
