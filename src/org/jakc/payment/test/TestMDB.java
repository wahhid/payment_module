/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wahhid
 */
public class TestMDB {
    
    public static void main(String[] args){
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rst;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn = DriverManager.getConnection("jdbc:odbc:billing");   
            String strSQL = "SELECT * FROM billing_parking";            
            pstmt = conn.prepareStatement(strSQL);
            rst = pstmt.executeQuery();
            while(rst.next()){
                System.out.println(rst.getString("unitno"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
}
