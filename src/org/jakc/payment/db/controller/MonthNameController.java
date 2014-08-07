/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.controller;

import java.sql.Connection;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author wahhid
 */
public class MonthNameController {
   
    private Connection conn;
    
    public MonthNameController(Connection conn){
        this.conn = conn;
    }
       
    public Domain getCB(){
        Domain d = new Domain("MONTHNAME");
        d.addDomainPair(1, "January");
        d.addDomainPair(2, "February");
        d.addDomainPair(3, "March");
        d.addDomainPair(4, "April");
        d.addDomainPair(5, "May");
        d.addDomainPair(6, "June");
        d.addDomainPair(7, "July");
        d.addDomainPair(8, "August");
        d.addDomainPair(9, "September");
        d.addDomainPair(10, "October");
        d.addDomainPair(11, "November");
        d.addDomainPair(12, "December");
        return d;
    }
    
}
