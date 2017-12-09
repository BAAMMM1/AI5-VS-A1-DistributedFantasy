package app.layerLogicAndService.cmpService.exception;

/**
 * @author Chris on 05.12.2017
 */
public class AlreadyInGroupException extends Throwable {

    ErrorMessage error;

    public AlreadyInGroupException(String s) {
        super(s);
        error = new ErrorMessage(s);
    }

    public ErrorMessage getError() {
        return error;
    }


}
