/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form.iznajmljivanje;

import communication.Communication;
import domain.Iznajmljivanje;
import domain.Osoba;
import domain.StavkaIznajmljivanja;
import domain.VideoIgra;
import domain.Zaposleni;
import form.model.ModelTabeleStavkeIznajmljivanja;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import transfer.Response;
import transfer.ResponseStatus;

/**
 *
 * @author PC
 */
public class KreirajIznajmljivanje extends javax.swing.JFrame {

    /**
     * Creates new form KreirajIznajmljivanje
     */
    
    Zaposleni ulogovani; 
    
    public KreirajIznajmljivanje(Zaposleni admin) {
        initComponents();
        this.ulogovani = admin;
        setLocationRelativeTo(this);
        pripremiFormu();
    }

    private void pripremiFormu() {
        tblStavke.setModel(new ModelTabeleStavkeIznajmljivanja());
        jlb.setEnabled(false);
        txtCena.setEnabled(false);

        if (ulogovani != null) {
            txtZaposleni.setText(ulogovani.toString());
        }

        txtDatum.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));

        ucitajOsobe();
        ucitajVideoIgre();
        jlb.setText("0");
    }

    private void ucitajOsobe() {
        try {
            Response response = Communication.getInstance().ucitajOsobe();

            if (response.getStatus() == ResponseStatus.ERROR) {
                throw response.getException();
            }

            List<Osoba> lista = (List<Osoba>) response.getPodatak();
            cmbOsoba.removeAllItems();

            for (Osoba o : lista) {
                cmbOsoba.addItem(o);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju osoba: " + ex.getMessage());
        }
    }

    private void ucitajVideoIgre() {
        try {
            Response response = Communication.getInstance().ucitajVideoIgre();

            if (response.getStatus() == ResponseStatus.ERROR) {
                throw response.getException();
            }

            List<VideoIgra> lista = (List<VideoIgra>) response.getPodatak();
            cmbVideoIgra.removeAllItems();

            for (VideoIgra vi : lista) {
                cmbVideoIgra.addItem(vi);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju video igara: " + ex.getMessage());
        }
    }

    private void izracunajCenuStavke() {
        try {
            VideoIgra vi = (VideoIgra) cmbVideoIgra.getSelectedItem();
            if (vi == null) {
                txtCena.setText("");
                return;
            }

            String brojDanaTekst = txtBrojDana.getText().trim();
            if (brojDanaTekst.isEmpty()) {
                txtCena.setText("");
                return;
            }

            int brojDana = Integer.parseInt(brojDanaTekst);
            if (brojDana <= 0) {
                txtCena.setText("");
                return;
            }

            double cena = vi.getCenaIznajmljivanja() * brojDana;
            txtCena.setText(String.valueOf(cena));

        } catch (Exception e) {
            txtCena.setText("");
        }
    }

    private void osveziUkupanIznos() {
        ModelTabeleStavkeIznajmljivanja model = (ModelTabeleStavkeIznajmljivanja) tblStavke.getModel();
        double ukupno = 0;

        for (StavkaIznajmljivanja s : model.getLista()) {
            ukupno += s.getCena();
        }

        jlb.setText(String.valueOf(ukupno));
    }

    private void validirajStavku() throws Exception {
        if (cmbVideoIgra.getSelectedItem() == null) {
            throw new Exception("Morate izabrati video igru.");
        }

        if (txtBrojDana.getText().trim().isEmpty()) {
            throw new Exception("Broj dana je obavezan.");
        }

        int brojDana;
        try {
            brojDana = Integer.parseInt(txtBrojDana.getText().trim());
        } catch (NumberFormatException e) {
            throw new Exception("Broj dana mora biti ceo broj.");
        }

        if (brojDana <= 0) {
            throw new Exception("Broj dana mora biti veći od 0.");
        }
    }

    private void validirajFormu() throws Exception {
        if (cmbOsoba.getSelectedItem() == null) {
            throw new Exception("Morate izabrati osobu.");
        }

        ModelTabeleStavkeIznajmljivanja model = (ModelTabeleStavkeIznajmljivanja) tblStavke.getModel();
        if (model.getLista().isEmpty()) {
            throw new Exception("Morate uneti bar jednu stavku.");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtZaposleni = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCena = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStavke = new javax.swing.JTable();
        btnObrisiStavku = new javax.swing.JButton();
        btnDodajStavku = new javax.swing.JButton();
        txtBrojDana = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnKreirajIznajmljivanje = new javax.swing.JButton();
        cmbOsoba = new javax.swing.JComboBox();
        cmbVideoIgra = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtDatum = new javax.swing.JTextField();
        txtUkupanIznos1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Forma Kreiraj Iznajmljivanje");

        txtZaposleni.setText("jLabel1");

        jLabel1.setText("Osobe: ");

        jLabel2.setText("Video igra:");

        jlb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlb.setText("Ukupna cena:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Stavka iznajmljivanja"));

        jLabel3.setText("Broj dana: ");

        txtCena.setText("jLabel4");

        tblStavke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblStavke);

        btnObrisiStavku.setText("Obriši stavku");
        btnObrisiStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiStavkuActionPerformed(evt);
            }
        });

        btnDodajStavku.setText("Dodaj stavku");
        btnDodajStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajStavkuActionPerformed(evt);
            }
        });

        txtBrojDana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBrojDanaKeyReleased(evt);
            }
        });

        jLabel4.setText("Cena:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtBrojDana, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtCena)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnObrisiStavku)
                        .addGap(31, 31, 31)
                        .addComponent(btnDodajStavku)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBrojDana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCena)
                    .addComponent(btnObrisiStavku)
                    .addComponent(btnDodajStavku)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnKreirajIznajmljivanje.setText("Kreiraj iznajmljivanje");
        btnKreirajIznajmljivanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKreirajIznajmljivanjeActionPerformed(evt);
            }
        });

        cmbVideoIgra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVideoIgraActionPerformed(evt);
            }
        });

        jLabel5.setText("Datum iznajmljivanja: (yyyy-mm-dd):");

        txtUkupanIznos1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtUkupanIznos1.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtZaposleni)
                .addGap(201, 201, 201))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlb)
                        .addGap(131, 131, 131)
                        .addComponent(btnKreirajIznajmljivanje))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbVideoIgra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbOsoba, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(257, Short.MAX_VALUE)
                    .addComponent(txtUkupanIznos1)
                    .addGap(181, 181, 181)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtZaposleni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbOsoba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbVideoIgra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKreirajIznajmljivanje)
                    .addComponent(jlb))
                .addGap(15, 15, 15))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(418, Short.MAX_VALUE)
                    .addComponent(txtUkupanIznos1)
                    .addGap(16, 16, 16)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDodajStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajStavkuActionPerformed
        try {
            validirajStavku();

            VideoIgra vi = (VideoIgra) cmbVideoIgra.getSelectedItem();
            int brojDana = Integer.parseInt(txtBrojDana.getText().trim());
            double cena = vi.getCenaIznajmljivanja() * brojDana;

            StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
            stavka.setVideoIgra(vi);
            stavka.setBrojDana(brojDana);
            stavka.setCena(cena);

            ModelTabeleStavkeIznajmljivanja model = (ModelTabeleStavkeIznajmljivanja) tblStavke.getModel();
            model.dodajStavku(stavka);

            osveziUkupanIznos();

            txtBrojDana.setText("");
            txtCena.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnDodajStavkuActionPerformed

    private void btnObrisiStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiStavkuActionPerformed
        try {
            int selectedRow = tblStavke.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Morate izabrati stavku.");
                return;
            }

            ModelTabeleStavkeIznajmljivanja model = (ModelTabeleStavkeIznajmljivanja) tblStavke.getModel();
            model.obrisiStavku(selectedRow);

            osveziUkupanIznos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greška pri brisanju stavke: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnObrisiStavkuActionPerformed

    private void btnKreirajIznajmljivanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKreirajIznajmljivanjeActionPerformed
         try {
            Iznajmljivanje i = kreirajObjekatIznajmljivanje();

            Response response = Communication.getInstance().kreirajIznajmljivanje(i);

            if (response.getStatus() == ResponseStatus.ERROR) {
                throw response.getException();
            }

            JOptionPane.showMessageDialog(this, "Sistem je zapamtio iznajmljivanje.");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti iznajmljivanje.\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnKreirajIznajmljivanjeActionPerformed

    private void txtBrojDanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrojDanaKeyReleased
       izracunajCenuStavke();

    }//GEN-LAST:event_txtBrojDanaKeyReleased

    private void cmbVideoIgraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVideoIgraActionPerformed
        izracunajCenuStavke();
    }//GEN-LAST:event_cmbVideoIgraActionPerformed

    private Iznajmljivanje kreirajObjekatIznajmljivanje() throws Exception {
        validirajFormu();

        Iznajmljivanje i = new Iznajmljivanje();
       i.setDatumIznajmljivanja(Date.valueOf(txtDatum.getText().trim()));
        i.setOsoba((Osoba) cmbOsoba.getSelectedItem());
        i.setZaposleni(ulogovani);
        i.setUkupanIznos(Double.parseDouble(jlb.getText().trim()));

        ModelTabeleStavkeIznajmljivanja model = (ModelTabeleStavkeIznajmljivanja) tblStavke.getModel();
        i.setStavke(model.getLista());

        return i;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodajStavku;
    private javax.swing.JButton btnKreirajIznajmljivanje;
    private javax.swing.JButton btnObrisiStavku;
    private javax.swing.JComboBox cmbOsoba;
    private javax.swing.JComboBox cmbVideoIgra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlb;
    private javax.swing.JTable tblStavke;
    private javax.swing.JTextField txtBrojDana;
    private javax.swing.JLabel txtCena;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JLabel txtUkupanIznos1;
    private javax.swing.JLabel txtZaposleni;
    // End of variables declaration//GEN-END:variables
}
