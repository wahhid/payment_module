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
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.report.entity.BillingMemberRpt;
import org.jakc.payment.report.gui.MonthlyBillingMemberReportFrame;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author wahhid
 */
public class MonthlyBillingMemberReportController {
    
    private ProcessCallBack pcb;
    private BackingController bc;    
    private Connection conn;
    private MonthlyBillingMemberReportFrame frame;    
    private String filePath;   
    private InputStream streamReport;
    
    public MonthlyBillingMemberReportController(Connection conn){
        this.conn = conn;
        this.bc = new BackingController(this.conn);        
        this.frame = new MonthlyBillingMemberReportFrame(this);        
        MDIFrame.add(this.frame);          
    }
    
    public ProcessCallBack PrepareBillingMember(){
        List<BillingMemberRpt> orpts = new ArrayList();
        this.pcb = this.bc.getTransaksiStikerController().activeBillingList();
        if(this.pcb.isError()){
            return this.pcb;
        }        
        List<TransaksiStiker> os = (List<TransaksiStiker>) this.pcb.getObject();
        for(TransaksiStiker o : os){
            BillingMemberRpt orpt = new BillingMemberRpt();
            orpt.setNotrans(o.getNotrans());
            orpt.setNama(o.getNama());
            orpt.setAwal(o.getAwal());
            orpt.setAkhir(o.getAkhir());            
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
