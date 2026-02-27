package transfer;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean successful;
    private Object result;
    private Exception exception;

    public static Response ok(Object result) {
        Response r = new Response();
        r.successful = true;
        r.result = result;
        return r;
    }

    public static Response error(Exception e) {
        Response r = new Response();
        r.successful = false;
        r.exception = e;
        return r;
    }

    public boolean isSuccessful() { return successful; }
    public Object getResult() { return result; }
    public Exception getException() { return exception; }
}