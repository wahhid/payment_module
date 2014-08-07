/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.controller;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.Auth;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.BillingParking;
import org.jakc.payment.db.entity.RequestTransaksiStiker;
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.os.gui.RequestTransaksiStikerFormFrame;
import org.jakc.payment.os.vo.RequestTransaksiStikerVo;
import org.jakc.payment.os.vo.TransaksiStikerVo;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;

/**
 *
 * @author wahhid
 */
public class RequestTransaksiStikerFormController extends FormController{

    private ProcessCallBack pcb;
    private Connection conn;
    private Auth auth;
    private String pk;    
    private BackingController bc;
    private RequestTransaksiStikerFormFrame frame;        
    
    
    public RequestTransaksiStikerFormController(Connection conn, Auth auth){
        this.conn = conn;
        this.auth = auth;
        this.bc = new BackingController(this.conn);        
        this.frame = new RequestTransaksiStikerFormFrame(this);
        MDIFrame.add(frame);
    }
    
    
    @Override
    public Response loadData(Class valueObjectClass) {
        RequestTransaksiStiker o;                
        RequestTransaksiStikerVo ovo = null;
        this.pcb = this.bc.getRequestTransaksiStikerController().getByNoTrans(this.pk);                           
        if(this.pcb.isError()){
            new ErrorResponse(this.pcb.getErrmsg());
        }
        o = (RequestTransaksiStiker) this.pcb.getObject();                        
        ovo.setNotrans(o.getNotrans());
        ovo.setNama(o.getNama());
        ovo.setNo_id(o.getNo_id());
        ovo.setUnit_kerja(o.getUnit_kerja());
        ovo.setAwal(o.getAwal());            
        return new VOResponse(o);
           
    }
    
    
    public ProcessCallBack stopMember(TransaksiStikerVo vo,HashMap data){       
        BillingParking bp;                
        String unit_no = "0" + vo.getNotrans().substring(0, 4);        
        System.out.println("unit_no : " + unit_no);
        String jenis_member = vo.getNotrans().substring(4, 5);
        System.out.println("jenis_member : " + jenis_member);
        
        if(jenis_member.equals("1")){
           data.put("jenis_member", "1st");
        }
        if(jenis_member.equals("2")){
           data.put("jenis_member", "2nd");
        }
        if(jenis_member.equals("3")){
           data.put("jenis_member", "3rd");
        }
        if(jenis_member.equals("4")){
           data.put("jenis_member", "4th");
        }
        if(jenis_member.equals("5")){
           data.put("jenis_member", "5th");
        }                     
        bp = this.bc.getBillingParkingController().findForStop(unit_no, jenis_member);       
        
        if(bp != null){
            System.out.println("Billing Parking not null");            
            this.pcb = this.bc.getTransaksiStikerController().getByNotrans(vo.getNotrans());            
            if(this.pcb.isError()){
                return this.pcb;
            }
            TransaksiStiker ts = (TransaksiStiker) this.pcb.getObject();
            System.out.println(ts.getNama());
            System.out.println(ts.getNotrans());      
            System.out.println("bp : " + bp.getAkhir());               
            data.put("awal",ts.getAwal());
            data.put("akhir",bp.getAkhir());
            data.put("cara_bayar",0);           
        }else{
            System.out.println("Billing Parking null");            
            this.pcb = this.bc.getTransaksiStikerController().getByNotrans(vo.getNotrans());
            if(this.pcb.isError()){
                return this.pcb;
            }
            TransaksiStiker ts = (TransaksiStiker) this.pcb.getObject();
            System.out.println(ts.getNama());            
            System.out.println(ts.getNotrans());
            Calendar cal = Calendar.getInstance();                
            data.put("awal",ts.getAwal());
            System.out.println("Awal : " + ts.getAwal());
            int day = ts.getAwal().getDate();           
            System.out.println("Day  :  " + day);
            cal.setTime(new Date(System.currentTimeMillis()));
            cal.set(Calendar.DATE, day);                                
            System.out.println("Akhir Date 1 : " + cal.getTime());
            cal.add(Calendar.MONTH, 2);                                           
            System.out.println("Akhir Date 2: " + cal.getTime());
            data.put("akhir",cal.getTime());                               
            data.put("cara_bayar",0);                                
        }        
        return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Stop Member Process Successfully", null);
    }
    
    public BackingController getBc() {
        return bc;
    }

    public Connection getConn() {
        return conn;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    
    

    
    
    
}
