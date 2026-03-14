package server;

import form.FrmServer;

public class MainServer {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new FrmServer().setVisible(true);
        });
    }
}