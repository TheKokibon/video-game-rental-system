package communication;

import java.io.*;
import java.net.Socket;
import transfer.Request;
import transfer.Response;

public class Communication {
    private static Communication instance;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Communication() {}

    public static Communication getInstance() {
        if (instance == null) instance = new Communication();
        return instance;
    }

    public void connect(String host, int port) throws Exception {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
    }

    public Response send(Request request) throws Exception {
        out.writeObject(request);
        out.flush();
        return (Response) in.readObject();
    }
}