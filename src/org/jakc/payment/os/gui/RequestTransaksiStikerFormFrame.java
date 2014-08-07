/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JButton;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.BillingParking;
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.grid.TransaksiStikerGrid;
import org.jakc.payment.os.controller.RequestTransaksiStikerDetailController;
import org.jakc.payment.os.controller.RequestTransaksiStikerFormController;
import org.jakc.payment.os.vo.RequestTransaksiStikerVo;
import org.jakc.payment.os.vo.TransaksiStikerVo;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.InternalFrame;

/**
 *
 * @author wahhid
 */

public class RequestTransaksiStikerFormFrame extends InternalFrame {

    private BackingController bc;
    private RequestTransaksiStikerFormController controller;
    private TransaksiStikerGrid grid;
    private HashMap data;
    
    /**
     * Creates new form RequestTransaksiStikerFormFrame
     */
    public RequestTransaksiStikerFormFrame(RequestTransaksiStikerFormController controller) {
        initComponents();
        this.controller = controller;        
        this.grid = new TransaksiStikerGrid(this,this.controller.getConn());
        this.gridControl1.setController(grid);
        this.gridControl1.setGridDataLocator(grid); 
        
        this.cbunitkerja.setDomain(this.controller.getBc().getStasiunKerjaController().getCB());
        this.cbunitkerja.addActionListener(new CbUnitKerjaChangeListener());             
        
        this.cbcayabayar.setDomain(this.controller.getBc().getCaraBayarController().getCB());
        
    }

    private class CbUnitKerjaChangeListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(cbunitkerja.getValue());            
            grid.setPk((String)cbunitkerja.getValue());
            gridControl1.reloadData();            
            gridControl1.addLoadDataCompletedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshUrutan();
                }
            });            
        }                
    }
     
    public void refreshUrutan(){        
        this.btnfirst.setEnabled(true);
        this.btnsecond.setEnabled(true);
        this.btnthird.setEnabled(true);
        this.btnforth.setEnabled(true);
        this.btnfifth.setEnabled(true);
        
        for(int i=0;i<this.gridControl1.getVOListTableModel().getRowCount();i++){
            TransaksiStikerVo o = (TransaksiStikerVo) this.gridControl1.getVOListTableModel().getObjectForRow(i);
            if(o.getNotrans().endsWith("1")){               
                this.btnfirst.setEnabled(false);
            }
            if(o.getNotrans().endsWith("2")){
                this.btnsecond.setEnabled(false);
            }
            if(o.getNotrans().endsWith("3")){
                this.btnthird.setEnabled(false);
            }
            if(o.getNotrans().endsWith("4")){
                this.btnforth.setEnabled(false);
            }
            if(o.getNotrans().endsWith("5")){
                this.btnfifth.setEnabled(false);
            }            
        }
        
    }
    
    public GridControl getGridControl1() {
        return gridControl1;
    }

    public void setGridControl1(GridControl gridControl1) {
        this.gridControl1 = gridControl1;
    }    
    

    public JButton getBtnfifth() {
        return btnfifth;
    }

    public void setBtnfifth(JButton btnfifth) {
        this.btnfifth = btnfifth;
    }

    public JButton getBtnfirst() {
        return btnfirst;
    }

    public void setBtnfirst(JButton btnfirst) {
        this.btnfirst = btnfirst;
    }

    public JButton getBtnforth() {
        return btnforth;
    }

    public void setBtnforth(JButton btnforth) {
        this.btnforth = btnforth;
    }

    public JButton getBtnsecond() {
        return btnsecond;
    }

    public void setBtnsecond(JButton btnsecond) {
        this.btnsecond = btnsecond;
    }

    public JButton getBtnthird() {
        return btnthird;
    }

    public void setBtnthird(JButton btnthird) {
        this.btnthird = btnthird;
    }

    public JButton getBtnextend() {
        return btnextend;
    }

    public void setBtnextend(JButton btnprocess) {
        this.btnextend = btnprocess;
    }

    public JButton getBtnstop() {
        return btnstop;
    }

    public void setBtnstop(JButton btnstop) {
        this.btnstop = btnstop;
    }
    
           
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbunitkerja = new org.openswing.swing.client.ComboBoxControl();
        gridControl1 = new org.openswing.swing.client.GridControl();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn2 = new org.openswing.swing.table.columns.client.DateColumn();
        cbcayabayar = new org.openswing.swing.table.columns.client.ComboColumn();
        jPanel1 = new javax.swing.JPanel();
        btnfirst = new javax.swing.JButton();
        btnsecond = new javax.swing.JButton();
        btnthird = new javax.swing.JButton();
        btnforth = new javax.swing.JButton();
        btnfifth = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnextend = new javax.swing.JButton();
        btnstop = new javax.swing.JButton();

        setTitle("Permintaan Langganan");
        setUniqueInstance(true);

        jLabel1.setText("Unit Kerja");

        cbunitkerja.setAttributeName("");
        cbunitkerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbunitkerjaActionPerformed(evt);
            }
        });

        gridControl1.setValueObjectClassName("org.jakc.payment.os.vo.TransaksiStikerVo");
        gridControl1.getColumnContainer().setLayout(new java.awt.GridLayout(1, 0));

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("notrans");
        textColumn1.setColumnSortable(true);
        gridControl1.getColumnContainer().add(textColumn1);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("nama");
        textColumn2.setColumnSortable(true);
        gridControl1.getColumnContainer().add(textColumn2);

        dateColumn1.setColumnFilterable(true);
        dateColumn1.setColumnName("awal");
        dateColumn1.setColumnSortable(true);
        gridControl1.getColumnContainer().add(dateColumn1);

        dateColumn2.setColumnFilterable(true);
        dateColumn2.setColumnName("akhir");
        dateColumn2.setColumnSortable(true);
        gridControl1.getColumnContainer().add(dateColumn2);

        cbcayabayar.setColumnFilterable(true);
        cbcayabayar.setColumnName("cara_bayar");
        cbcayabayar.setColumnSortable(true);
        gridControl1.getColumnContainer().add(cbcayabayar);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnfirst.setText("1st Membership");
        btnfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstActionPerformed(evt);
            }
        });

        btnsecond.setText("2nd Membership");
        btnsecond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsecondActionPerformed(evt);
            }
        });

        btnthird.setText("3rd Membership");
        btnthird.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthirdActionPerformed(evt);
            }
        });

        btnforth.setText("4th Membership");
        btnforth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnforthActionPerformed(evt);
            }
        });

        btnfifth.setText("5th Membership");
        btnfifth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfifthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnfirst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnsecond, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnthird, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnforth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnfifth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnfirst)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnsecond)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnthird)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnforth)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnfifth)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnextend.setText("Extend");
        btnextend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnextendActionPerformed(evt);
            }
        });

        btnstop.setText("Stop");
        btnstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnextend, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(btnstop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnextend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnstop)
                .addGap(0, 235, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(534, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(161, 161, 161))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbunitkerja, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbunitkerja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void cbunitkerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbunitkerjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbunitkerjaActionPerformed

    private void btnextendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnextendActionPerformed
        // TODO add your handling code here:
        try{
            this.data = new HashMap();          
            this.data.put("last_awal",this.grid.getVo().getAwal());
            this.data.put("last_akhir",this.grid.getVo().getAkhir());  
            this.data.put("jenis_transaksi",1);
            this.data.put("jenis_mobil","M");        

            String jenis_member = this.grid.getDetailTransaksiStiker().getNotrans().substring(4, 5);
            if(jenis_member.equals("1")){
               this.data.put("jenis_member", "1st");
            }
            if(jenis_member.equals("2")){
               this.data.put("jenis_member", "2nd");
            }
            if(jenis_member.equals("3")){
               this.data.put("jenis_member", "3rd");
            }
            if(jenis_member.equals("4")){
               this.data.put("jenis_member", "4th");
            }
            if(jenis_member.equals("5")){
               this.data.put("jenis_member", "5th");
            }             

            this.data.put("unit_kerja",this.cbunitkerja.getValue());
            this.data.put("awal",new Date(System.currentTimeMillis()));                
            this.data.put("nopol",this.grid.getDetailTransaksiStiker().getNopol());
            this.data.put("no_id",this.grid.getDetailTransaksiStiker().getNotrans());
            this.data.put("merk",this.grid.getDetailTransaksiStiker().getMerk());
            this.data.put("tipe",this.grid.getDetailTransaksiStiker().getTipe());
            this.data.put("tahun",this.grid.getDetailTransaksiStiker().getTahun());
            this.data.put("warna",this.grid.getDetailTransaksiStiker().getWarna());
            this.data.put("start_date_status",1);               
            new RequestTransaksiStikerDetailController(null,this.data,this.controller.getConn(),this.controller.getAuth());                          
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btnextendActionPerformed

    private void btnfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstActionPerformed
        this.data = new HashMap();
        this.data.put("jenis_transaksi",0);
        this.data.put("jenis_mobil","M");
        this.data.put("jenis_member", "1st");
        this.data.put("unit_kerja",this.cbunitkerja.getValue());
        this.data.put("no_id",((String)this.cbunitkerja.getValue()).substring(1, 5) + "1");
        this.data.put("awal",new Date(System.currentTimeMillis()));
        this.data.put("start_date_status",0);
        new RequestTransaksiStikerDetailController(null,this.data,this.controller.getConn(),this.controller.getAuth());      
    }//GEN-LAST:event_btnfirstActionPerformed

    private void btnsecondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsecondActionPerformed
        this.data = new HashMap();        
        this.data.put("jenis_transaksi",0);
        this.data.put("jenis_mobil","M");
        this.data.put("jenis_member", "2nd");
        this.data.put("unit_kerja",this.cbunitkerja.getValue());
        this.data.put("no_id",((String)this.cbunitkerja.getValue()).substring(1, 5) + "2");
        this.data.put("awal",new Date(System.currentTimeMillis()));
        this.data.put("start_date_status",0);
        new RequestTransaksiStikerDetailController(null,this.data,this.controller.getConn(),this.controller.getAuth());                     
    }//GEN-LAST:event_btnsecondActionPerformed

    private void btnthirdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthirdActionPerformed
        // TODO add your handling code here:
        this.data = new HashMap();
        this.data.put("jenis_transaksi",0);
        this.data.put("jenis_mobil","M");
        this.data.put("jenis_member", "3rd");
        this.data.put("unit_kerja",this.cbunitkerja.getValue());
        this.data.put("no_id",((String)this.cbunitkerja.getValue()).substring(1, 5) + "3");
        this.data.put("awal",new Date(System.currentTimeMillis()));
        this.data.put("start_date_status",0);
        new RequestTransaksiStikerDetailController(null,this.data,this.controller.getConn(),this.controller.getAuth());              
    }//GEN-LAST:event_btnthirdActionPerformed

    private void btnforthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnforthActionPerformed
        this.data = new HashMap();        
        this.data.put("jenis_transaksi",0);
        this.data.put("jenis_mobil","M");
        this.data.put("jenis_member", "4th");
        this.data.put("unit_kerja",this.cbunitkerja.getValue());
        this.data.put("no_id",((String)this.cbunitkerja.getValue()).substring(1, 5) + "4");
        this.data.put("awal",new Date(System.currentTimeMillis()));
        this.data.put("start_date_status",0);
        new RequestTransaksiStikerDetailController(null,this.data,this.controller.getConn(),this.controller.getAuth());            
    }//GEN-LAST:event_btnforthActionPerformed

    private void btnfifthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfifthActionPerformed
        this.data = new HashMap();        
        this.data.put("jenis_transaksi",0);
        this.data.put("jenis_mobil","M");
        this.data.put("jenis_member", "5th");
        this.data.put("unit_kerja",this.cbunitkerja.getValue());
        this.data.put("no_id",((String)this.cbunitkerja.getValue()).substring(1, 5) + "5");
        this.data.put("awal",new Date(System.currentTimeMillis()));
        this.data.put("start_date_status",0);
        new RequestTransaksiStikerDetailController(null,this.data,this.controller.getConn(),this.controller.getAuth());              
    }//GEN-LAST:event_btnfifthActionPerformed

    private void btnstopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstopActionPerformed
        // TODO add your handling code here:    
        BillingParking bp;
        this.data = new HashMap();          
        
        this.data.put("last_awal",this.grid.getVo().getAwal());
        this.data.put("last_akhir",this.grid.getVo().getAkhir());  
        
        this.data.put("jenis_transaksi",2);       
        this.data.put("jenis_mobil","M");        
        
        this.controller.stopMember(this.grid.getVo(), data);
        
        this.data.put("unit_kerja",this.cbunitkerja.getValue());        
        this.data.put("nopol",this.grid.getDetailTransaksiStiker().getNopol());
        this.data.put("no_id",this.grid.getDetailTransaksiStiker().getNotrans());
        this.data.put("merk",this.grid.getDetailTransaksiStiker().getMerk());
        this.data.put("tipe",this.grid.getDetailTransaksiStiker().getTipe());
        this.data.put("tahun",this.grid.getDetailTransaksiStiker().getTahun());
        this.data.put("warna",this.grid.getDetailTransaksiStiker().getWarna());
        this.data.put("start_date_status",1);          
        
        this.controller.stopMember(this.grid.getVo(), data);
        
        new RequestTransaksiStikerDetailController(null,this.data,this.controller.getConn(),this.controller.getAuth());          
        //this.grid.stopMember();   
    }//GEN-LAST:event_btnstopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnextend;
    private javax.swing.JButton btnfifth;
    private javax.swing.JButton btnfirst;
    private javax.swing.JButton btnforth;
    private javax.swing.JButton btnsecond;
    private javax.swing.JButton btnstop;
    private javax.swing.JButton btnthird;
    private org.openswing.swing.table.columns.client.ComboColumn cbcayabayar;
    private org.openswing.swing.client.ComboBoxControl cbunitkerja;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn2;
    private org.openswing.swing.client.GridControl gridControl1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    // End of variables declaration//GEN-END:variables
}
