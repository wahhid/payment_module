/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.gui;

import java.awt.Cursor;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.os.controller.ReportViewerController;
import org.jakc.payment.os.controller.RequestTransaksiStikerDetailController;
import org.jakc.payment.os.controller.RequestTransaksiStikerFormController;
import org.jakc.payment.os.controller.RequestTransaksiStikerGridController;
import org.jakc.payment.report.entity.RequestTransaksiStikerRpt;
import org.openswing.swing.domains.java.Domain;
import org.openswing.swing.internationalization.java.Resources;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author wahhid
 */
public class RequestTransaksiStikerGridFrame extends InternalFrame {

    private ProcessCallBack pcb;
    private RequestTransaksiStikerGridController controller;
    private Connection conn;
    private HashMap data;
    /**
     * Creates new form RequestTransaksiStikerGridFrame
     */
    public RequestTransaksiStikerGridFrame(RequestTransaksiStikerGridController controller,Connection conn) {
        this.conn = conn;        
        this.controller = controller;
        initComponents();        
        this.insertButton1.setText("Insert");
        this.editButton1.setText("Edit");        
        this.reloadButton1.setText("Reload");
        this.filterButton1.setText("Find");
        this.deleteButton1.setText("Delete");        
        this.dateColumn1.setFormat(Resources.DMY);
        this.gridControl1.setController(this.controller);
        this.gridControl1.setGridDataLocator(this.controller);
        this.cbjenismember.setDomain(this.controller.getBc().getTarifStikerController().getCB());
        this.cbcarabayar.setDomain(this.controller.getBc().getCaraBayarController().getCB());
        this.cbjenistransaksi.setDomain(this.controller.getBc().getJenisTransaksiController().getCB());
        this.comboColumn1.setDomain(this.CBApprovedStatus());
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        filterButton1 = new org.openswing.swing.client.FilterButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        gridControl1 = new org.openswing.swing.client.GridControl();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        cbjenistransaksi = new org.openswing.swing.table.columns.client.ComboColumn();
        cbjenismember = new org.openswing.swing.table.columns.client.ComboColumn();
        cbcarabayar = new org.openswing.swing.table.columns.client.ComboColumn();
        currencyColumn1 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        btnprint = new javax.swing.JButton();
        btnapproved = new javax.swing.JButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();

        setTitle("Permintaan Langganan");
        setUniqueInstance(true);

        filterButton1.setButtonBehavior(Consts.BUTTON_IMAGE_AND_TEXT);

        reloadButton1.setButtonBehavior(Consts.BUTTON_IMAGE_AND_TEXT);

        editButton1.setButtonBehavior(Consts.BUTTON_IMAGE_AND_TEXT);
        editButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton1ActionPerformed(evt);
            }
        });

        insertButton1.setButtonBehavior(Consts.BUTTON_IMAGE_AND_TEXT);
        insertButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButton1ActionPerformed(evt);
            }
        });

        gridControl1.setFilterButton(filterButton1);
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setReloadButton(reloadButton1);
        gridControl1.setValueObjectClassName("org.jakc.payment.os.vo.RequestTransaksiStikerVo");
        gridControl1.getColumnContainer().setLayout(new java.awt.GridLayout(1, 0));

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("notrans");
        textColumn1.setColumnSortable(true);
        gridControl1.getColumnContainer().add(textColumn1);

        dateColumn1.setColumnFilterable(true);
        dateColumn1.setColumnName("tanggal");
        dateColumn1.setColumnSortable(true);
        dateColumn1.setSortVersus(org.openswing.swing.util.java.Consts.DESC_SORTED);
        gridControl1.getColumnContainer().add(dateColumn1);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("nama");
        textColumn2.setColumnSortable(true);
        gridControl1.getColumnContainer().add(textColumn2);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("no_id");
        textColumn3.setColumnSortable(true);
        gridControl1.getColumnContainer().add(textColumn3);

        cbjenistransaksi.setColumnName("jenis_transaksi");
        gridControl1.getColumnContainer().add(cbjenistransaksi);

        cbjenismember.setColumnFilterable(true);
        cbjenismember.setColumnName("jenis_member");
        cbjenismember.setColumnSortable(true);
        gridControl1.getColumnContainer().add(cbjenismember);

        cbcarabayar.setColumnFilterable(true);
        cbcarabayar.setColumnName("cara_bayar");
        cbcarabayar.setColumnSortable(true);
        gridControl1.getColumnContainer().add(cbcarabayar);

        currencyColumn1.setColumnName("harga");
        currencyColumn1.setCurrencySymbol("Rp");
        gridControl1.getColumnContainer().add(currencyColumn1);

        comboColumn1.setColumnFilterable(true);
        comboColumn1.setColumnName("approvedstatus");
        comboColumn1.setColumnSortable(true);
        gridControl1.getColumnContainer().add(comboColumn1);

        btnprint.setText("Print");
        btnprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintActionPerformed(evt);
            }
        });

        btnapproved.setText("Approved");
        btnapproved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnapprovedActionPerformed(evt);
            }
        });

        deleteButton1.setButtonBehavior(Consts.BUTTON_IMAGE_AND_TEXT);
        deleteButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(insertButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnprint, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnapproved)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(filterButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(editButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(insertButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(deleteButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnprint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnapproved, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private Domain CBApprovedStatus(){
        Domain d = new Domain("APPROVEDSTATUS");
        d.addDomainPair(0, "Not Approved");
        d.addDomainPair(1, "Approved");
        return d;
    }
    
    private void insertButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButton1ActionPerformed
        // TODO add your handling code here:
        new RequestTransaksiStikerFormController(this.conn,this.controller.getAuth());
    }//GEN-LAST:event_insertButton1ActionPerformed

    private void editButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton1ActionPerformed
        // TODO add your handling code here:                       
        this.data = new HashMap();          
        this.data.put("last_awal",null);
        this.data.put("last_akhir",null);               
        new RequestTransaksiStikerDetailController(this.controller.getVo().getNotrans(), this.data, this.conn,this.controller.getAuth());                   
    }//GEN-LAST:event_editButton1ActionPerformed

    private void deleteButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton1ActionPerformed
        // TODO add your handling code here:
        if(this.controller.getVo().getTgl_approved() != null){
            JOptionPane.showMessageDialog(null, "Delete not allowed, Transaction already approved","Warning",  JOptionPane.WARNING_MESSAGE);
        }else{
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                this.controller.getBc().getRequestTransaksiStikerController().delete(this.controller.getVo().getNotrans());            
                JOptionPane.showMessageDialog(null, "Data deleted successfully", "Information", JOptionPane.INFORMATION_MESSAGE);               
            }
            if(response == JOptionPane.NO_OPTION){                
                JOptionPane.showMessageDialog(null,"Process was cancelled", "Warning",  JOptionPane.WARNING_MESSAGE);               
            }                       
        }
    }//GEN-LAST:event_deleteButton1ActionPerformed

    private void btnapprovedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnapprovedActionPerformed
        // TODO add your handling code here:
        if(this.controller.getVo().getTgl_approved() != null){
            JOptionPane.showMessageDialog(null, "Transaction already approved","Warning",  JOptionPane.WARNING_MESSAGE);        
        }else{
            this.pcb = this.controller.approved();
            if(this.pcb.isError()){
                JOptionPane.showMessageDialog(null, this.pcb.getErrmsg(), "Warning", JOptionPane.WARNING_MESSAGE);                               
            }else{
                JOptionPane.showMessageDialog(null, "Approved Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);               
                this.gridControl1.reloadData();
                System.out.println("Approved Finished");                
            }
            
        }

    }//GEN-LAST:event_btnapprovedActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        // TODO add your handling code here:
        List<RequestTransaksiStikerRpt> orpts = new ArrayList();
        ClassLoader cLoader = this.getClass().getClassLoader();
        InputStream url = cLoader.getResourceAsStream("org/jakc/payment/report/resource/PaymentTransactionReceipt.jasper");
        System.out.println("Resoure Path : " + url);
        HashMap parameters = new HashMap();
        parameters.put("PAGE_TITLE", "Payment Receipt");
        parameters.put("COMPANY_NAME", "Kondominium Taman Anggrek");
        parameters.put("PRINTED_BY", this.controller.getAuth().getPegawai().getNama());   
        parameters.put("START_DATE",this.controller.getVo().getTanggal());
        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        this.setCursor(hourglassCursor);    
        this.pcb = this.controller.PreparePaymentReceipt();
        if(this.pcb.isError()){
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), this.pcb.getErrmsg(), "Error" , JOptionPane.ERROR_MESSAGE);
        }
        RequestTransaksiStikerRpt orpt = (RequestTransaksiStikerRpt) this.pcb.getObject();
        orpts.add(orpt);
        JRDataSource dataSource = new JRBeanCollectionDataSource(orpts);                            
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        this.setCursor(normalCursor);        
        new ReportViewerController(url,parameters,dataSource,"");     
    }//GEN-LAST:event_btnprintActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnapproved;
    private javax.swing.JButton btnprint;
    private org.openswing.swing.table.columns.client.ComboColumn cbcarabayar;
    private org.openswing.swing.table.columns.client.ComboColumn cbjenismember;
    private org.openswing.swing.table.columns.client.ComboColumn cbjenistransaksi;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    // End of variables declaration//GEN-END:variables
}