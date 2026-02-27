package main;

import communication.Communication;
import javax.swing.JOptionPane;
import form.LoginForma;
import transfer.Request;
import transfer.Response;
import transfer.enums.RequestType;

public class ClientMain {

    public static void main(String[] args) {

        try {
            // 1) Povezivanje na server
            Communication.getInstance().connect("localhost", 9000);

            // 2) Otvaranje login forme (Swing thread)
            java.awt.EventQueue.invokeLater(() -> {
                new LoginForma().setVisible(true);
            });

            Response res = Communication.getInstance().send(
                    new Request(RequestType.VRATI_LISTU_CLANOVA, null)
            );

            if (res.isSuccessful()) {
                System.out.println(res.getResult());
            } else {
                System.out.println(res.getException().getMessage());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Ne mogu da se povežem na server.\n" + e.getMessage(),
                    "Greška",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}
