package domain;

import java.io.Serializable;
import java.sql.Date;

public class Clanarina implements Serializable {
    private static final long serialVersionUID = 1L;

    private int clanarinaID;
    private Date datumPocetka;
    private Date datumIsteka;
    private String status;   // npr "AKTIVNA"
    private Clan clan;
    private Paket paket;

    public Clanarina() {
    }

    public Clanarina(int clanarinaID, Date datumPocetka, Date datumIsteka, String status, Clan clan, Paket paket) {
        this.clanarinaID = clanarinaID;
        this.datumPocetka = datumPocetka;
        this.datumIsteka = datumIsteka;
        this.status = status;
        this.clan = clan;
        this.paket = paket;
    }
    
    

    public int getClanarinaID() { return clanarinaID; }
    public void setClanarinaID(int clanarinaID) { this.clanarinaID = clanarinaID; }

    public Date getDatumPocetka() { return datumPocetka; }
    public void setDatumPocetka(Date datumPocetka) { this.datumPocetka = datumPocetka; }

    public Date getDatumIsteka() { return datumIsteka; }
    public void setDatumIsteka(Date datumIsteka) { this.datumIsteka = datumIsteka; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Clan getClan() { return clan; }
    public void setClan(Clan clan) { this.clan = clan; }

    public Paket getPaket() { return paket; }
    public void setPaket(Paket paket) { this.paket = paket; }
}