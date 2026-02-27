package server;

import java.net.ServerSocket;
import java.net.Socket;
import server.thread.ClientHandler;

public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Server radi na portu " + port);

        while (true) {
            Socket socket = ss.accept();
            System.out.println("Klijent povezan");
            new ClientHandler(socket).start();
        }
    }
}