package co.com.robinfood.queue.Exceptions;

public class JmsConnectionException extends ApplicationException {

    public JmsConnectionException(Exception exception) {
        super(500, exception.getMessage());
    }

    public JmsConnectionException(String inMessage) {
        super(500, inMessage);
    }
}
