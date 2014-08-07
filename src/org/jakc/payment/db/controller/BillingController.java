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
import org.jakc.payment.db.entity.Billing;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author wahhid
 */
public class BillingController {
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst;
    
    private ProcessCallBack pcb;
    
    public BillingController(Connection conn){
        this.conn = conn;
    }

    public Domain getCB(){
        Domain d = new Domain("BILLING");
        this.pcb = this.findAll();
        if(!this.pcb.isError()){                    
            for(Billing o : (List<Billing>)this.pcb.getObject()){
                String monthname = null;
                if(o.getBilling_month() == 1)
                {
                    monthname = "January";
                }
                if(o.getBilling_month() == 2)
                {
                    monthname = "February";
                }
                if(o.getBilling_month() == 3)
                {
                    monthname = "March";
                }
                if(o.getBilling_month() == 4)
                {
                    monthname = "April";
                }
                if(o.getBilling_month() == 5)
                {
                    monthname = "May";
                }
                if(o.getBilling_month() == 6)
                {
                    monthname = "June";
                }
                if(o.getBilling_month() == 7)
                {
                    monthname = "July";
                }
                if(o.getBilling_month() == 8)
                {
                    monthname = "August";
                }
                if(o.getBilling_month() == 9)
                {
                    monthname = "September";
                }
                if(o.getBilling_month() == 10)
                {
                    monthname = "October";
                }
                if(o.getBilling_month() == 11)
                {
                    monthname = "November";
                }
                if(o.getBilling_month() == 12)
                {
                    monthname = "December";
                }            
                d.addDomainPair(o.getBilling_id(), o.getBilling_year() + " - " + monthname);
            }
        }        
        return d;
    }
    
    public ProcessCallBack insert(Billing o){                
        String strSQL = "INSERT INTO billing VALUES (?,?,?)";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, o.getBilling_year());
            this.pstmt.setInt(2, o.getBilling_month());
            this.pstmt.setInt(3, o.getBilling_status());
            this.pstmt.executeUpdate();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " +  "Insert Successfully", o);            
        }catch(Exception ex){
            ex.printStackTrace();            
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.pstmt.close();
                this.rst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();            
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
        }
    }
    
    public ProcessCallBack update(Billing o){
        String strSQL = "UPDATE billing SET "
                + "billing_year=?,"
                + "billing_month=?,"
                + "billing_status=?"
                + " WHERE billing_id=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, o.getBilling_year());
            this.pstmt.setInt(2, o.getBilling_month());
            this.pstmt.setInt(3, o.getBilling_status());
            this.pstmt.setInt(4, o.getBilling_id());
            this.pstmt.executeUpdate();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Update Successfully", o);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.pstmt.close();
                this.rst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();                
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
        }        
    }
        
    public ProcessCallBack generated(int billing_id){
        String strSQL = "UPDATE billing SET "
                + "billing_status=?"
                + " WHERE billing_id=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, 1);
            this.pstmt.setInt(2, billing_id);
            this.pstmt.executeUpdate();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Generated successfully", billing_id);                                    
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.pstmt.close();
                this.rst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                                        
            }
        }                
    }
    
    public ProcessCallBack findAll(){
        List<Billing> os = new ArrayList();
        String strSQL = "SELECT * FROM billing";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                Billing o = new Billing();
                o.setBilling_id(rst.getInt("billing_id"));
                o.setBilling_year(rst.getInt("billing_year"));
                o.setBilling_month(rst.getInt("billing_month"));
                o.setBilling_status(rst.getInt("billing_status"));
                os.add(o);
            }            
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Find all succesfully", os);                        
        }catch(Exception ex){
           ex.printStackTrace();
           return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);
            }
        }
    }
    
    public ProcessCallBack getByBillingId(int billing_id){
        Billing o = new Billing();
        String strSQL = "SELECT * FROM billing WHERE billing_id=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, billing_id);
            this.rst = this.pstmt.executeQuery();            
            if(rst.next()){
                o.setBilling_id(rst.getInt("billing_id"));
                o.setBilling_year(rst.getInt("billing_year"));
                o.setBilling_month(rst.getInt("billing_month"));
                o.setBilling_status(rst.getInt("billing_status"));
                return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Find successfully", o);                                        
            }else{
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + "Data not found", null);                        
            }            
        }catch(Exception ex){
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {    
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                                                    
            }
        }
    }
    
    public ProcessCallBack CloseBilling(int billing_id){
       String strSQL = "UPDATE billing SET billing_status=1 WHERE billing_id=?";
       try{
           this.pstmt = this.conn.prepareStatement(strSQL);
           this.pstmt.setInt(1, billing_id);
           this.pstmt.executeUpdate();                   
           return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Close billing successfully", null);                        
       }catch(SQLException ex){
           ex.printStackTrace();
           return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
       }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
       }
    }
    
}
