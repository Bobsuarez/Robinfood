package co.com.robinfood.queue.Exceptions;

public class ValidateFieldsException extends ApplicationException {

    public ValidateFieldsException(Exception exception) {
        super(405, exception.getMessage());
    }

    public ValidateFieldsException(String inMessage) {
        super(405, inMessage);
    }
}
