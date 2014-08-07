/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jakc.payment.db.entity.StasiunKerja;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author wahhid
 */
public class StasiunKerjaController {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;

    private int ErrStatus=0;
    private String ErrMsg;
    
    public StasiunKerjaController(Connection conn){
        this.conn = conn;
    }
    
    public StasiunKerja getByKode(String kode){
        StasiunKerja o = new StasiunKerja();
        String strSQL = "SELECT * FROM stasiunkerja WHERE kode=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, kode);           
            this.rst = this.pstmt.executeQuery();            
            if(this.rst.next()){
                o.setKode(rst.getString("kode"));
                o.setNama(rst.getString("nama"));
                o.setMargin(rst.getInt("margin"));
                o.setSpv(rst.getString("spv"));
                o.setTarget(rst.getInt("target"));
                o.setStatus(rst.getInt("status"));
            }else{
                this.ErrStatus = 1;
                this.ErrMsg = "Data not found";               
            }
        }catch(Exception ex){
            this.ErrStatus = 1;
            this.ErrMsg = ex.getMessage();
            ex.printStackTrace();
        }
        return o;
    }
    
    public Domain getCB(){
        Domain d = new Domain("STASIUNKERJA");
        String strSQL = "SELECT * FROM stasiunkerja ORDER BY kode";
        
        try {
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                d.addDomainPair(rst.getString("kode"), rst.getString("nama"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }        
        return d;
    }

    public int getErrStatus() {
        return ErrStatus;
    }

    public void setErrStatus(int ErrStatus) {
        this.ErrStatus = ErrStatus;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }
    
    
}
