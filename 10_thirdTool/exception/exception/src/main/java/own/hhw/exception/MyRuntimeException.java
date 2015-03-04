package own.hhw.exception;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-3-12
 * Time: ÏÂÎç5:18
 * To change this template use File | Settings | File Templates.
 */
public class MyRuntimeException extends RuntimeException {
    public MyRuntimeException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public MyRuntimeException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public MyRuntimeException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
