package domain;

import java.io.Serializable;

public class Clan implements Serializable {
    private static final long serialVersionUID = 1L;

    private int clanID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private boolean aktivan;
    private Administrator admin;

    public Clan() {}

    public int getClanID() { return clanID; }
    public void setClanID(int clanID) { this.clanID = clanID; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public boolean isAktivan() { return aktivan; }
    public void setAktivan(boolean aktivan) { this.aktivan = aktivan; }
    
  public Administrator getAdministrator() {return admin;}

    public void setAdministrator(Administrator admin) {this.admin = admin;}
    

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

  
}