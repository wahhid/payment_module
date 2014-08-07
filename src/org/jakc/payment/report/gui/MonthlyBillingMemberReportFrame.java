/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.report.gui;

import java.awt.Cursor;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.os.controller.ReportViewerController;
import org.jakc.payment.report.controller.MonthlyBillingMemberReportController;
import org.jakc.payment.report.entity.BillingMemberRpt;
import org.jakc.payment.report.entity.BillingParkingRpt;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author wahhid
 */
public class MonthlyBillingMemberReportFrame extends InternalFrame {

    private ProcessCallBack pcb;
    private MonthlyBillingMemberReportController controller;    
    /**
     * Creates new form MonthlyBillingMemberReportFrame
     */
    public MonthlyBillingMemberReportFrame(MonthlyBillingMemberReportController controller) {
        this.controller = controller;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbbilling = new org.openswing.swing.client.ComboBoxControl();
        jLabel2 = new javax.swing.JLabel();
        cbbilling1 = new org.openswing.swing.client.ComboBoxControl();
        btncancel = new javax.swing.JButton();
        btngenerate = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Month");

        jLabel2.setText("Year");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(cbbilling, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(cbbilling1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbbilling, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbbilling1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        btncancel.setText("Cancel");

        btngenerate.setText("Generate");
        btngenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerateActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(0, 226, Short.MAX_VALUE)
                        .add(btncancel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btngenerate)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btngenerate)
                    .add(btncancel))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btngenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerateActionPerformed
        // TODO add your handling code here:
        List<BillingMemberRpt> orpts;
        ClassLoader cLoader = this.getClass().getClassLoader();
        InputStream url = cLoader.getResourceAsStream("org/jakc/payment/report/resource/BillingMember.jasper");
        System.out.println("Resoure Path : " + url);
        HashMap parameters = new HashMap();
        parameters.put("PAGE_TITLE", "Billing Member Report");
        parameters.put("COMPANY_NAME", "Kondominium Taman Anggrek");        
        parameters.put("PRINTED_BY", "admin");
        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        this.setCursor(hourglassCursor);
        this.pcb = this.controller.PrepareBillingMember();
        if(this.pcb.isError()){
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), this.pcb.getErrmsg(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        orpts = (List<BillingMemberRpt>) this.pcb.getObject();
        JRDataSource dataSource = new JRBeanCollectionDataSource(orpts);
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        this.setCursor(normalCursor);
        new ReportViewerController(url,parameters,dataSource,"Monthly Billing Member Report");
    }//GEN-LAST:event_btngenerateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btngenerate;
    private org.openswing.swing.client.ComboBoxControl cbbilling;
    private org.openswing.swing.client.ComboBoxControl cbbilling1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}