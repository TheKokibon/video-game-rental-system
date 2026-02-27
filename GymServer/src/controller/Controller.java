package controller;

import transfer.Request;
import transfer.Response;
import transfer.enums.RequestType;

public class Controller {

    private static final Controller instance = new Controller();

    private Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }
    private final service.AdministratorService adminService = new service.AdministratorService();
    private final service.ClanService clanService = new service.ClanService();
    private final service.PaketService paketService = new service.PaketService();
    private final service.ClanarinaService clanarinaService = new service.ClanarinaService();

    public Response handle(Request req) {
        try {

            System.out.println("REQ: " + req.getType() + " data=" + req.getData());

            if (req.getType() == RequestType.PING) {
                return Response.ok("PONG");
            }

            if (req.getType() == RequestType.LOGIN) {
                transfer.LoginRequest lr = (transfer.LoginRequest) req.getData();
                domain.Administrator admin
                        = adminService.login(lr.getUsername(), lr.getPassword());
                return Response.ok(admin);
            }

            if (req.getType() == RequestType.VRATI_LISTU_CLANOVA) {
                return Response.ok(clanService.getAll());
            }

            if (req.getType() == RequestType.SACUVAJ_CLANA) {
                domain.Clan c = (domain.Clan) req.getData();
                clanService.save(c);
                return Response.ok("OK");
            }

            if (req.getType() == RequestType.IZMENI_CLANA) {
                domain.Clan c = (domain.Clan) req.getData();
                clanService.update(c);
                return Response.ok("OK");
            }

            if (req.getType() == RequestType.OBRISI_CLANA) {
                Integer clanID = (Integer) req.getData();
                clanService.delete(clanID);
                return Response.ok("OK");
            }

            if (req.getType() == transfer.enums.RequestType.PRONADJI_CLANOVE) {
                String q = (String) req.getData();
                return transfer.Response.ok(clanService.search(q));
            }

            if (req.getType() == RequestType.VRATI_LISTU_PAKETA) {
                return Response.ok(paketService.getAll());
            }

            if (req.getType() == RequestType.SACUVAJ_PAKET) {
                domain.Paket p = (domain.Paket) req.getData();
                paketService.save(p);
                return Response.ok("OK");
            }

            if (req.getType() == RequestType.IZMENI_PAKET) {
                domain.Paket p = (domain.Paket) req.getData();
                paketService.update(p);
                return Response.ok("OK");
            }

            if (req.getType() == RequestType.OBRISI_PAKET) {
                Integer id = (Integer) req.getData();
                paketService.delete(id);
                return Response.ok("OK");
            }

            if (req.getType() == RequestType.PRONADJI_PAKETE) {
                String q = (String) req.getData();
                return Response.ok(paketService.search(q));
            }

            if (req.getType() == RequestType.DODELI_PAKET_CLANU) {
                domain.Clanarina cl = (domain.Clanarina) req.getData();
                clanarinaService.dodeliPaket(cl);
                return Response.ok("OK");
            }

            if (req.getType() == transfer.enums.RequestType.PRONADJI_AKTIVNU_CLANARINU_ZA_CLANA) {
                Integer clanId = (Integer) req.getData();
                return transfer.Response.ok(clanarinaService.vratiAktivnuZaClana(clanId));
            }

            if (req.getType() == transfer.enums.RequestType.PRODUZI_PAKET_CLANU) {
                transfer.ProduziPaketRequest pr = (transfer.ProduziPaketRequest) req.getData();
                clanarinaService.produziPaket(pr.getClanId(), pr.getNoviDatumIsteka());
                return transfer.Response.ok("OK");
            }

            return Response.error(
                    new Exception("Nepodrzana operacija: " + req.getType())
            );

        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
