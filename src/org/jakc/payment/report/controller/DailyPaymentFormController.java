/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.report.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.Auth;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.RequestTransaksiStiker;
import org.jakc.payment.report.entity.RequestTransaksiStikerRpt;
import org.jakc.payment.report.gui.DailyPaymentFormFrame;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author wahhid
 */
public class DailyPaymentFormController {
    
    private ProcessCallBack pcb;
    private BackingController bc;    
    private Connection conn;
    private Auth auth;
    private DailyPaymentFormFrame frame;    
    private String filePath;   
    private InputStream streamReport;
    
    public DailyPaymentFormController(Connection conn, Auth auth){
        this.conn = conn;
        this.auth = auth;
        this.bc = new BackingController(this.conn);        
        this.frame = new DailyPaymentFormFrame(this);        
        MDIFrame.add(this.frame);                
    }                       
   
    public ProcessCallBack PrepareDailyReport(Date tanggal, int approvedstatus){
        List<RequestTransaksiStikerRpt> orpts = new ArrayList();        
        
        this.pcb = this.bc.getRequestTransaksiStikerController().ForDailyReport(tanggal,approvedstatus);        
        if(this.pcb.isError()){
            return this.pcb;
        }
        
        List<RequestTransaksiStiker> os = (List<RequestTransaksiStiker>) this.pcb.getObject();        
        for(RequestTransaksiStiker o : os){
            RequestTransaksiStikerRpt orpt = new RequestTransaksiStikerRpt();
            orpt.setNotrans(o.getNotrans());
            orpt.setNama(o.getNama());
            orpt.setNo_id(o.getNo_id());
            orpt.setNopol(o.getNopol());
            orpt.setUnit_kerja(o.getUnit_kerja());
            orpt.setAwal(o.getAwal());
            orpt.setAkhir(o.getAkhir());
            orpt.setTanggal(o.getTanggal());
            orpt.setAdm(o.getAdm());
            orpt.setJenis_transaksi(o.getJenis_transaksi());
            if(orpt.getJenis_transaksi() == 0){
                orpt.setJenis_transaksi_name("Langganan Baru");
            }
            if(orpt.getJenis_transaksi() == 1){
                orpt.setJenis_transaksi_name("Perpanjangan Langganan");
            }
            if(orpt.getJenis_transaksi() == 2){
                orpt.setJenis_transaksi_name("Stop Langganan");
            }           
            orpt.setJenis_member(o.getJenis_member());
            orpt.setCara_bayar(o.getCara_bayar());
            if(o.getCara_bayar() == 0){
                orpt.setCara_bayar_name("Non Billing");
            }
            if(o.getCara_bayar() == 1){
                orpt.setCara_bayar_name("Billing");
            }
            orpt.setHarga(o.getHarga());
            orpt.setStatus(o.getStatus());
            orpt.setMerk(o.getMerk());
            orpt.setTipe(o.getTipe());
            orpt.setTahun(o.getTahun());
            orpt.setWarna(o.getWarna());
            orpt.setTgl_approved(o.getTgl_approved());
            orpt.setAdm_approved(o.getAdm_approved());
            orpt.setFlag(o.getFlag());
            orpt.setRemark(o.getRemark());
            orpt.setStart_date_status(o.getStart_date_status());
            orpt.setDuration(o.getDuration());                        
            orpts.add(orpt);
        }                            
        return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "List Found", orpts);
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
 
    
}
