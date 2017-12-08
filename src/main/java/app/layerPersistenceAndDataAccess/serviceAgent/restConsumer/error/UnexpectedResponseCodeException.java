package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.error;

/**
 * @author Christian G. on 17.11.2017
 */
public class UnexpectedResponseCodeException extends Throwable {

    private ErrorCodeDTO errorCodeDTO;

    public UnexpectedResponseCodeException(ErrorCodeDTO errorCodeDTO) {

        if(errorCodeDTO == null){
            throw new IllegalArgumentException("errorCodeDTO must not be null");
        }

        this.errorCodeDTO = errorCodeDTO;
    }

    public ErrorCodeDTO getErrorCodeDTO() {
        return errorCodeDTO;
    }
}
