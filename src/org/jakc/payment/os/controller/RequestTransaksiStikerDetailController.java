/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.controller;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.Auth;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.RequestTransaksiStiker;
import org.jakc.payment.db.entity.StasiunKerja;
import org.jakc.payment.db.entity.TarifStiker;
import org.jakc.payment.os.gui.RequestTransaksiStikerDetailFrame;
import org.jakc.payment.os.vo.RequestTransaksiStikerVo;import org.jakc.payment.report.entity.RequestTransaksiStikerRpt;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author wahhid
 */
public class RequestTransaksiStikerDetailController extends FormController {
    private ProcessCallBack pcb;
   private Connection conn;
   private Auth auth;
   private BackingController bc;
   private RequestTransaksiStikerVo ivo;
   private String pk;
   private HashMap data;
   private RequestTransaksiStikerDetailFrame frame;
   private Boolean update=false;      
   private int urutan=0;   
   private int cara_bayar;
   private int jenis_transaksi;
   
   public RequestTransaksiStikerDetailController(String pk, HashMap data,Connection conn, Auth auth){            
        this.conn = conn; 
        this.auth = auth;
        this.bc = new BackingController(this.conn);        
        this.data = data;
        this.pk = pk;
        
        
        this.frame = new RequestTransaksiStikerDetailFrame(this);         
        MDIFrame.add(this.frame);               
        
        if(pk != null){
            System.out.println("READONLY");    
            this.frame.getForm1().setMode(Consts.READONLY);
            this.frame.getForm1().reload();                        
        }else{
            System.out.println("INSERT"); 
            this.frame.getForm1().setMode(Consts.INSERT); 
            if((Integer)this.data.get("jenis_transaksi") == 0){
                //New Member
                this.NewSubcription(data);                                               
            }
            
            if((Integer)this.data.get("jenis_transaksi") == 1){
                //Extends Subscription
                this.ExtendsSubscription(data);
                
            }            
            
            if((Integer)this.data.get("jenis_transaksi") == 2){
                //Stop Subscription
                this.StopSubcription();
                this.frame.getDcawal1().setDate((Date) this.data.get("last_awal"));
                this.frame.getDcakhir1().setDate((Date) this.data.get("last_akhir"));                 
                this.frame.getRbcurrentdate().setEnabled(false);
                this.frame.getRblastmembership().setEnabled(false);
                this.frame.getRbcustom().setEnabled(false);
                this.frame.getSpduration().setEnabled(false);
                this.frame.getRbnonbilling().setEnabled(false);
                this.frame.getRbbilling().setEnabled(false);
                this.frame.getTxtnopol().setEnabled(false);
                this.frame.getTxtmerk().setEnabled(false);
                this.frame.getTxttype().setEnabled(false);
                this.frame.getTxttahun().setEnabled(false);
                this.frame.getTxtwarna().setEnabled(false);
            }
            
            if((Integer)this.data.get("jenis_transaksi") == 3){
                //Change Plat Number
                
            }                            
        }                                                        
    }
                           
    private void NewSubcription(HashMap data){                
        this.frame.getCbunitkerja().setValue(this.data.get("unit_kerja"));
        this.frame.getTxtnoid().setValue(this.data.get("no_id"));
        if((Integer) this.data.get("start_date_status") == 0){
            this.frame.getRbcurrentdate().setSelected(true);
        }
        if((Integer) this.data.get("start_date_status") == 1){
            this.frame.getRblastmembership().setSelected(true);
        }
        if((Integer) this.data.get("start_date_status") == 2){
            this.frame.getRbcustom().setSelected(true);
        }                           
        this.frame.getRblastmembership().setEnabled(false);
        this.frame.getSpduration().setValue(1);
        this.frame.getDcawal().setDate(new Date(System.currentTimeMillis())); 
        this.frame.getCbjenistransaksi().setValue(this.data.get("jenis_transaksi"));   
        this.cara_bayar = 0;
        if(this.cara_bayar == 0){
            this.frame.getRbnonbilling().setSelected(true);
        }else{
            this.frame.getRbbilling().setSelected(true);
        }                     
        this.frame.getTxtnopol().setValue(this.data.get("nopol"));
        this.frame.getCbjenismobil().setValue(this.data.get("jenis_mobil"));
        this.frame.getTxtjenismember().setValue(this.data.get("jenis_member"));
        this.frame.getTxtmerk().setValue(this.data.get("merk"));
        this.frame.getTxttype().setValue(this.data.get("tipe"));        
        this.frame.getTxtwarna().setValue(this.data.get("warna"));          
        this.frame.getTxttahun().setValue(this.data.get("tahun"));        
    }
    
    private void ExtendsSubscription(HashMap data){
        this.frame.getCbunitkerja().setValue(this.data.get("unit_kerja"));
        this.frame.getTxtnoid().setValue(this.data.get("no_id"));
        this.frame.getDcawal1().setDate((Date) this.data.get("last_awal"));
        this.frame.getDcakhir1().setDate((Date) this.data.get("last_akhir"));         
        if((Integer) this.data.get("start_date_status") == 0){
            this.frame.getRbcurrentdate().setSelected(true);
        }
        if((Integer) this.data.get("start_date_status") == 1){
            this.frame.getRblastmembership().setSelected(true);
        }
        if((Integer) this.data.get("start_date_status") == 2){
            this.frame.getRbcustom().setSelected(true);
        }                           
        
        this.frame.getSpduration().setValue(1);
        this.frame.getDcawal().setDate((Date) this.data.get("last_akhir")); 
        this.frame.getCbjenistransaksi().setValue(this.data.get("jenis_transaksi"));   
        this.cara_bayar = 0;
        if(this.cara_bayar == 0){
            this.frame.getRbnonbilling().setSelected(true);
        }else{
            this.frame.getRbbilling().setSelected(true);
        }                     
//        this.frame.getRbnonbilling().setEnabled(false);
//        this.frame.getRbbilling().setEnabled(false);
        this.frame.getTxtnopol().setValue(this.data.get("nopol"));
        this.frame.getTxtnopol().setEnabled(false);
        this.frame.getCbjenismobil().setValue(this.data.get("jenis_mobil"));  
        this.frame.getTxtjenismember().setValue(this.data.get("jenis_member"));
        this.frame.getTxtmerk().setValue(this.data.get("merk"));
        this.frame.getTxttype().setValue(this.data.get("tipe"));        
        this.frame.getTxtwarna().setValue(this.data.get("warna"));          
        this.frame.getTxttahun().setValue(this.data.get("tahun"));           
    }
    
    private void StopSubcription(){
        this.frame.getRblastmembership().setEnabled(false);
        this.frame.getCbunitkerja().setValue(this.data.get("unit_kerja"));
        this.frame.getTxtnoid().setValue(this.data.get("no_id"));
        
        this.frame.getDcawal1().setDate((Date) this.data.get("last_awal"));
        this.frame.getDcakhir1().setDate((Date) this.data.get("last_akhir"));     
        
        if((Integer) this.data.get("start_date_status") == 0){
            this.frame.getRbcurrentdate().setSelected(true);
        }
        if((Integer) this.data.get("start_date_status") == 1){
            this.frame.getRblastmembership().setSelected(true);
        }
        if((Integer) this.data.get("start_date_status") == 2){
            this.frame.getRbcustom().setSelected(true);
        }                           
        this.frame.getSpduration().setValue(0);
        
        this.frame.getDcawal().setDate((Date) this.data.get("awal")); 
        this.frame.getDcakhir().setDate((Date) this.data.get("akhir")); 
        
        this.frame.getCbjenistransaksi().setValue(this.data.get("jenis_transaksi"));   
        this.cara_bayar = 0;
        if(this.cara_bayar == 0){
            this.frame.getRbnonbilling().setSelected(true);
        }else{
            this.frame.getRbbilling().setSelected(true);
        }                     
        
        this.frame.getTxtnopol().setValue(this.data.get("nopol"));
        this.frame.getTxtnopol().setEnabled(false);
        this.frame.getCbjenismobil().setValue(this.data.get("jenis_mobil"));  
        this.frame.getTxtjenismember().setValue(this.data.get("jenis_member"));
        this.frame.getTxtmerk().setValue(this.data.get("merk"));
        this.frame.getTxttype().setValue(this.data.get("tipe"));        
        this.frame.getTxtwarna().setValue(this.data.get("warna"));          
        this.frame.getTxttahun().setValue(this.data.get("tahun"));          
    }
        
    @Override
    public void afterReloadData() {
        
        this.frame.getSpduration().setValue(ivo.getDuration());
        
        if(ivo.getStart_date_status() == 0){
            this.frame.getRbcurrentdate().setSelected(true);
        }
        if(ivo.getStart_date_status() == 1){
            this.frame.getRblastmembership().setSelected(true);
        }
        if(ivo.getStart_date_status() == 2){
            this.frame.getRbcustom().setSelected(true);
        }              

        if(ivo.getJenis_transaksi() == 1){
           this.frame.getTxtnopol().setEnabled(false);               
        }else{
           this.frame.getRblastmembership().setEnabled(false);
        }                                    
        
        if(ivo.getCara_bayar() == 0){
            this.frame.getRbnonbilling().setSelected(true);
        }else{
            this.frame.getRbbilling().setSelected(true);
        }
        this.calculate();
        
    }
    
    @Override
    public Response loadData(Class valueObjectClass) {    
        RequestTransaksiStikerVo vo = new RequestTransaksiStikerVo();
        System.out.println("PK: " +  pk);
        try{                                      
            this.pcb = this.bc.getRequestTransaksiStikerController().getByNoTrans(pk);
            if(this.pcb.isError()){
                return new ErrorResponse(this.pcb.getErrmsg());
            }
            RequestTransaksiStiker o = (RequestTransaksiStiker) this.pcb.getObject();
            vo.setNotrans(o.getNotrans());
            vo.setNama(o.getNama());
            vo.setNo_id(o.getNo_id());
            vo.setUnit_kerja(o.getUnit_kerja());
            vo.setAwal(o.getAwal());
            vo.setAkhir(o.getAkhir());
            vo.setHarga(o.getHarga());
            vo.setTanggal(o.getTanggal());
            vo.setAdm(o.getAdm());
            vo.setJenis_transaksi(o.getJenis_transaksi());
            vo.setCara_bayar(o.getCara_bayar());
            vo.setNopol(o.getNopol());
            vo.setJenis_mobil(o.getJenis_mobil());
            vo.setJenis_member(o.getJenis_member());
            vo.setStatus(o.getStatus());
            vo.setMerk(o.getMerk());
            vo.setTipe(o.getTipe());
            vo.setTahun(o.getTahun());
            vo.setWarna(o.getWarna());
            vo.setTgl_approved(o.getTgl_approved());
            vo.setAdm_approved(o.getAdm_approved());
            vo.setFlag(o.getFlag());
            vo.setRemark(o.getRemark());
            vo.setStart_date_status(o.getStart_date_status());
            vo.setDuration(o.getDuration());                
            ivo = vo;
            return new VOResponse(vo);                        
        }catch(Exception ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());           
        }
        
    }          
    
    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        RequestTransaksiStikerVo vo = (RequestTransaksiStikerVo) newPersistentObject;
        System.out.println(vo.getCara_bayar());
        RequestTransaksiStiker o = new RequestTransaksiStiker();        
        try{
            o.setNotrans(new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date(System.currentTimeMillis())));            
            StasiunKerja stasiunkerja = this.bc.getStasiunKerjaController().getByKode(vo.getUnit_kerja());
            if(this.bc.getStasiunKerjaController().getErrStatus() == 1){
                this.bc.getStasiunKerjaController().setErrStatus(0);
                return new ErrorResponse(this.bc.getStasiunKerjaController().getErrMsg());
            }
            o.setNama(stasiunkerja.getNama());
            o.setNo_id(vo.getNo_id());
            o.setUnit_kerja(vo.getUnit_kerja());
            o.setAwal(vo.getAwal());
            o.setAkhir(vo.getAkhir());
            o.setHarga(vo.getHarga());
            o.setTanggal(vo.getTanggal());
            o.setAdm("00001");
            o.setJenis_transaksi((Integer)this.data.get("jenis_transaksi"));
            o.setJenis_member(vo.getJenis_member());            
            o.setCara_bayar(this.cara_bayar);            
            o.setNopol(vo.getNopol());
            o.setJenis_mobil(vo.getJenis_mobil());
            o.setStatus(1);
            o.setMerk(vo.getMerk());
            o.setTipe(vo.getTipe());
            o.setTahun(vo.getTahun());
            o.setWarna(vo.getWarna());
            o.setTgl_approved(null);
            o.setAdm_approved(null);    
            o.setFlag(vo.getFlag());
            o.setRemark(vo.getRemark());            
            
            if(this.frame.getRbcurrentdate().isSelected()){
                o.setStart_date_status(0);
            }
            if(this.frame.getRblastmembership().isSelected()){
                o.setStart_date_status(1);
            }
            if(this.frame.getRbcustom().isSelected()){
                o.setStart_date_status(2);
            }                        
            
            o.setDuration((Integer)this.frame.getSpduration().getValue());            
            this.pcb = this.bc.getRequestTransaksiStikerController().insert(o);
            if(this.pcb.isError()){
                return new ErrorResponse(this.pcb.getErrmsg());
            }           
            vo.setNotrans(o.getNotrans());
            return new VOResponse(vo);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        }        
    }
    
    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        RequestTransaksiStikerVo vo = (RequestTransaksiStikerVo) persistentObject;
        RequestTransaksiStiker o = new RequestTransaksiStiker();        
        try{
            if(vo.getTgl_approved() != null){
                return new ErrorResponse("Can't edit, transaction already approved");
            }
            o.setNotrans(vo.getNotrans());
            StasiunKerja stasiunkerja = this.bc.getStasiunKerjaController().getByKode(vo.getUnit_kerja());
            if(this.bc.getStasiunKerjaController().getErrStatus() == 1){
                this.bc.getStasiunKerjaController().setErrStatus(0);
                return new ErrorResponse(this.bc.getStasiunKerjaController().getErrMsg());
            }            
            o.setNama(stasiunkerja.getNama());
            o.setNo_id(vo.getNo_id());
            o.setUnit_kerja(vo.getUnit_kerja());
            o.setAwal(vo.getAwal());
            o.setAkhir(vo.getAkhir());
            o.setHarga(vo.getHarga());
            o.setTanggal(vo.getTanggal());
            o.setAdm("00001");
            o.setJenis_transaksi((Integer)this.data.get("jenis_transaksi"));
            o.setJenis_member(vo.getJenis_member());
            System.out.println(vo.getCara_bayar());
            o.setCara_bayar(this.cara_bayar);            
            o.setNopol(vo.getNopol());
            o.setJenis_mobil(vo.getJenis_mobil());
            o.setStatus(1);
            o.setMerk(vo.getMerk());
            o.setTipe(vo.getTipe());
            o.setTahun(vo.getTahun());
            o.setWarna(vo.getWarna());
            o.setTgl_approved(vo.getTgl_approved());
            o.setAdm_approved(vo.getAdm_approved());    
            o.setFlag(vo.getFlag());
            o.setRemark(vo.getRemark());            
            
            if(this.frame.getRbcurrentdate().isSelected()){
                o.setStart_date_status(0);
            }
            if(this.frame.getRblastmembership().isSelected()){
                o.setStart_date_status(1);
            }
            if(this.frame.getRbcustom().isSelected()){
                o.setStart_date_status(2);
            }
            
            o.setDuration((Integer)this.frame.getSpduration().getValue());
            
            this.pcb = this.bc.getRequestTransaksiStikerController().update(o);
            if(this.pcb.isError()){
                return new ErrorResponse(this.pcb.getErrmsg());
            }            
            return new VOResponse(vo);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        }
    }
    
    public ProcessCallBack calculate(){
        Calendar cal = Calendar.getInstance();
        double tarifparkir;
        try{
            if((Integer)this.frame.getSpduration().getValue() <=2){
                this.frame.getRbbilling().setEnabled(false);
            }else{
                this.frame.getRbbilling().setEnabled(true);
            }
            //Get Tarif Parkir
            System.out.println("Calculated");                     
            if(this.frame.getForm1().getMode() == Consts.INSERT){
                this.pcb  = this.bc.getTarifStikerController().getByJenisLangganan((String)this.data.get("jenis_member"));             
                if(this.pcb.isError()){
                   return this.pcb; 
                }
            }else{
                this.pcb = this.bc.getTarifStikerController().getByJenisLangganan((String)this.frame.getTxtjenismember().getValue());             
                if(this.pcb.isError()){
                    return this.pcb;
                }                              
            }            
            
            TarifStiker ts = (TarifStiker) this.pcb.getObject();
            tarifparkir = ts.getTarif();
            
            if(this.frame.getForm1().getMode() == Consts.INSERT){
                //New
                if((Integer)this.data.get("jenis_transaksi") == 0 || this.jenis_transaksi == 0){
                    cal.setTime(this.frame.getDcawal().getDate());
                    cal.add(Calendar.MONTH, (Integer) this.frame.getSpduration().getValue());      
                    this.frame.getDcakhir().setValue(cal.getTime());   
                    if(cara_bayar == 0){
                        this.frame.getCcbayar().setValue(ts.getTarif() * (Integer)this.frame.getSpduration().getValue());            
                    }else{
                        if((Integer)this.frame.getSpduration().getValue() >=4 ){
                            this.frame.getCcbayar().setValue(ts.getTarif() * 2);                    
                        }else{
                            this.frame.getSpduration().setValue(3);
                            this.frame.getCcbayar().setValue(ts.getTarif() * 2);
                        }                                            
                    }
                }

                //Extend
                if((Integer)this.data.get("jenis_transaksi") == 1 || this.jenis_transaksi == 1){ 
                    //Cannot Change cara bayar
                    if(cara_bayar == 0){
                        this.frame.getCcbayar().setValue(ts.getTarif() * (Integer)this.frame.getSpduration().getValue());
                    }else{
                        //Find Last Billing Parking                                        
                        //Billing Parking Found, Akhir Billing data > 2 month  
                        //Billing Parking Not Found

                        if((Integer)this.frame.getSpduration().getValue() >=4 ){
                            this.frame.getCcbayar().setValue(ts.getTarif() * 2);                    
                        }else{
                            this.frame.getSpduration().setValue(3);
                            this.frame.getCcbayar().setValue(ts.getTarif() * 2);
                        }                                                                                               
                    }
                }    

                //Stop
                if((Integer)this.data.get("jenis_transaksi") == 2 || this.jenis_transaksi == 2){
                    this.frame.getCcbayar().setValue(0);     
                    return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Calculate Process Successfully", null);
                }                         
                return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Calculate Process Successfully", null);            
            }else{
                //New
                if((Integer)this.data.get("jenis_transaksi") == 0 || this.jenis_transaksi == 0){
                    cal.setTime(this.frame.getDcawal().getDate());
                    cal.add(Calendar.MONTH, (Integer) this.frame.getSpduration().getValue());      
                    this.frame.getDcakhir().setValue(cal.getTime());   
                    if(cara_bayar == 0){
                        this.frame.getCcbayar().setValue(ts.getTarif() * (Integer)this.frame.getSpduration().getValue());            
                    }else{
                        if((Integer)this.frame.getSpduration().getValue() >=4 ){
                            this.frame.getCcbayar().setValue(ts.getTarif() * 2);                    
                        }else{
                            this.frame.getSpduration().setValue(3);
                            this.frame.getCcbayar().setValue(ts.getTarif() * 2);
                        }                                            
                    }
                }

                //Extend
                if((Integer)this.data.get("jenis_transaksi") == 1 || this.jenis_transaksi == 1){ 
                    //Cannot Change cara bayar
                    if(cara_bayar == 0){
                        this.frame.getCcbayar().setValue(ts.getTarif() * (Integer)this.frame.getSpduration().getValue());
                    }else{
                        //Find Last Billing Parking                                        
                        //Billing Parking Found, Akhir Billing data > 2 month  
                        //Billing Parking Not Found

                        if((Integer)this.frame.getSpduration().getValue() >=4 ){
                            this.frame.getCcbayar().setValue(ts.getTarif() * 2);                    
                        }else{
                            this.frame.getSpduration().setValue(3);
                            this.frame.getCcbayar().setValue(ts.getTarif() * 2);
                        }                                                                                               
                    }
                }    

                //Stop
                if((Integer)this.data.get("jenis_transaksi") == 2 || this.jenis_transaksi == 2){
                    this.frame.getCcbayar().setValue(0);     
                    return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Calculate Process Successfully", null);
                }                         
                return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Calculate Process Successfully", null);                            
            }
                                                                                   
            
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }
    }  
        
    public ProcessCallBack PreparePaymentReceipt(){
        RequestTransaksiStikerRpt orpt;
        this.pcb = this.bc.getRequestTransaksiStikerController().getByNoTrans(this.frame.getTxtnotrans().getText());
        if(this.pcb.isError()){
            return this.pcb;
        }
        RequestTransaksiStiker o = (RequestTransaksiStiker) this.pcb.getObject();        
        orpt = new RequestTransaksiStikerRpt();
        orpt.setNotrans(o.getNotrans());
        orpt.setNama(o.getNama());
        orpt.setNo_id(o.getNo_id());
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
        return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Data Found", orpt);
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public RequestTransaksiStikerDetailFrame getFrame() {
        return frame;
    }

    public void setFrame(RequestTransaksiStikerDetailFrame frame) {
        this.frame = frame;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public int getUrutan() {
        return urutan;
    }

    public void setUrutan(int urutan) {
        this.urutan = urutan;
    }

    public BackingController getBc() {
        return bc;
    }

    public HashMap getData() {
        return data;
    }

    public int getCara_bayar() {
        return cara_bayar;
    }

    public void setCara_bayar(int cara_bayar) {
        this.cara_bayar = cara_bayar;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    
    
}
