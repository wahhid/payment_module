/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.db.entity.BillingParking;

/**
 *
 * @author wahhid
 */
public class BillingParkingController {
 
    private ProcessCallBack pcb;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst;
        
    public BillingParkingController(Connection conn){
        this.conn = conn;
    }
    
    public ProcessCallBack insert(BillingParking o){
        String strSQL = "INSERT INTO billing_parking VALUES (?,?,?,?,?,?,?,?)";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, o.getUnitno());
            this.pstmt.setDate(2, new java.sql.Date(o.getDate_trans().getTime()));
            this.pstmt.setString(3, o.getDescription());
            this.pstmt.setDouble(4, o.getAmount());
            this.pstmt.setInt(5, o.getBilling_id());            
            this.pstmt.setDate(6, new java.sql.Date(o.getAkhir().getTime()));
            this.pstmt.setDate(7, new java.sql.Date(o.getAwal().getTime()));
            this.pstmt.setString(8, o.getJenis_langganan());
            this.pstmt.executeUpdate();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Insert successfully", o);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }
        }        
    }
    
    public ProcessCallBack update(BillingParking o){
        String strSQL = "UPDATE billing_parking SET "
                + "unitno=?,"
                + "date_trans=?,"
                + "description=?,"
                + "amount=?,"
                + "billing_id=?,"
                + "jenis_langganan=?,"
                + "awal=?,"
                + "akhir=?"
                + " WHERE billing_id=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, o.getUnitno());
            this.pstmt.setDate(2, new java.sql.Date(o.getDate_trans().getTime()));
            this.pstmt.setString(3, o.getDescription());
            this.pstmt.setDouble(4, o.getAmount());
            this.pstmt.setInt(5, o.getBilling_id());
            this.pstmt.setString(6, o.getJenis_langganan());
            this.pstmt.setDate(7, new java.sql.Date(o.getAwal().getTime()));
            this.pstmt.setDate(8, new java.sql.Date(o.getAkhir().getTime()));            
            this.pstmt.setInt(9, o.getBilling_id());
            this.pstmt.executeUpdate();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Update successfully", o);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.pstmt.close();                
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }
        }         
    }
    
    public ProcessCallBack deleteall(int billing_id){
        String strSQL = "DELETE FROM billing_parking WHERE billing_id=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, billing_id);
            this.pstmt.executeUpdate();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Delete successfully", billing_id);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {                
                this.pstmt.close();                                
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }
        }              
    }
    
    public ProcessCallBack findByBillingId(int billing_id){
        List<BillingParking> os = new ArrayList<BillingParking>();
        String strSQL = "SELECT * FROM billing_parking WHERE billing_id=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, billing_id);
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                BillingParking o = new BillingParking();
                o.setUnitno(rst.getString("unitno"));
                o.setDate_trans(rst.getDate("date_trans"));
                o.setDescription(rst.getString("description"));
                o.setAmount(rst.getInt("amount"));
                o.setBilling_id(rst.getInt("billing_id"));                
                o.setAwal(rst.getDate("awal")); 
                o.setAkhir(rst.getDate("akhir"));         
                o.setJenis_langganan(rst.getString("jenis_langganan"));
                os.add(o);
            }
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "List found", os);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }                    
        }
    }
    
    public BillingParking findForStop(String unitno,String jenis_langganan){
        BillingParking o = new BillingParking();
        String strSQL = "SELECT * FROM billing_parking WHERE unitno=? AND jenis_langganan=? ORDER BY billing_id DESC LIMIT 1";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, unitno);
            this.pstmt.setString(2, jenis_langganan);
            this.rst = this.pstmt.executeQuery();
            if(rst.next()){
                o.setUnitno(rst.getString("unitno"));
                o.setDate_trans(rst.getDate("date_trans"));
                o.setDescription(rst.getString("description"));
                o.setAmount(rst.getDouble("amount"));
                o.setJenis_langganan(rst.getString("jenis_langganan"));
                o.setBilling_id(rst.getInt("billing_id"));
                o.setAwal(rst.getDate("awal"));
                o.setAkhir(rst.getDate("akhir"));
            }else{
                o = null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            o = null;
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                o = null;
            }
        }
        return o;
    }
}
