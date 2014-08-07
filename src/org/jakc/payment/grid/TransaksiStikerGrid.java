/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.grid;

import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.BillingParking;
import org.jakc.payment.db.entity.DetailTransaksiStiker;
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.os.gui.RequestTransaksiStikerFormFrame;
import org.jakc.payment.os.vo.TransaksiStikerVo;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.QueryUtil;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author wahhid
 */
public class TransaksiStikerGrid extends GridController implements GridDataLocator{

    private ProcessCallBack pcb;
    private Connection conn;
    private BackingController bc;
    private RequestTransaksiStikerFormFrame frame;
    private String pk = "0";    
    private TransaksiStikerVo vo;
    private DetailTransaksiStiker detailTransaksiStiker;
    
    public TransaksiStikerGrid(RequestTransaksiStikerFormFrame frame,Connection conn){
        this.conn = conn;
        this.bc = new BackingController(this.conn);
        this.frame = frame;
    }
            
    @Override
    public void selectedCell(int rowNumber, int columnIndex, String attributedName, ValueObject persistentObject) {
        Calendar cal = Calendar.getInstance();
        vo = (TransaksiStikerVo) persistentObject;
        this.detailTransaksiStiker = this.bc.getDetailTransaksiStikerController().getByNotrans(vo.getNotrans());        
        this.frame.getBtnextend().setText("Extend - " + vo.getNotrans());          
        this.frame.getBtnstop().setText("Stop - " + vo.getNotrans());
        if(vo.getCara_bayar() == 0){
            this.frame.getBtnstop().setEnabled(false);
        }else{
            this.frame.getBtnstop().setEnabled(true);
            cal.setTime(vo.getAkhir());
            cal.add(Calendar.MONTH, -2);
            Date currentDate  = new Date(System.currentTimeMillis());
            Date referenceDate = cal.getTime();
            if(currentDate.after(referenceDate)){
                this.frame.getBtnstop().setEnabled(false);
            }
        }
    }
      
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try{
          ArrayList vals = new ArrayList();
          String strSQL="SELECT " +
                  "transaksi_stiker.notrans," +
                  "transaksi_stiker.nama," +
//                  "transaksi_stiker.alamat," +
//                  "transaksi_stiker.telepon," +
                  "transaksi_stiker.jenis_transaksi," +                 
                  "transaksi_stiker.awal," +
//                  "transaksi_stiker.harga," +
//                  "transaksi_stiker.keterangan," +
//                  "transaksi_stiker.tanggal," +
//                  "transaksi_stiker.operator," +
                  "transaksi_stiker.akhir," +
//                  "transaksi_stiker.maks," +
//                  "transaksi_stiker.no_id," +
//                  "transaksi_stiker.unit_kerja," +
//                  "transaksi_stiker.no_induk," +
//                  "transaksi_stiker.jenis_stiker," +
//                  "transaksi_stiker.hari_ke," +
//                  "transaksi_stiker.jenis_langganan," +
//                  "transaksi_stiker.exit_pass," +
//                  "transaksi_stiker.no_kuitansi," +
//                  "transaksi_stiker.tgl_edited," +
//                  "transaksi_stiker.tipe_exit_pass," +
//                  "transaksi_stiker.seq_code," +
//                  "transaksi_stiker.unitno," +
//                  "transaksi_stiker.area," +
//                  "transaksi_stiker.reserved," +
                  "transaksi_stiker.cara_bayar" + 
                  " FROM transaksi_stiker WHERE transaksi_stiker.unit_kerja='" + this.pk.trim() + "'";
          Map attribute2dbField = new HashMap();
          attribute2dbField.put("notrans","transaksi_stiker.notrans");
          attribute2dbField.put("nama","transaksi_stiker.nama");
//          attribute2dbField.put("alamat","transaksi_stiker.alamat");
//          attribute2dbField.put("telepon","transaksi_stiker.telepon");
          attribute2dbField.put("jenis_transaksi","transaksi_stiker.jenis_transaksi");
          attribute2dbField.put("awal","transaksi_stiker.awal");
//          attribute2dbField.put("harga","transaksi_stiker.harga");
//          attribute2dbField.put("keterangan","transaksi_stiker.keterangan");          
//          attribute2dbField.put("tanggal","transaksi_stiker.tanggal");
//          attribute2dbField.put("operator","transaksi_stiker.operator");          
          attribute2dbField.put("akhir","transaksi_stiker.akhir");      
//          attribute2dbField.put("maks","transaksi_stiker.maks");
//          attribute2dbField.put("no_id","transaksi_stiker.no_id");
//          attribute2dbField.put("unit_kerja","transaksi_stiker.unit_kerja");
//          attribute2dbField.put("no_induk","transaksi_stiker.no_induk");
//          attribute2dbField.put("jenis_stiker","transaksi_stiker.jenis_stiker");
//          attribute2dbField.put("hari_ke","transaksi_stiker.hari_ke");
//          attribute2dbField.put("jenis_langganan","transaksi_stiker.jenis_langganan");
//          attribute2dbField.put("exit_pass","transaksi_stiker.exit_pass");
//          attribute2dbField.put("no_kuitansi","transaksi_stiker.no_kuitansi");
//          attribute2dbField.put("tgl_edited","transaksi_stiker.tgl_edited");
//          attribute2dbField.put("tipe_exit_pass","transaksi_stiker.tipe_exit_pass");
//          attribute2dbField.put("seq_code","transaksi_stiker.seq_code");
//          attribute2dbField.put("unitno","transaksi_stiker.unitno");
//          attribute2dbField.put("area","transaksi_stiker.area");
//          attribute2dbField.put("reserved","transaksi_stiker.reserved");          
          attribute2dbField.put("cara_bayar","transaksi_stiker.cara_bayar");          

          return QueryUtil.getQuery(
            conn,
            strSQL,
            new ArrayList(), // list of values linked to "?" parameters in sql
            attribute2dbField,
            TransaksiStikerVo.class, // v.o. to dinamically create for each row...
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

  
    public ProcessCallBack stopMember(){
        BillingParking bp;
        TransaksiStiker ts;
        System.out.println("Stop Billing");
        String unit_no = "0" + this.vo.getNotrans().substring(0,4);
        System.out.println("Unit No : " + unit_no);
        String jenis_langganan = this.vo.getNotrans().substring(4);
        System.out.println("Jenis Langganan : " + jenis_langganan);        
        
        if(jenis_langganan.equals("1")){
            jenis_langganan = "1st";
        }
        if(jenis_langganan.equals("2")){
            jenis_langganan = "2nd";
        }
        if(jenis_langganan.equals("3")){
            jenis_langganan = "3rd";
        }
        if(jenis_langganan.equals("4")){
            jenis_langganan = "4th";
        }
        if(jenis_langganan.equals("5")){
            jenis_langganan = "5th";
        }
        System.out.println("Jenis Langganan : " + jenis_langganan);
        bp = this.bc.getBillingParkingController().findForStop(unit_no, jenis_langganan);
        if(bp != null){
            System.out.println("Billing Parking not null");
            System.out.println("Notrans : " + this.vo.getNotrans());
            this.pcb = this.bc.getTransaksiStikerController().getByNotrans(this.vo.getNotrans());
            if(this.pcb.isError()){
                return this.pcb;
            }else{
                ts = (TransaksiStiker) this.pcb.getObject();
            }
            System.out.println(ts.getNama());            
            System.out.println(ts.getNotrans());      
            System.out.println("bp : " + bp.getAkhir());
            ts.setAkhir(bp.getAkhir());                      
            ts.setCara_bayar(0);
            System.out.println("1 : " + ts.getAkhir());
            Calendar cal = Calendar.getInstance();
            cal.setTime(ts.getAkhir());
            System.out.println(cal.getTime());
            this.bc.getTransaksiStikerController().update(ts);
            this.frame.getGridControl1().reloadData();                        
        }else{
            System.out.println("Billing Parking null");
            System.out.println("Notrans : " + this.vo.getNotrans());
            this.pcb = this.bc.getTransaksiStikerController().getByNotrans(this.vo.getNotrans());
            if(this.pcb.isError()){
                return this.pcb;
            }else{
                ts = (TransaksiStiker) this.pcb.getObject();                        
            }
            System.out.println(ts.getNama());            
            System.out.println(ts.getNotrans());
            Calendar cal = Calendar.getInstance();
            cal.setTime(ts.getAwal());
            cal.add(Calendar.MONTH, 2);           
            System.out.println(cal.getTime().getYear());
            ts.setAkhir(cal.getTime());
            System.out.println("2 : " + ts.getAkhir());
            ts.setCara_bayar(0);
            this.bc.getTransaksiStikerController().update(ts);    
            this.frame.getGridControl1().reloadData();            
        }                           
        return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Stop Member Successfully", null);
    }
    
    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {                
        if(attributeName.equals("akhir")){
            Date akhir = (Date) value;
            if(akhir.before(new Date(System.currentTimeMillis()))){                
                return new Color(255,100,100);
            }            
        }
        return super.getBackgroundColor(row, attributeName, value);
       
    }
        
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public TransaksiStikerVo getVo() {
        return vo;
    }

    public void setVo(TransaksiStikerVo vo) {
        this.vo = vo;
    }

    public DetailTransaksiStiker getDetailTransaksiStiker() {
        return detailTransaksiStiker;
    }

    public void setDetailTransaksiStiker(DetailTransaksiStiker detailTransaksiStiker) {
        this.detailTransaksiStiker = detailTransaksiStiker;
    }                
    
}
