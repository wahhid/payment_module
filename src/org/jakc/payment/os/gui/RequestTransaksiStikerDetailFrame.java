/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.gui;

import java.awt.Cursor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.os.controller.ReportViewerController;
import org.jakc.payment.os.controller.RequestTransaksiStikerDetailController;
import org.jakc.payment.report.entity.RequestTransaksiStikerRpt;
import org.openswing.swing.client.ComboBoxControl;
import org.openswing.swing.client.CurrencyControl;
import org.openswing.swing.client.DateChangedListener;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.client.TextControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.internationalization.java.Resources;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author wahhid
 */
public class RequestTransaksiStikerDetailFrame extends InternalFrame {

    private ProcessCallBack pcb;
    private RequestTransaksiStikerDetailController controller;
    /**
     * Creates new form RequestTransaksiStikerDetailFrame
     */
    public RequestTransaksiStikerDetailFrame(RequestTransaksiStikerDetailController controller) {
        this.controller = controller;  
        initComponents();             
        
        this.editButton1.setText("Edit");
        this.saveButton1.setText("Save");
        this.reloadButton1.setText("Reload");

        this.dcawal.setFormat(Resources.DMY);
        this.dcawal1.setFormat(Resources.DMY);
        this.dcakhir.setFormat(Resources.DMY);
        this.dcakhir1.setFormat(Resources.DMY);
        
        this.cbunitkerja.setDomain(controller.getBc().getStasiunKerjaController().getCB());
        this.cbjenistransaksi.setDomain(controller.getBc().getJenisTransaksiController().getCB());
        this.cbjenismobil.setDomain(controller.getBc().getJenisMobilController().getCB());               
        this.dcawal.addDateChangedListener(new RequestTransaksiStikerDetailFrame.dcAwalDateChangedListener());         
        this.form1.setFormController(this.controller);                                  
    }
    
    private class dcAwalDateChangedListener implements DateChangedListener {       
        @Override
        public void dateChanged(Date oldDate, Date newDate) {                  
            controller.calculate();                        
        }

    }    
        
    public Form getForm1() {
        return form1;
    }

    public void setForm1(Form form1) {
        this.form1 = form1;
    }

    public ComboBoxControl getCbjenismobil() {
        return cbjenismobil;
    }

    public void setCbjenismobil(ComboBoxControl cbjenismobil) {
        this.cbjenismobil = cbjenismobil;
    }

    public ComboBoxControl getCbjenistransaksi() {
        return cbjenistransaksi;
    }

    public void setCbjenistransaksi(ComboBoxControl cbjenistransaksi) {
        this.cbjenistransaksi = cbjenistransaksi;
    }

    public ComboBoxControl getCbunitkerja() {
        return cbunitkerja;
    }

    public void setCbunitkerja(ComboBoxControl cbunitkerja) {
        this.cbunitkerja = cbunitkerja;
    }

    public TextControl getTxtnoid() {
        return txtnoid;
    }

    public void setTxtnoid(TextControl txtnoid) {
        this.txtnoid = txtnoid;
    }

    public DateControl getDcakhir() {
        return dcakhir;
    }

    public void setDcakhir(DateControl dcakhir) {
        this.dcakhir = dcakhir;
    }

    public DateControl getDcawal() {
        return dcawal;
    }

    public void setDcawal(DateControl dcawal) {
        this.dcawal = dcawal;
    }

    public TextControl getTxtjenismember() {
        return txtjenismember;
    }

    public void setTxtjenismember(TextControl txtjenismember) {
        this.txtjenismember = txtjenismember;
    }

    public TextControl getTxtnotrans() {
        return txtnotrans;
    }

    public void setTxtnotrans(TextControl txtnotrans) {
        this.txtnotrans = txtnotrans;
    }

    public CurrencyControl getCcbayar() {
        return ccbayar;
    }

    public void setCcbayar(CurrencyControl ccbayar) {
        this.ccbayar = ccbayar;
    }

    public TextControl getTxtmerk() {
        return txtmerk;
    }

    public void setTxtmerk(TextControl txtmerk) {
        this.txtmerk = txtmerk;
    }

    public TextControl getTxtnopol() {
        return txtnopol;
    }

    public void setTxtnopol(TextControl txtnopol) {
        this.txtnopol = txtnopol;
    }

    public TextControl getTxttahun() {
        return txttahun;
    }

    public void setTxttahun(TextControl txttahun) {
        this.txttahun = txttahun;
    }

    public TextControl getTxttype() {
        return txttype;
    }

    public void setTxttype(TextControl txttype) {
        this.txttype = txttype;
    }

    public TextControl getTxtwarna() {
        return txtwarna;
    }

    public void setTxtwarna(TextControl txtwarna) {
        this.txtwarna = txtwarna;
    }

    public DateControl getDcakhir1() {
        return dcakhir1;
    }

    public void setDcakhir1(DateControl dcakhir1) {
        this.dcakhir1 = dcakhir1;
    }

    public DateControl getDcawal1() {
        return dcawal1;
    }

    public void setDcawal1(DateControl dcawal1) {
        this.dcawal1 = dcawal1;
    }

    public JRadioButton getRbcurrentdate() {
        return rbcurrentdate;
    }

    public void setRbcurrentdate(JRadioButton rbcurrentdate) {
        this.rbcurrentdate = rbcurrentdate;
    }

    public JRadioButton getRbcustom() {
        return rbcustom;
    }

    public void setRbcustom(JRadioButton rbcustom) {
        this.rbcustom = rbcustom;
    }

    public JRadioButton getRblastmembership() {
        return rblastmembership;
    }

    public void setRblastmembership(JRadioButton rblastmembership) {
        this.rblastmembership = rblastmembership;
    }

    public JSpinner getSpduration() {
        return spduration;
    }

    public void setSpduration(JSpinner spduration) {
        this.spduration = spduration;
    }

    public JRadioButton getRbbilling() {
        return rbbilling;
    }

    public void setRbbilling(JRadioButton rbbilling) {
        this.rbbilling = rbbilling;
    }

    public JRadioButton getRbnonbilling() {
        return rbnonbilling;
    }

    public void setRbnonbilling(JRadioButton rbnonbilling) {
        this.rbnonbilling = rbnonbilling;
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        form1 = new org.openswing.swing.form.client.Form();
        jLabel1 = new javax.swing.JLabel();
        cbunitkerja = new org.openswing.swing.client.ComboBoxControl();
        jLabel2 = new javax.swing.JLabel();
        ccbayar = new org.openswing.swing.client.CurrencyControl();
        jLabel3 = new javax.swing.JLabel();
        cbjenistransaksi = new org.openswing.swing.client.ComboBoxControl();
        jLabel4 = new javax.swing.JLabel();
        txtnopol = new org.openswing.swing.client.TextControl();
        jLabel5 = new javax.swing.JLabel();
        cbjenismobil = new org.openswing.swing.client.ComboBoxControl();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtmerk = new org.openswing.swing.client.TextControl();
        jLabel8 = new javax.swing.JLabel();
        txttype = new org.openswing.swing.client.TextControl();
        jLabel9 = new javax.swing.JLabel();
        txtwarna = new org.openswing.swing.client.TextControl();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dcawal = new org.openswing.swing.client.DateControl();
        jLabel12 = new javax.swing.JLabel();
        dcakhir = new org.openswing.swing.client.DateControl();
        jLabel14 = new javax.swing.JLabel();
        txtnotrans = new org.openswing.swing.client.TextControl();
        jLabel13 = new javax.swing.JLabel();
        txtnoid = new org.openswing.swing.client.TextControl();
        txttahun = new org.openswing.swing.client.TextControl();
        txtjenismember = new org.openswing.swing.client.TextControl();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        dcawal1 = new org.openswing.swing.client.DateControl();
        jLabel17 = new javax.swing.JLabel();
        dcakhir1 = new org.openswing.swing.client.DateControl();
        jLabel18 = new javax.swing.JLabel();
        rbcurrentdate = new javax.swing.JRadioButton();
        rblastmembership = new javax.swing.JRadioButton();
        rbcustom = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        spduration = new javax.swing.JSpinner();
        rbnonbilling = new javax.swing.JRadioButton();
        rbbilling = new javax.swing.JRadioButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        btnprint = new javax.swing.JButton();

        setTitle("Subscription");
        setUniqueInstance(true);

        saveButton1.setButtonBehavior(Consts.BUTTON_IMAGE_AND_TEXT);
        saveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton1ActionPerformed(evt);
            }
        });

        form1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        form1.setVOClassName("org.jakc.payment.os.vo.RequestTransaksiStikerVo");
        form1.setAutoscrolls(true);
        form1.setEditButton(editButton1);
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);

        jLabel1.setText("Unit Kerja");

        cbunitkerja.setAttributeName("unit_kerja");
        cbunitkerja.setEnabled(false);
        cbunitkerja.setEnabledOnEdit(false);
        cbunitkerja.setEnabledOnInsert(false);
        cbunitkerja.setRequired(true);

        jLabel2.setText("Bayar");

        ccbayar.setAttributeName("harga");
        ccbayar.setCurrencySymbol("Rp ");
        ccbayar.setEnabled(false);
        ccbayar.setEnabledOnEdit(false);
        ccbayar.setEnabledOnInsert(false);
        ccbayar.setRequired(true);
        ccbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccbayarActionPerformed(evt);
            }
        });

        jLabel3.setText("Jenis Transaksi");

        cbjenistransaksi.setAttributeName("cara_bayar");
        cbjenistransaksi.setEnabled(false);
        cbjenistransaksi.setEnabledOnEdit(false);
        cbjenistransaksi.setEnabledOnInsert(false);
        cbjenistransaksi.setRequired(true);

        jLabel4.setText("No Polisi");

        txtnopol.setAttributeName("nopol");
        txtnopol.setRequired(true);

        jLabel5.setText("Jenis Kendaraan");

        cbjenismobil.setAttributeName("jenis_mobil");
        cbjenismobil.setEnabled(false);
        cbjenismobil.setEnabledOnEdit(false);
        cbjenismobil.setEnabledOnInsert(false);
        cbjenismobil.setRequired(true);

        jLabel6.setText("Golongan Tarif");

        jLabel7.setText("Merk");

        txtmerk.setAttributeName("merk");
        txtmerk.setRequired(true);

        jLabel8.setText("Type");

        txttype.setAttributeName("tipe");
        txttype.setRequired(true);

        jLabel9.setText("Warna");

        txtwarna.setAttributeName("warna");
        txtwarna.setRequired(true);

        jLabel10.setText("Tahun");

        jLabel11.setText("Periode");

        dcawal.setAttributeName("awal");
        dcawal.setEnabled(false);
        dcawal.setEnabledOnEdit(false);
        dcawal.setEnabledOnInsert(false);
        dcawal.setRequired(true);

        jLabel12.setText("TO");

        dcakhir.setAttributeName("akhir");
        dcakhir.setEnabled(false);
        dcakhir.setEnabledOnEdit(false);
        dcakhir.setEnabledOnInsert(false);
        dcakhir.setRequired(true);
        dcakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dcakhirActionPerformed(evt);
            }
        });

        jLabel14.setText("No Trans");

        txtnotrans.setAttributeName("notrans");
        txtnotrans.setEnabled(false);
        txtnotrans.setEnabledOnEdit(false);
        txtnotrans.setEnabledOnInsert(false);

        jLabel13.setText("No ID");

        txtnoid.setAttributeName("no_id");
        txtnoid.setEnabled(false);
        txtnoid.setEnabledOnEdit(false);
        txtnoid.setEnabledOnInsert(false);
        txtnoid.setRequired(true);

        txttahun.setAttributeName("tahun");
        txttahun.setRequired(true);

        txtjenismember.setAttributeName("jenis_member");
        txtjenismember.setEnabled(false);
        txtjenismember.setEnabledOnEdit(false);
        txtjenismember.setEnabledOnInsert(false);
        txtjenismember.setRequired(true);

        jLabel15.setText("Cara Bayar");

        jLabel16.setText("Last Periode");

        dcawal1.setAttributeName("");
        dcawal1.setEnabled(false);
        dcawal1.setEnabledOnEdit(false);
        dcawal1.setEnabledOnInsert(false);
        dcawal1.setShowCalendarButton(false);

        jLabel17.setText("TO");

        dcakhir1.setAttributeName("");
        dcakhir1.setEnabled(false);
        dcakhir1.setEnabledOnEdit(false);
        dcakhir1.setEnabledOnInsert(false);
        dcakhir1.setShowCalendarButton(false);

        jLabel18.setText("Start Date");

        buttonGroup1.add(rbcurrentdate);
        rbcurrentdate.setText("Current Date");
        rbcurrentdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcurrentdateActionPerformed(evt);
            }
        });

        buttonGroup1.add(rblastmembership);
        rblastmembership.setText("Last Membership");
        rblastmembership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rblastmembershipActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbcustom);
        rbcustom.setText("Custom");
        rbcustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcustomActionPerformed(evt);
            }
        });

        jLabel19.setText("Duration");

        spduration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spdurationStateChanged(evt);
            }
        });

        buttonGroup2.add(rbnonbilling);
        rbnonbilling.setText("Non Billing");
        rbnonbilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnonbillingActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbbilling);
        rbbilling.setText("Billing");
        rbbilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbbillingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnoid, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbunitkerja, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(form1Layout.createSequentialGroup()
                                .addComponent(rbcurrentdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rblastmembership, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbcustom))
                            .addGroup(form1Layout.createSequentialGroup()
                                .addComponent(dcawal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(dcakhir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spduration, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dcawal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcakhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbnonbilling)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbbilling))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ccbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbjenistransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnopol, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbjenismobil, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmerk, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttype, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtwarna, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtjenismember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbunitkerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnoid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcawal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcakhir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbcurrentdate)
                            .addComponent(rblastmembership)
                            .addComponent(rbcustom))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spduration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcawal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dcakhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbjenistransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbnonbilling)
                    .addComponent(rbbilling))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ccbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnopol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbjenismobil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtjenismember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtwarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(form1);

        reloadButton1.setButtonBehavior(Consts.BUTTON_IMAGE_AND_TEXT);
        reloadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadButton1ActionPerformed(evt);
            }
        });

        editButton1.setButtonBehavior(Consts.BUTTON_IMAGE_AND_TEXT);

        btnprint.setText("Print");
        btnprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ccbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ccbayarActionPerformed

    private void dcakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dcakhirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dcakhirActionPerformed

    private void rbcurrentdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcurrentdateActionPerformed
        // TODO add your handling code here:        
        this.dcawal.setEnabled(false);
        this.dcawal.setDate(new Date(System.currentTimeMillis()));
        this.controller.calculate();
    }//GEN-LAST:event_rbcurrentdateActionPerformed

    private void rblastmembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rblastmembershipActionPerformed
        // TODO add your handling code here:
        this.dcawal.setEnabled(false);
        this.dcawal.setValue(this.dcakhir1.getValue());
        this.controller.calculate();
    }//GEN-LAST:event_rblastmembershipActionPerformed

    private void rbcustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcustomActionPerformed
        // TODO add your handling code here:
        this.dcawal.setEnabled(true);
    }//GEN-LAST:event_rbcustomActionPerformed

    private void spdurationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spdurationStateChanged
        // TODO add your handling code here:
        if((Integer)this.spduration.getValue() <= 0){
            this.spduration.setValue(1);
        }
        this.controller.calculate();
    }//GEN-LAST:event_spdurationStateChanged

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButton1ActionPerformed

    private void reloadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reloadButton1ActionPerformed

    private void rbnonbillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnonbillingActionPerformed
        // TODO add your handling code here:
        this.controller.setCara_bayar(0);
        this.controller.calculate();
    }//GEN-LAST:event_rbnonbillingActionPerformed

    private void rbbillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbbillingActionPerformed
        // TODO add your handling code here:
        this.controller.setCara_bayar(1);
        this.controller.calculate();        
    }//GEN-LAST:event_rbbillingActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        List<RequestTransaksiStikerRpt> orpts = new ArrayList();
        ClassLoader cLoader = this.getClass().getClassLoader();
        InputStream url = cLoader.getResourceAsStream("org/jakc/payment/report/resource/PaymentTransactionReceipt.jasper");
        System.out.println("Resoure Path : " + url);

        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        this.setCursor(hourglassCursor);    
        this.pcb = this.controller.PreparePaymentReceipt();
        if(this.pcb.isError()){
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), this.pcb.getErrmsg(), "Error" , JOptionPane.ERROR_MESSAGE);
            return;
        }
        RequestTransaksiStikerRpt orpt = (RequestTransaksiStikerRpt) this.pcb.getObject();        
        HashMap parameters = new HashMap();
        parameters.put("PAGE_TITLE", "Payment Receipt");
        parameters.put("COMPANY_NAME", "Kondominium Taman Anggrek");
        parameters.put("PRINTED_BY", this.controller.getAuth().getPegawai().getNama());   
        parameters.put("START_DATE",orpt.getTanggal());        
        orpts.add(orpt);
        JRDataSource dataSource = new JRBeanCollectionDataSource(orpts);                            
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        this.setCursor(normalCursor);        
        new ReportViewerController(url,parameters,dataSource,"");         
    }//GEN-LAST:event_btnprintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnprint;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private org.openswing.swing.client.ComboBoxControl cbjenismobil;
    private org.openswing.swing.client.ComboBoxControl cbjenistransaksi;
    private org.openswing.swing.client.ComboBoxControl cbunitkerja;
    private org.openswing.swing.client.CurrencyControl ccbayar;
    private org.openswing.swing.client.DateControl dcakhir;
    private org.openswing.swing.client.DateControl dcakhir1;
    private org.openswing.swing.client.DateControl dcawal;
    private org.openswing.swing.client.DateControl dcawal1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbbilling;
    private javax.swing.JRadioButton rbcurrentdate;
    private javax.swing.JRadioButton rbcustom;
    private javax.swing.JRadioButton rblastmembership;
    private javax.swing.JRadioButton rbnonbilling;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private javax.swing.JSpinner spduration;
    private org.openswing.swing.client.TextControl txtjenismember;
    private org.openswing.swing.client.TextControl txtmerk;
    private org.openswing.swing.client.TextControl txtnoid;
    private org.openswing.swing.client.TextControl txtnopol;
    private org.openswing.swing.client.TextControl txtnotrans;
    private org.openswing.swing.client.TextControl txttahun;
    private org.openswing.swing.client.TextControl txttype;
    private org.openswing.swing.client.TextControl txtwarna;
    // End of variables declaration//GEN-END:variables
}
