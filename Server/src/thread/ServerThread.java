package thread;

import form.FrmServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private final FrmServer frmServer;
    private boolean kraj = false;

    public ServerThread(FrmServer frmServer) {
        this.frmServer = frmServer;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);

            while (!kraj) {
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket, frmServer);
                clientThread.start();
            }

        } catch (IOException e) {
            if (!kraj) {
                e.printStackTrace();
            }
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}