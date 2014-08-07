/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.test;

import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import nabiladbv20.createPOSTGRESConnection;
import org.jakc.payment.ClientApplication;
import org.jakc.payment.util.LoadConfiguration;

/**
 *
 * @author wahhid
 */
public class TestAkhirDate {
    
    private createPOSTGRESConnection Conn;
    private Connection conn;
    private Properties conf = new Properties();  
    
    public TestAkhirDate(){
        this.conf = new LoadConfiguration().load();       
        if(this.conf == null){
            System.exit(1);
        }        
        this.connectdb();
    }
        
    private void connectdb(){
        try{
            this.Conn = new createPOSTGRESConnection(this.conf.getProperty("server"),
                                                  this.conf.getProperty("port"),
                                                  this.conf.getProperty("database"),
                                                  this.conf.getProperty("user"),
                                                  this.conf.getProperty("password"));

    //        this.Conn = new createPOSTGRESConnection(conf.getProperty("dbserver"), 
    //                                                conf.getProperty("dbport"), 
    //                                                conf.getProperty("dbname"), 
    //                                                conf.getProperty("user"), 
    //                                                conf.getProperty("password"));
            this.Conn.openConnection();
            if(this.Conn.getErrStatus() == 1){
                 System.err.println(this.Conn.getErrMsg());
                 System.exit(1);
            }else{            
                this.conn = this.Conn.getConn();
            }            
        } catch (Exception ex){
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void updatedateakhir(){        
    }
    
    public static void main(String[] args){
        
    }
}
