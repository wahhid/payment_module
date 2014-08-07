/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author wahhid
 */
public class JenisTransaksiController {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;

    private int ErrStatus=0;
    private String ErrMsg;
    
    public JenisTransaksiController(Connection conn){
        this.conn = conn;
    }
    
    public Domain getCB(){
        Domain d = new Domain("JENISTRANSAKSI");
        d.addDomainPair(0, "Langganan Baru");
        d.addDomainPair(1, "Perpanjang Langganan");
        d.addDomainPair(2, "Stop Langganan");
        return d;
    }
        
}
