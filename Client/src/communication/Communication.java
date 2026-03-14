package communication;

import constants.Operacije;
import domain.Iznajmljivanje;
import domain.Osoba;
import domain.StavkaIznajmljivanja;
import domain.VideoIgra;
import domain.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import transfer.Request;
import transfer.Response;
import transfer.ResponseStatus;

public class Communication {

    private static Communication instance;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Communication() throws Exception {
        socket = new Socket("127.0.0.1", 9000);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public Response posaljiZahtev(Request request) throws Exception {
        out.reset();
        out.writeObject(request);
        out.flush();
        return (Response) in.readObject();
    }

    public Response logout(Zaposleni zaposleni) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.LOGOUT);
        request.setPodatak(zaposleni);

        return posaljiZahtev(request);
    }

    public Response kreirajOsobu() throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.KREIRAJ_OSOBU);
        request.setPodatak(null);
        return posaljiZahtev(request);
    }

    public Response ucitajOsobe() throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.UCITAJ_OSOBE);
        request.setPodatak(new Osoba());

        return posaljiZahtev(request);
    }

    public List<Osoba> pretraziOsobe(Osoba osoba) throws Exception {
        Request req = new Request();
        req.setOperacija(Operacije.PRETRAZI_OSOBE);
        req.setPodatak(osoba);

        Response res = posaljiZahtev(req);

        if (res.getStatus() == ResponseStatus.ERROR) {
            throw res.getException();
        }

        return (List<Osoba>) res.getPodatak();
    }

    public Response promeniOsobu(Osoba osoba) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.PROMENI_OSOBU);
        request.setPodatak(osoba);
        return posaljiZahtev(request);
    }

    public Response obrisiOsobu(Osoba osoba) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.OBRISI_OSOBU);
        request.setPodatak(osoba);
        return posaljiZahtev(request);
    }

    public Response kreirajVideoIgru(VideoIgra videoIgra) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.KREIRAJ_VIDEO_IGRU);
        request.setPodatak(videoIgra);
        return posaljiZahtev(request);
    }

    public Response ucitajVideoIgre() throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.UCITAJ_VIDEO_IGRE);
        request.setPodatak(new VideoIgra());
        return posaljiZahtev(request);
    }

    public List<VideoIgra> pretraziVideoIgre(VideoIgra videoIgra) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.PRETRAZI_VIDEO_IGRE);
        request.setPodatak(videoIgra);

        Response response = posaljiZahtev(request);

        if (response.getStatus() == ResponseStatus.ERROR) {
            throw response.getException();
        }

        return (List<VideoIgra>) response.getPodatak();
    }

    public Response obrisiVideoIgru(VideoIgra videoIgra) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.OBRISI_VIDEO_IGRU);
        request.setPodatak(videoIgra);
        return posaljiZahtev(request);
    }

    public Response promeniVideoIgru(VideoIgra videoIgra) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.PROMENI_VIDEO_IGRU);
        request.setPodatak(videoIgra);
        return posaljiZahtev(request);
    }

    public Response kreirajIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.KREIRAJ_IZNAJMLJIVANJE);
        request.setPodatak(iznajmljivanje);
        return posaljiZahtev(request);
    }

    public Response promeniIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.PROMENI_IZNAJMLJIVANJE);
        request.setPodatak(iznajmljivanje);
        return posaljiZahtev(request);
    }

    public Response ucitajIznajmljivanja() throws Exception {
        Request request = new Request();
        request.setOperacija(Operacije.UCITAJ_IZNAJMLJIVANJA);
        request.setPodatak(new Iznajmljivanje());
        return posaljiZahtev(request);
    }

    public List<StavkaIznajmljivanja> ucitajStavkeIznajmljivanja(Iznajmljivanje iznajmljivanje) throws Exception {
        StavkaIznajmljivanja s = new StavkaIznajmljivanja();
        s.setIznajmljivanje(iznajmljivanje);

        Request request = new Request();
        request.setOperacija(Operacije.UCITAJ_STAVKE_IZNAJMLJIVANJA);
        request.setPodatak(s);

        Response response = posaljiZahtev(request);

        if (response.getStatus() == ResponseStatus.ERROR) {
            throw response.getException();
        }

        return (List<StavkaIznajmljivanja>) response.getPodatak();
    }
    
    public List<Iznajmljivanje> pretraziIznajmljivanja(Iznajmljivanje kriterijum) throws Exception {
    Request request = new Request();
    request.setOperacija(Operacije.PRETRAZI_IZNAJMLJIVANJA);
    request.setPodatak(kriterijum);

    Response response = posaljiZahtev(request);

    if (response.getStatus() == ResponseStatus.ERROR) {
        throw response.getException();
    }

    return (List<Iznajmljivanje>) response.getPodatak();
}

}
