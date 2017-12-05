package app.layerLogicAndService.cmpHero.service.exception;

/**
 * @author Chris on 05.12.2017
 */
public class AlreadyInGroupException extends Throwable {

    Error error;

    public AlreadyInGroupException(String s) {
        super(s);
        error = new Error(s);
    }

    public Error getError() {
        return error;
    }

    public class Error{
        String message;

        public Error(String message) {
            this.message = message;
        }
    }
}
