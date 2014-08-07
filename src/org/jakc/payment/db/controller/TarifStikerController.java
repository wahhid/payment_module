/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.db.entity.TarifStiker;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author wahhid
 */
public class TarifStikerController {
    private ProcessCallBack pcb;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;

    public TarifStikerController(Connection conn){
        this.conn = conn;
    }
    
    public Domain getCBJenisStiker(){
        Domain d = new Domain("jenisstiker");
        d.addDomainPair("1st", "1st");
        d.addDomainPair("2nd", "2nd");
        d.addDomainPair("3rd", "3rd");
        d.addDomainPair("4th", "4th");
        d.addDomainPair("5th", "5th");
        return d;
    }
    
    public ProcessCallBack getByJenisLangganan(String jenislangganan){
        TarifStiker o = new TarifStiker();
        String strSQL = "SELECT * FROM tarif_stiker WHERE jenis_langganan=?";        
        try {
            this.pstmt = conn.prepareStatement(strSQL);
            this.pstmt.setString(1, jenislangganan);
            rst = this.pstmt.executeQuery();            
            if(rst.next()){
                o.setId_mobil(rst.getString("id_mobil"));
                o.setJenis_langganan(rst.getString("jenis_langganan"));
                o.setTarif(rst.getDouble("tarif"));
                return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Data found", o);
            }else{
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + "Data not found", null);
            }
        } catch (SQLException ex) {
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
    
   public Domain getCB(){       
        Domain d = new Domain("TARIFSTIKER");
        String strSQL = "SELECT * FROM tarif_stiker ORDER BY jenis_langganan";
        
        try {
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                d.addDomainPair(rst.getString("jenis_langganan"), rst.getString("jenis_langganan"));
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
}
