package transfer;

import java.io.Serializable;

public class Response implements Serializable {

    private ResponseStatus status;
    private Object podatak;
    private Exception exception;

    public Response() {
    }

    public Response(ResponseStatus status, Object podatak, Exception exception) {
        this.status = status;
        this.podatak = podatak;
        this.exception = exception;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getPodatak() {
        return podatak;
    }

    public void setPodatak(Object podatak) {
        this.podatak = podatak;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}