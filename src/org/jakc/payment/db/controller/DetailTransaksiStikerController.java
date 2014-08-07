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
import org.jakc.payment.db.entity.DetailTransaksiStiker;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author wahhid
 */
public class DetailTransaksiStikerController {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;

    private int ErrStatus=0;
    private String ErrMsg;
    
    public DetailTransaksiStikerController(Connection conn){
        this.conn = conn;
    }
    
    public DetailTransaksiStiker getByNotrans(String notrans){
        String strSQL = "SELECT * FROM detail_transaksi_stiker WHERE notrans=?";
        DetailTransaksiStiker o = new DetailTransaksiStiker();                
        try {
            this.pstmt = conn.prepareStatement(strSQL);
            this.pstmt.setString(1, notrans);
            rst = this.pstmt.executeQuery();            
            rst.next();
            if(rst.getRow() == 1){
                o.setNotrans(rst.getString("notrans"));
                o.setNopol(rst.getString("nopol"));
                o.setJenis_mobil(rst.getString("jenis_mobil"));
                o.setAdm(rst.getString("adm"));
                o.setKategori(rst.getInt("kategori"));
                o.setJenis_member(rst.getString("jenis_member"));
                o.setAkses(rst.getString("akses"));
                o.setAkses_out(rst.getString("akses_out"));
                o.setStatus(rst.getInt("status"));
                o.setMerk(rst.getString("merk"));
                o.setTipe(rst.getString("tipe"));
                o.setTahun(rst.getString("tahun"));
                o.setWarna(rst.getString("warna"));
                o.setKeterangan(rst.getString("keterangan"));
            }else{
                this.ErrStatus = 1;
                this.ErrMsg = "Data not found";
            }
        } catch (SQLException ex) {
            this.ErrStatus = 1;
            this.ErrMsg = ex.getMessage();
            ex.printStackTrace();
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                this.ErrStatus = 1;
                this.ErrMsg = ex.getMessage();                
                ex.printStackTrace();                
            }            
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
        }
        return d;
    }    
    
    public void insert(DetailTransaksiStiker o){
        String strSQL = "INSERT INTO detail_transaksi_stiker VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, o.getNotrans());
            this.pstmt.setString(2, o.getNopol());
            this.pstmt.setString(3, o.getJenis_mobil());
            this.pstmt.setString(4, o.getAdm());
            this.pstmt.setInt(5, o.getKategori());
            this.pstmt.setString(6, o.getJenis_member());
            this.pstmt.setString(7, o.getAkses());
            this.pstmt.setString(8, o.getAkses_out());
            this.pstmt.setInt(9, o.getStatus());
            this.pstmt.setString(10, o.getMerk());
            this.pstmt.setString(11, o.getTipe());
            this.pstmt.setString(12, o.getTahun());
            this.pstmt.setString(13, o.getWarna());
            this.pstmt.setString(14, o.getKeterangan());           
            this.pstmt.executeUpdate();
        }catch(Exception ex){
            this.ErrStatus = 1;
            this.ErrMsg = ex.getMessage();
            ex.printStackTrace();
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                this.ErrStatus = 1;
                this.ErrMsg = ex.getMessage();               
                ex.printStackTrace();
            }
        }
    }   
    
    public void update(DetailTransaksiStiker o){
        String strSQL = "UPDATE detail_transaksi_stiker SET "
                + "notrans=?,"
                + "nopol=?,"
                + "jenis_mobil=?,"
                + "adm=?,"
                + "kategori=?,"
                + "jenis_member=?,"
                + "akses=?,"
                + "akses_out=?,"
                + "status=?,"
                + "merk=?,"
                + "tipe=?,"
                + "tahun=?,"
                + "warna=?,"
                + "keterangan=? "
                + "WHERE notrans=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, o.getNotrans());
            this.pstmt.setString(2, o.getNopol());
            this.pstmt.setString(3, o.getJenis_mobil());
            this.pstmt.setString(4, o.getAdm());
            this.pstmt.setInt(5, o.getKategori());
            this.pstmt.setString(6, o.getJenis_member());
            this.pstmt.setString(7, o.getAkses());
            this.pstmt.setString(8, o.getAkses_out());
            this.pstmt.setInt(9, o.getStatus());
            this.pstmt.setString(10, o.getMerk());
            this.pstmt.setString(11, o.getTipe());
            this.pstmt.setString(12, o.getTahun());
            this.pstmt.setString(13, o.getWarna());
            this.pstmt.setString(14, o.getKeterangan());           
            this.pstmt.setString(15, o.getNotrans());
            this.pstmt.executeUpdate();
        }catch(Exception ex){
            this.ErrStatus = 1;
            this.ErrMsg = ex.getMessage();
            ex.printStackTrace();
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                this.ErrStatus = 1;
                this.ErrMsg = ex.getMessage();               
                ex.printStackTrace();
            }
        }        
    }
    
    public void delete(String notrans){
        String strSQL = "UPDATE detail_transaksi_stiker SET status=? WHERE notrans?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, 1);                      
            this.pstmt.setString(2, notrans);
            this.pstmt.executeUpdate();            
        }catch(Exception ex){
            this.ErrStatus = 1;
            this.ErrMsg = ex.getMessage();
            ex.printStackTrace();
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                this.ErrStatus = 1;
                this.ErrMsg = ex.getMessage();               
                ex.printStackTrace();
            }
        }
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
