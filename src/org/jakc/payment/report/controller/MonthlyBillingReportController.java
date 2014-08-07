/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.report.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.BillingParking;
import org.jakc.payment.report.entity.BillingParkingRpt;
import org.jakc.payment.report.gui.MonthlyBillingReportFrame;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author wahhid
 */
public class MonthlyBillingReportController {
   
    private ProcessCallBack pcb;
    private BackingController bc;    
    private Connection conn;
    private MonthlyBillingReportFrame frame;    
    private String filePath;   
    private InputStream streamReport;
    
    public MonthlyBillingReportController(Connection conn){
        this.conn = conn;
        this.bc = new BackingController(this.conn);        
        this.frame = new MonthlyBillingReportFrame(this);        
        MDIFrame.add(this.frame);                
        
    }

    public ProcessCallBack PrepareBillingParking(int billing_id){
        List<BillingParkingRpt> orpts = new ArrayList();
        this.pcb = this.bc.getBillingParkingController().findByBillingId(billing_id);
        if(this.pcb.isError()){
            return this.pcb;
        }
        List<BillingParking> os = (List<BillingParking>) this.pcb.getObject();
        for(BillingParking o : os){
            BillingParkingRpt orpt = new BillingParkingRpt();
            orpt.setUnit_no(o.getUnitno());
            orpt.setDate_trans(o.getDate_trans());
            orpt.setDescription(o.getDescription());
            orpt.setAmount(o.getAmount());
            orpt.setBilling_id(o.getBilling_id());
            orpts.add(orpt);
        }
        return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "List Found", orpts);
    }
    
    public BackingController getBc() {
        return bc;
    }

    public void setBc(BackingController bc) {
        this.bc = bc;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
    
}
