/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author wahhid
 */
public class JenisMobilController  {
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;

    private int ErrStatus=0;
    private String ErrMsg;

    
    public JenisMobilController(Connection conn){
        this.conn = conn;
    }
    
    public Domain getCB(){
        Domain d = new Domain("JENISMOBIL");
        String strSQL = "SELECT * FROM jenis_mobil ORDER BY nama";
        
        try {
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                d.addDomainPair(rst.getString("id"), rst.getString("nama"));
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
