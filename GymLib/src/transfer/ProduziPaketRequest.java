package transfer;

import java.io.Serializable;
import java.sql.Date;

public class ProduziPaketRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private int clanId;
    private Date noviDatumIsteka;

    public ProduziPaketRequest() {}

    public ProduziPaketRequest(int clanId, Date noviDatumIsteka) {
        this.clanId = clanId;
        this.noviDatumIsteka = noviDatumIsteka;
    }

    public int getClanId() { return clanId; }
    public void setClanId(int clanId) { this.clanId = clanId; }

    public Date getNoviDatumIsteka() { return noviDatumIsteka; }
    public void setNoviDatumIsteka(Date noviDatumIsteka) { this.noviDatumIsteka = noviDatumIsteka; }
}