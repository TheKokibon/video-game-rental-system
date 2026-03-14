package transfer;

import java.io.Serializable;

public class Request implements Serializable {

    private int operacija;
    private Object podatak;

    public Request() {
    }

    public Request(int operacija, Object podatak) {
        this.operacija = operacija;
        this.podatak = podatak;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getPodatak() {
        return podatak;
    }

    public void setPodatak(Object podatak) {
        this.podatak = podatak;
    }
}