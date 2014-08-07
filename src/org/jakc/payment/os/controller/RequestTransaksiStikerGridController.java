/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.controller;

import java.awt.Color;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.Auth;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.DetailTransaksiStiker;
import org.jakc.payment.db.entity.RequestTransaksiStiker;
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.os.gui.RequestTransaksiStikerGridFrame;
import org.jakc.payment.os.vo.RequestTransaksiStikerVo;
import org.jakc.payment.report.entity.RequestTransaksiStikerRpt;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.QueryUtil;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author wahhid
 */
public class RequestTransaksiStikerGridController extends GridController implements GridDataLocator{

    private Connection conn;
    private Auth auth;
    private BackingController bc;
    private RequestTransaksiStikerGridFrame frame;
    private RequestTransaksiStikerVo vo;
    private HashMap data;
    private ProcessCallBack pcb;
    
    public RequestTransaksiStikerGridController(Connection conn, Auth auth){
        this.conn = conn;
        this.auth = auth;
        this.bc = new BackingController(this.conn);
        this.frame = new RequestTransaksiStikerGridFrame(this,this.conn);
        MDIFrame.add(frame);
    }
        
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        RequestTransaksiStikerVo vo = (RequestTransaksiStikerVo) persistentObject;
        this.data = new HashMap();          
        this.data.put("last_awal",null);
        this.data.put("last_akhir",null);               
        new RequestTransaksiStikerDetailController(this.vo.getNotrans(), this.data, this.conn,this.auth);               
        
    }
     
    
    @Override
    public void afterReloadGrid() {
        
    }
    
    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {                
        
        if(attributeName.equals("approvedstatus")){
            if(value == null){                
                return new Color(255,100,100);
            }            
        }
        return super.getBackgroundColor(row, attributeName, value);
       
    }
    
    @Override
    public void selectedCell(int rowNumber, int columnIndex, String attributedName, ValueObject persistentObject) {
        vo = (RequestTransaksiStikerVo) persistentObject;
    }
 
    public ProcessCallBack approved(){               
        RequestTransaksiStiker o ;
        TransaksiStiker ts;
        DetailTransaksiStiker dts;
        this.pcb  = this.bc.getRequestTransaksiStikerController().getByNoTrans(vo.getNotrans());
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
                this.bc.getRequestTransaksiStikerController().approved(vo.getNotrans(),this.auth.getPegawai().getUsername());
            }
                                  
        }
        
        //Extend Membership
        if(o.getJenis_transaksi() == 1){
            this.pcb = this.bc.getTransaksiStikerController().getByNotrans(vo.getNo_id());
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
            System.out.println(strDateAkhir);
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
            dts = this.bc.getDetailTransaksiStikerController().getByNotrans(vo.getNo_id());                    
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
                this.bc.getRequestTransaksiStikerController().approved(vo.getNotrans(),this.auth.getPegawai().getUsername());
            }                    
        }
        
        //Stop Membership
        if(o.getJenis_transaksi() == 2){
            this.pcb = this.bc.getTransaksiStikerController().getByNotrans(vo.getNo_id());
            if(this.pcb.isError()){
                return this.pcb;
            }
            ts = (TransaksiStiker) this.pcb.getObject();
            
            ts.setAkhir(vo.getAkhir());
            ts.setCara_bayar(0);               
            this.pcb = this.bc.getTransaksiStikerController().update(ts);
            if(this.pcb.isError()){
                return this.pcb;
            }
            this.bc.getRequestTransaksiStikerController().approved(vo.getNotrans(),this.auth.getPegawai().getUsername());                                                                                   
        }
        
        //Change Car Plat Number
        if(o.getJenis_transaksi() == 3){
            
        }
        return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " +  "Approved Successfully", null);
    }
        
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try{
          ArrayList vals = new ArrayList();
          String strSQL="SELECT " +
                  "request_transaksi_stiker.notrans," +
                  "request_transaksi_stiker.nama," +    
                  "request_transaksi_stiker.no_id," +    
                  "request_transaksi_stiker.unit_kerja," +
                  "request_transaksi_stiker.awal," +    
                  "request_transaksi_stiker.akhir," +
                  "request_transaksi_stiker.harga," +    
                  "request_transaksi_stiker.tanggal," +
                  "request_transaksi_stiker.adm," +    
                  "request_transaksi_stiker.jenis_transaksi," +
                  "request_transaksi_stiker.cara_bayar," +    
                  "request_transaksi_stiker.nopol," +    
                  "request_transaksi_stiker.jenis_mobil," +                      
                  "request_transaksi_stiker.jenis_member," +
                  "request_transaksi_stiker.status," +    
                  "request_transaksi_stiker.merk," +    
                  "request_transaksi_stiker.tipe," +    
                  "request_transaksi_stiker.tahun," +    
                  "request_transaksi_stiker.warna," +                         
                  "request_transaksi_stiker.tgl_approved," +
                  "request_transaksi_stiker.adm_approved," +    
                  "request_transaksi_stiker.flag," +    
                  "request_transaksi_stiker.remark," +    
                  "request_transaksi_stiker.start_date_status," +    
                  "request_transaksi_stiker.duration," +    
                  "request_transaksi_stiker.approvedstatus" +    
                  " FROM request_transaksi_stiker WHERE request_transaksi_stiker.status=1";
          
          Map attribute2dbField = new HashMap();
          attribute2dbField.put("notrans","request_transaksi_stiker.notrans");
          attribute2dbField.put("nama","request_transaksi_stiker.nama");
          attribute2dbField.put("no_id","request_transaksi_stiker.no_id");
          attribute2dbField.put("unit_kerja","request_transaksi_stiker.unit_kerja");
          attribute2dbField.put("awal","request_transaksi_stiker.awal");
          attribute2dbField.put("akhir","request_transaksi_stiker.akhir");         
          attribute2dbField.put("harga","request_transaksi_stiker.harga");
          attribute2dbField.put("tanggal","request_transaksi_stiker.tanggal");
          attribute2dbField.put("adm","request_transaksi_stiker.adm");
          attribute2dbField.put("jenis_transaksi","request_transaksi_stiker.jenis_transaksi");
          attribute2dbField.put("cara_bayar","request_transaksi_stiker.cara_bayar");  
          attribute2dbField.put("nopol","request_transaksi_stiker.nopol");
          attribute2dbField.put("jenis_mobil","request_transaksi_stiker.jenis_mobil");          
          attribute2dbField.put("jenis_member","request_transaksi_stiker.jenis_member");
          attribute2dbField.put("status","request_transaksi_stiker.status");
          attribute2dbField.put("merk","request_transaksi_stiker.merk");
          attribute2dbField.put("tipe","request_transaksi_stiker.tipe");
          attribute2dbField.put("tahun","request_transaksi_stiker.tahun");
          attribute2dbField.put("warna","request_transaksi_stiker.warna");          
          attribute2dbField.put("tgl_approved","request_transaksi_stiker.tgl_approved");
          attribute2dbField.put("adm_approved","request_transaksi_stiker.adm_approved");
          attribute2dbField.put("flag","request_transaksi_stiker.flag");
          attribute2dbField.put("remark","request_transaksi_stiker.remark");
          attribute2dbField.put("start_date_status","request_transaksi_stiker.start_date_status");
          attribute2dbField.put("duration","request_transaksi_stiker.duration");
          attribute2dbField.put("approvedstatus","request_transaksi_stiker.approvedstatus");

          return QueryUtil.getQuery(
            conn,
            strSQL,
            new ArrayList(), // list of values linked to "?" parameters in sql
            attribute2dbField,
            RequestTransaksiStikerVo.class, // v.o. to dinamically create for each row...
            "Y",
            "N",
            new GridParams(
              action,
              startIndex,
              filteredColumns,
              currentSortedColumns,
              currentSortedVersusColumns,
              new HashMap() // other params...
            ),
            25, // pagination size...
            true // log query...
          );
        }
        catch (Exception ex) {
          ex.printStackTrace();
          return new ErrorResponse(ex.getMessage());
        }
    }    
        

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        List<RequestTransaksiStikerVo> vos = persistentObjects;
        for(RequestTransaksiStikerVo vo : vos){
            if(vo.getAdm_approved() == null){
                this.bc.getRequestTransaksiStikerController().delete(vo.getNotrans());
            }
        }
        return new VOListResponse(vos,false,vos.size());
    }
    
    

    @Override
    public void afterDeleteGrid() {
        
    }
    
    public ProcessCallBack PreparePaymentReceipt(){
        RequestTransaksiStikerRpt orpt;
        this.pcb = this.bc.getRequestTransaksiStikerController().getByNoTrans(vo.getNotrans());
        if(this.pcb.isError()){
            return this.pcb;
        }
        RequestTransaksiStiker o = (RequestTransaksiStiker) this.pcb.getObject();        
        orpt = new RequestTransaksiStikerRpt();
        orpt.setNotrans(o.getNotrans());
        orpt.setNama(o.getNama());
        orpt.setNo_id(o.getNo_id());
        orpt.setUnit_kerja(o.getUnit_kerja());
        orpt.setNopol(o.getNopol());
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
    
    public RequestTransaksiStikerVo getVo() {
        return vo;
    }

    public BackingController getBc() {
        return bc;
    }
    
    public void printreciept(){       

    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    
    
}
