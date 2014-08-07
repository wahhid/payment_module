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
public class CaraBayarController {
 
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;

    private int ErrStatus=0;
    private String ErrMsg;

    public CaraBayarController(Connection conn){
        this.conn = conn;
    }
    
    public Domain getCB(){
        Domain d = new Domain("CARABAYAR");
        d.addDomainPair((Integer) 0, "NON BILLING");
        d.addDomainPair((Integer) 1, "BILLING");
        return d;
    }
}
