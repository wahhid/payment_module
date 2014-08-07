/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.controller;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.Auth;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.DetailTransaksiStiker;
import org.jakc.payment.db.entity.RequestTransaksiStiker;
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.os.gui.ApprovalFormFrame;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author wahhid
 */
public class ApprovalFormController {
    private ProcessCallBack pcb;
    private Connection conn;
    private Auth auth;
    private BackingController bc; 
    private ApprovalFormFrame frame;
    private RequestTransaksiStiker o;
    
    public ApprovalFormController(Connection conn,Auth auth){
        this.conn = conn;
        this.auth = auth;
        this.bc = new BackingController(this.conn);
        this.frame = new ApprovalFormFrame(this);        
        MDIFrame.add(this.frame);
    }

    public ProcessCallBack approved(){                       
        Calendar cal;
        TransaksiStiker ts;
        DetailTransaksiStiker dts;
        this.pcb  = this.bc.getRequestTransaksiStikerController().getByNoTrans(this.frame.getTxttransid().getText());
        if(this.pcb.isError()){            
            return this.pcb;
        }else{
           o = (RequestTransaksiStiker) this.pcb.getObject();
        }
        //New Membership        
        if(o.getJenis_transaksi() == 0){                        
            ts = new TransaksiStiker();
            dts = new DetailTransaksiStiker();
            //Complet Transaksi Stiker Information
            ts.setNotrans(o.getNo_id());
            ts.setNama(o.getNama());
            ts.setAlamat(null);
            ts.setTelepon("TIDAK BAYAR");
            ts.setJenis_transaksi(o.getJenis_transaksi());
            ts.setAwal(o.getAwal());
            ts.setHarga(0);
            ts.setKeterangan("");
            ts.setTanggal(o.getTanggal());
            ts.setOperator("00001");
            String strDateAkhir = new SimpleDateFormat("yyyy-MM-dd").format(o.getAkhir()) + " 23:59:00";
            try {
                ts.setAkhir(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDateAkhir));
            } catch (ParseException ex) {
                return new ProcessCallBack(true, "Date Parsing error on new membership", null);                
            }
            ts.setMaks(1);
            ts.setNo_id(o.getNo_id());
            ts.setUnit_kerja(o.getUnit_kerja());
            ts.setNo_induk("");
            ts.setJenis_stiker(0);
            ts.setHari_ke("");
            ts.setJenis_langganan("");
            ts.setExit_pass(0);
            ts.setNo_kuitansi(o.getNo_id());
            ts.setTgl_edited(new Date(System.currentTimeMillis()));
            ts.setTipe_exit_pass(1);
            ts.setSeq_code("0");
            ts.setUnitno("");
            ts.setArea("");
            ts.setReserved(0);
            ts.setCara_bayar(o.getCara_bayar());
            this.pcb = this.bc.getTransaksiStikerController().insert(ts); 
            if(this.pcb.isError()){
                return this.pcb;
            }                        
            dts.setNotrans(o.getNo_id());
            dts.setNopol(o.getNopol());
            dts.setJenis_mobil(o.getJenis_mobil());
            dts.setAdm(o.getAdm());
            dts.setKategori(0);
            dts.setJenis_member("Tidak Ada Golongan Tarif");
            dts.setAkses("");
            dts.setAkses_out("");
            dts.setStatus(1);
            dts.setMerk(o.getMerk());
            dts.setTipe(o.getTipe());
            dts.setTahun(o.getTahun());
            dts.setWarna(o.getWarna());
            dts.setKeterangan("");
            this.bc.getDetailTransaksiStikerController().insert(dts);
            if(this.bc.getDetailTransaksiStikerController().getErrStatus() == 1){
                this.bc.getDetailTransaksiStikerController().setErrStatus(0);
            }else{
                this.bc.getRequestTransaksiStikerController().approved(this.frame.getTxttransid().getText(),this.auth.getPegawai().getUsername());
            }
                                  
        }
        
        //Extend Membership
        if(o.getJenis_transaksi() == 1){
            this.pcb = this.bc.getTransaksiStikerController().getByNotrans(o.getNo_id());
            if(this.pcb.isError()){
                return this.pcb;
            }
            ts = (TransaksiStiker) this.pcb.getObject();
            //ts.setNotrans(o.getNo_id());
            //ts.setNama(o.getNama());
            //ts.setAlamat(null);
            //ts.setTelepon("TIDAK BAYAR");
            ts.setJenis_transaksi(o.getJenis_transaksi());
            //ts.setAwal(o.getAwal());
            //ts.setHarga(0);
            //ts.setKeterangan("");
            //ts.setTanggal(o.getTanggal());
            ts.setOperator("00001");
            String strDateAkhir = new SimpleDateFormat("yyyy-MM-dd").format(o.getAkhir()) + " 23:59:00";
            try {
                ts.setAkhir(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDateAkhir));
            } catch (ParseException ex) {
                return new ProcessCallBack(true, "Date Parsing error on new membership", null);                
            }
            //ts.setMaks(1);
            ts.setNo_id(o.getNo_id());
            ts.setUnit_kerja(o.getUnit_kerja());
            //ts.setNo_induk("");
            //ts.setJenis_stiker(0);
            //ts.setHari_ke("");
            //ts.setJenis_langganan("");
            //ts.setExit_pass(0);
            ts.setNo_kuitansi(o.getNo_id());
            ts.setTgl_edited(new Date(System.currentTimeMillis()));
            //ts.setTipe_exit_pass(1);
            //ts.setSeq_code("0");
            //ts.setUnitno("");
            //ts.setArea("");
            //ts.setReserved(0);
            ts.setCara_bayar(o.getCara_bayar());                
            this.pcb = this.bc.getTransaksiStikerController().update(ts);
            if(this.pcb.isError()){
                return this.pcb;
            }            
            dts = this.bc.getDetailTransaksiStikerController().getByNotrans(o.getNo_id());                    
            //dts.setNotrans(o.getNo_id());
            dts.setNopol(o.getNopol());
            dts.setJenis_mobil(o.getJenis_mobil());
            dts.setAdm(o.getAdm());
            dts.setKategori(0);
            dts.setJenis_member("Tidak Ada Golongan Tarif");
            //dts.setAkses("");
            //dts.setAkses_out("");
            //dts.setStatus(1);
            dts.setMerk(o.getMerk());
            dts.setTipe(o.getTipe());
            dts.setTahun(o.getTahun());
            dts.setWarna(o.getWarna());
            //dts.setKeterangan("");
            this.bc.getDetailTransaksiStikerController().update(dts);       
            if(this.bc.getDetailTransaksiStikerController().getErrStatus() == 1){
                this.bc.getDetailTransaksiStikerController().setErrStatus(0);
            }else{
                this.bc.getRequestTransaksiStikerController().approved(this.frame.getTxttransid().getText(),this.auth.getPegawai().getUsername());
            }                    
        }
        
        //Stop Membership
        if(o.getJenis_transaksi() == 2){
            this.pcb = this.bc.getTransaksiStikerController().getByNotrans(o.getNo_id());
            if(this.pcb.isError()){
                return this.pcb;
            }
            ts = (TransaksiStiker) this.pcb.getObject();
            
            ts.setAkhir(o.getAkhir());
            ts.setCara_bayar(0);               
            this.pcb = this.bc.getTransaksiStikerController().update(ts);
            if(this.pcb.isError()){
                return this.pcb;
            }
            this.bc.getRequestTransaksiStikerController().approved(this.frame.getTxttransid().getText(),this.auth.getPegawai().getUsername());                                                                                   
        }
        
        //Change Car Plat Number
        if(o.getJenis_transaksi() == 3){
            
        }
        return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " +  "Approved Successfully", null);
    }    
            
    public Connection getConn() {
        return conn;
    }

    public Auth getAuth() {
        return auth;
    }

    public RequestTransaksiStiker getO() {
        return o;
    }
    
    
    
}
