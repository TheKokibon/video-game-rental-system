package server.thread;

import java.io.*;
import java.net.Socket;
import controller.Controller;
import transfer.Request;
import transfer.Response;

public class ClientHandler extends Thread {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    
    @Override
    public void run() {
        try (
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            out.flush(); // bitno

            while (true) {
                Request req = (Request) in.readObject();
                System.out.println("Primio objekat: " + req.getClass().getName());
                Response res = Controller.getInstance().handle(req);
                out.writeObject(res);
                out.flush();
            }

        } catch (Exception e) {
            System.out.println("Klijent je prekinuo vezu.");
        }
    }
}