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
import java.util.List;
import javax.swing.JOptionPane;
import transfer.Response;
import transfer.ResponseStatus;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class IzmeniIznajmljivanje extends javax.swing.JFrame {

    /**
     * Creates new form IzmeniIznajmljivanje
     */
    Iznajmljivanje iznajmljivanje;
    Zaposleni ulogovani;
    private double ukupanIznos = 0.0;

    public IzmeniIznajmljivanje(Zaposleni admin, Iznajmljivanje i) {
        initComponents();
        setLocationRelativeTo(this);
        this.iznajmljivanje = i;
        this.ulogovani = admin;
        pripremiFormu();
        popuniFormu();
    }

    private void pripremiFormu() {
        tblStavke.setModel(new ModelTabeleStavkeIznajmljivanja());
        tea.setEnabled(false);
        txtCena.setEnabled(false);

        ucitajOsobe();
        ucitajVideoIgre();

        if (ulogovani != null) {
            txtZaposleni.setText(ulogovani.toString());
        }
    }

    private void popuniFormu() {
        txtDatum.setText(String.valueOf(iznajmljivanje.getDatumIznajmljivanja()));
        cmbOsoba.setSelectedItem(iznajmljivanje.getOsoba());
        txtZaposleni.setText(ulogovani.toString());

        ModelTabeleStavkeIznajmljivanja model
                = (ModelTabeleStavkeIznajmljivanja) tblStavke.getModel();
        model.setLista(iznajmljivanje.getStavke());

        osveziUkupanIznos();
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
        ukupanIznos = 0;

        for (StavkaIznajmljivanja s : model.getLista()) {
            ukupanIznos += s.getCena();
        }

        tea.setText("Ukupan iznos: " + ukupanIznos);
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

        ModelTabeleStavkeIznajmljivanja model
                = (ModelTabeleStavkeIznajmljivanja) tblStavke.getModel();

        if (model.getLista() == null || model.getLista().isEmpty()) {
            throw new Exception("Morate uneti bar jednu stavku.");
        }
    }

    private Iznajmljivanje kreirajIzmenjenObjekat() throws Exception {
        Iznajmljivanje novo = new Iznajmljivanje();

        novo.setIdIznajmljivanje(iznajmljivanje.getIdIznajmljivanje());
        novo.setDatumIznajmljivanja(java.sql.Date.valueOf(txtDatum.getText().trim()));
        novo.setOsoba((Osoba) cmbOsoba.getSelectedItem());
        novo.setZaposleni(ulogovani);
        String teaTekst = tea.getText().trim();
        String iznosSamo = teaTekst.replace("Ukupan iznos: ", "").trim();
        novo.setUkupanIznos(Double.parseDouble(iznosSamo));

        ModelTabeleStavkeIznajmljivanja model
                = (ModelTabeleStavkeIznajmljivanja) tblStavke.getModel();

        novo.setStavke(new ArrayList<>(model.getLista()));

        return novo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtZaposleni = new javax.swing.JLabel();
        cmbVideoIgra = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tea = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCena = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStavke = new javax.swing.JTable();
        btnObrisiStavku = new javax.swing.JButton();
        btnDodajStavku = new javax.swing.JButton();
        txtBrojDana = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDatum = new javax.swing.JTextField();
        btnIzmeniIznajmljivanje = new javax.swing.JButton();
        cmbOsoba = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Forma Izmeni Iznajmljivanje");

        txtZaposleni.setText("jLabel1");

        cmbVideoIgra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVideoIgraActionPerformed(evt);
            }
        });

        jLabel1.setText("Osobe: ");

        jLabel2.setText("Video igra:");

        tea.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tea.setText("Ukupna cena:");

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
        tblStavke.setColumnSelectionAllowed(true);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
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

        jLabel5.setText("Datum iznajmljivanja: (yyyy-mm-dd):");

        btnIzmeniIznajmljivanje.setText("Izmeni iznajmljivanje");
        btnIzmeniIznajmljivanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniIznajmljivanjeActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tea)
                        .addGap(120, 120, 120)
                        .addComponent(btnIzmeniIznajmljivanje)))
                .addContainerGap())
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tea)
                    .addComponent(btnIzmeniIznajmljivanje))
                .addContainerGap())
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

    private void btnIzmeniIznajmljivanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniIznajmljivanjeActionPerformed
        try {
            Iznajmljivanje i = kreirajIzmenjenObjekat();

            Response response = Communication.getInstance().promeniIznajmljivanje(i);

            System.out.println("BROJ STAVKI ZA SLANJE: " + i.getStavke().size());

            if (response.getStatus() == ResponseStatus.ERROR) {
                throw response.getException();
            }

            JOptionPane.showMessageDialog(this, "Sistem je zapamtio iznajmljivanje.");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti iznajmljivanje.\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnIzmeniIznajmljivanjeActionPerformed

    private void cmbVideoIgraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVideoIgraActionPerformed
        izracunajCenuStavke();
    }//GEN-LAST:event_cmbVideoIgraActionPerformed

    private void txtBrojDanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrojDanaKeyReleased
        izracunajCenuStavke();
    }//GEN-LAST:event_txtBrojDanaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodajStavku;
    private javax.swing.JButton btnIzmeniIznajmljivanje;
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
    private javax.swing.JTable tblStavke;
    private javax.swing.JLabel tea;
    private javax.swing.JTextField txtBrojDana;
    private javax.swing.JLabel txtCena;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JLabel txtZaposleni;
    // End of variables declaration//GEN-END:variables
}
