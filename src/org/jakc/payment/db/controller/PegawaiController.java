/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.payment.db.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jakc.payment.db.entity.Pegawai;

/**
 *
 * @author wahhid
 */
public class PegawaiController {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;

    private int ErrStatus=0;
    private String ErrMsg;

    
    public PegawaiController(Connection conn){
        this.conn = conn;
    }

    public Pegawai checkuser(String username,String password){
        Pegawai o = this.getUser(username);
        if(o != null){
            if(o.getPassword().equals(password)){
                return o;    
            }else{
                return null;
            }            
        }else{
            return null;
        }
    }

    public Pegawai getUser(String username){
        Pegawai user = new Pegawai();
        String strSQL = "SELECT * FROM pegawai WHERE username=?";
        try {
            this.pstmt = conn.prepareStatement(strSQL);
            this.pstmt.setString(1, username);
            rst = this.pstmt.executeQuery();
            rst.next();
            if(rst.getRow() == 1){                  
                user.setUsername(rst.getString("username"));
                user.setPassword(rst.getString("password"));                
                user.setBisalogin(rst.getInt("bisalogin"));
            }else{
                this.ErrStatus = 1;
                this.ErrMsg = "User not found";
                user = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            this.ErrStatus = 1;
            this.ErrMsg = ex.getMessage();
            user = null;
        }
        return user;
    }

    /**
     * @return the ErrStatus
     */
    public int getErrStatus() {
        return ErrStatus;
    }

    /**
     * @param ErrStatus the ErrStatus to set
     */
    public void setErrStatus(int ErrStatus) {
        this.ErrStatus = ErrStatus;
    }

    /**
     * @return the ErrMsg
     */
    public String getErrMsg() {
        return ErrMsg;
    }

    /**
     * @param ErrMsg the ErrMsg to set
     */
    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }
}
