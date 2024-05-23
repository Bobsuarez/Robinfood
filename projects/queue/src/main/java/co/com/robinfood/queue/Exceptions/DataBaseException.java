package co.com.robinfood.queue.Exceptions;

public class DataBaseException extends ApplicationException {

    public DataBaseException(Exception exception) {
        super(500, exception.getMessage());
    }

}
