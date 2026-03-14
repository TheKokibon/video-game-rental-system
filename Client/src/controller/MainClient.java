package controller;

import form.FrmLogin;

public class MainClient {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new FrmLogin().setVisible(true);
        });
    }
}