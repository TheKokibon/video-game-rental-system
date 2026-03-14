package thread;

import constants.Operacije;
import controller.Controller;
import domain.Iznajmljivanje;
import domain.Osoba;
import domain.StavkaIznajmljivanja;
import domain.VideoIgra;
import domain.Zaposleni;
import form.FrmServer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import transfer.Request;
import transfer.Response;
import transfer.ResponseStatus;

public class ClientThread extends Thread {

    private final Socket socket;
    private final FrmServer frmServer;
    private Zaposleni ulogovani;

    public ClientThread(Socket socket, FrmServer frmServer) {
        this.socket = socket;
        this.frmServer = frmServer;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Request request = (Request) in.readObject();
                Response response = new Response();

                try {
                    switch (request.getOperacija()) {
                        case Operacije.LOGIN:
                            Zaposleni z = (Zaposleni) request.getPodatak();
                            ulogovani = Controller.getInstance().login(z);

                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(ulogovani);

                            frmServer.osveziTabelu(Controller.getInstance().getUlogovani());
                            break;

                        case Operacije.LOGOUT:
                            Zaposleni logoutZaposleni = (Zaposleni) request.getPodatak();

                            Controller.getInstance().logout(logoutZaposleni);

                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(null);

                            frmServer.osveziTabelu(Controller.getInstance().getUlogovani());
                            break;

                        case Operacije.KREIRAJ_OSOBU:

                            Osoba osoba = (Osoba) request.getPodatak();

                            Controller.getInstance().kreirajOsobu(osoba);

                            response.setStatus(ResponseStatus.SUCCESS);
                            break;

                        case Operacije.UCITAJ_OSOBE:
                            java.util.List<Osoba> ucitaneOsobe = Controller.getInstance().ucitajOsobe();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(ucitaneOsobe);
                            break;

                        case Operacije.PRETRAZI_OSOBE:
                            List<Osoba> osobe = Controller.getInstance().pretraziOsobe((Osoba) request.getPodatak());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(osobe);
                            break;

                        case Operacije.PROMENI_OSOBU:
                            Controller.getInstance().promeniOsobu((Osoba) request.getPodatak());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(null);
                            break;

                        case Operacije.OBRISI_OSOBU:
                            Controller.getInstance().obrisiOsobu((Osoba) request.getPodatak());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(null);
                            break;

                        case Operacije.KREIRAJ_VIDEO_IGRU:

                            VideoIgra videoIgra = (VideoIgra) request.getPodatak();

                            Controller.getInstance().kreirajVideoIgru(videoIgra);

                            response.setStatus(ResponseStatus.SUCCESS);
                            break;

                        case Operacije.UCITAJ_VIDEO_IGRE:
                            List<VideoIgra> videoIgre = Controller.getInstance().ucitajVideoIgre();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(videoIgre);
                            break;

                        case Operacije.PRETRAZI_VIDEO_IGRE:
                            List<VideoIgra> pronadjeneVideoIgre = Controller.getInstance().pretraziVideoIgre((VideoIgra) request.getPodatak());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(pronadjeneVideoIgre);
                            break;

                        case Operacije.OBRISI_VIDEO_IGRU:
                            Controller.getInstance().obrisiVideoIgru((VideoIgra) request.getPodatak());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(null);
                            break;

                        case Operacije.PROMENI_VIDEO_IGRU:
                            Controller.getInstance().promeniVideoIgru((VideoIgra) request.getPodatak());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(null);
                            break;

                        case Operacije.KREIRAJ_IZNAJMLJIVANJE:
                            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) request.getPodatak();
                            Controller.getInstance().kreirajIznajmljivanje(iznajmljivanje);
                            response.setPodatak(iznajmljivanje);
                            break;

                        case Operacije.PROMENI_IZNAJMLJIVANJE:
                            Iznajmljivanje iz = (Iznajmljivanje) request.getPodatak();
                            System.out.println("CLIENT THREAD - STAVKE: " + (iz.getStavke() == null ? "null" : iz.getStavke().size()));

                            Controller.getInstance().izmeniIznajmljivanje(iz);
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(null);
                            break;

                        case Operacije.UCITAJ_IZNAJMLJIVANJA:
                            List<Iznajmljivanje> lista = Controller.getInstance().ucitajIznajmljivanja();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(lista);
                            break;

                        case Operacije.UCITAJ_STAVKE_IZNAJMLJIVANJA:
                            List<StavkaIznajmljivanja> stavke
                                    = Controller.getInstance().ucitajStavkeIznajmljivanja(
                                            ((StavkaIznajmljivanja) request.getPodatak()).getIznajmljivanje()
                                    );
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(stavke);
                            break;

                        case Operacije.PRETRAZI_IZNAJMLJIVANJA:
                            List<Iznajmljivanje> listaIznajmljivanja
                                    = Controller.getInstance().pretraziIznajmljivanja((Iznajmljivanje) request.getPodatak());
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.setPodatak(listaIznajmljivanja);
                            break;

                        default:
                            throw new Exception("Operacija nije podrzana.");
                    }

                } catch (Exception ex) {
                    response.setStatus(ResponseStatus.ERROR);
                    response.setException(ex);
                }

                out.reset();
                out.writeObject(response);
                out.flush();
            }

        } catch (Exception e) {
            if (ulogovani != null) {
                Controller.getInstance().logout(ulogovani);
                frmServer.osveziTabelu(Controller.getInstance().getUlogovani());
            }
        }
    }
}
