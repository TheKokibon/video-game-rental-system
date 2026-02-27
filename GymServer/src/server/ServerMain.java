package server;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        new Server(9000).start();
    }
}