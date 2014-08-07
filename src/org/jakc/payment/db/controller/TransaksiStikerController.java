/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.os.vo.BillingVo;

/**
 *
 * @author wahhid
 */
public class TransaksiStikerController {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;
    private ProcessCallBack pcb;
    
    public TransaksiStikerController(Connection conn){
        this.conn = conn;
    }
    
    public ProcessCallBack getByNotrans(String notrans){
        String strSQL = "SELECT * FROM transaksi_stiker WHERE notrans=?";
        TransaksiStiker o = new TransaksiStiker();                
        try {
            this.pstmt = conn.prepareStatement(strSQL);
            this.pstmt.setString(1, notrans);
            rst = this.pstmt.executeQuery();                        
            if(rst.next()){
                o.setNotrans(rst.getString("notrans"));
                o.setNama(rst.getString("nama"));
                o.setAlamat(rst.getString("alamat"));
                o.setTelepon(rst.getString("telepon"));
                o.setJenis_transaksi(rst.getInt("jenis_transaksi"));
                o.setAwal(rst.getDate("awal"));                
                o.setHarga(rst.getDouble("harga"));
                o.setKeterangan((rst.getString("keterangan")));
                o.setTanggal(rst.getDate("tanggal"));
                o.setOperator(rst.getString("operator"));
                o.setAkhir(rst.getDate("akhir"));
                o.setMaks(rst.getInt("maks"));
                o.setNo_id(rst.getString("no_id"));
                o.setUnit_kerja(rst.getString("unit_kerja"));
                o.setNo_induk(rst.getString("no_induk"));
                o.setJenis_stiker(rst.getInt("jenis_stiker"));
                o.setHari_ke(rst.getString("hari_ke"));
                o.setJenis_langganan(rst.getString("jenis_langganan"));
                o.setExit_pass(rst.getInt("exit_pass"));
                o.setNo_kuitansi(rst.getString("no_kuitansi"));
                o.setTgl_edited(rst.getDate("tgl_edited"));
                o.setTipe_exit_pass(rst.getInt("tipe_exit_pass"));
                o.setSeq_code(rst.getString("seq_code"));
                o.setUnitno(rst.getString("unitno"));
                o.setArea(rst.getString("area"));
                o.setReserved(rst.getInt("reserved"));
                o.setCara_bayar(rst.getInt("cara_bayar"));
                return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Data found", o);
            }else{
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + "Data not found", null);                
            }
        } catch (SQLException ex) {            
            ex.printStackTrace();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();                
                return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }            
        }         
    }

    public ProcessCallBack insert(TransaksiStiker o){
        String strSQL = "INSERT INTO transaksi_stiker VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, o.getNotrans());
            this.pstmt.setString(2, o.getNama()); 
            this.pstmt.setString(3, o.getAlamat());
            this.pstmt.setString(4, o.getTelepon());
            this.pstmt.setInt(5, o.getJenis_transaksi());
            this.pstmt.setDate(6, new java.sql.Date(o.getAwal().getTime()));
            this.pstmt.setDouble(7, o.getHarga());
            this.pstmt.setString(8, o.getKeterangan());
            this.pstmt.setDate(9, new java.sql.Date(o.getTanggal().getTime()));
            this.pstmt.setString(10, o.getOperator());
            this.pstmt.setTimestamp(11, new java.sql.Timestamp(o.getAkhir().getTime()));
            this.pstmt.setInt(12, o.getMaks());
            this.pstmt.setString(13, o.getNo_id());
            this.pstmt.setString(14, o.getUnit_kerja());
            this.pstmt.setString(15, o.getNo_induk());
            this.pstmt.setInt(16, o.getJenis_stiker());
            this.pstmt.setString(17, o.getHari_ke());
            this.pstmt.setString(18, o.getJenis_langganan());
            this.pstmt.setInt(19, o.getExit_pass());
            this.pstmt.setString(20, o.getNo_kuitansi());
            this.pstmt.setDate(21, new java.sql.Date(o.getTgl_edited().getTime()));
            this.pstmt.setInt(22, o.getTipe_exit_pass());
            this.pstmt.setString(23, o.getSeq_code());
            this.pstmt.setString(24, o.getUnitno());
            this.pstmt.setString(25, o.getArea());
            this.pstmt.setInt(26, o.getReserved());
            this.pstmt.setInt(27, o.getCara_bayar());
            this.pstmt.executeUpdate();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Data found", o);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }
        }
                
    }
    
    public ProcessCallBack update(TransaksiStiker o){
        String strSQL = "UPDATE transaksi_stiker SET "
                + "notrans=?,"
                + "nama=?,"
                + "alamat=?,"
                + "telepon=?,"
                + "jenis_transaksi=?,"
                + "awal=?,"
                + "harga=?,"
                + "keterangan=?,"
                + "tanggal=?,"
                + "operator=?,"
                + "akhir=?,"
                + "maks=?,"
                + "no_id=?,"
                + "unit_kerja=?,"
                + "no_induk=?,"
                + "jenis_stiker=?,"
                + "hari_ke=?,"
                + "jenis_langganan=?,"
                + "exit_pass=?,"
                + "no_kuitansi=?,"
                + "tgl_edited=?,"
                + "tipe_exit_pass=?,"
                + "seq_code=?,"
                + "unitno=?,"
                + "area=?,"
                + "reserved=?,"
                + "cara_bayar=?  "
                + "WHERE notrans=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, o.getNotrans());
            this.pstmt.setString(2, o.getNama()); 
            this.pstmt.setString(3, o.getAlamat());
            this.pstmt.setString(4, o.getTelepon());
            this.pstmt.setInt(5, o.getJenis_transaksi());
            this.pstmt.setDate(6, new java.sql.Date(o.getAwal().getTime()));
            this.pstmt.setDouble(7, o.getHarga());
            this.pstmt.setString(8, o.getKeterangan());
            this.pstmt.setDate(9, new java.sql.Date(o.getTanggal().getTime()));
            this.pstmt.setString(10, o.getOperator());
            this.pstmt.setTimestamp(11, new java.sql.Timestamp(o.getAkhir().getTime()));
            this.pstmt.setInt(12, o.getMaks());
            this.pstmt.setString(13, o.getNo_id());
            this.pstmt.setString(14, o.getUnit_kerja());
            this.pstmt.setString(15, o.getNo_induk());
            this.pstmt.setInt(16, o.getJenis_stiker());
            this.pstmt.setString(17, o.getHari_ke());
            this.pstmt.setString(18, o.getJenis_langganan());
            this.pstmt.setInt(19, o.getExit_pass());
            this.pstmt.setString(20, o.getNo_kuitansi());
            this.pstmt.setDate(21, new java.sql.Date(o.getTgl_edited().getTime()));
            this.pstmt.setInt(22, o.getTipe_exit_pass());
            this.pstmt.setString(23, o.getSeq_code());
            this.pstmt.setString(24, o.getUnitno());
            this.pstmt.setString(25, o.getArea());
            this.pstmt.setInt(26, o.getReserved());
            this.pstmt.setInt(27, o.getCara_bayar());
            this.pstmt.setString(28, o.getNotrans());
            this.pstmt.executeUpdate();
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Data found", o);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }
        }       
    }
    
    public ProcessCallBack delete(String notrans){
        String strSQL = "UPDATE transaksi_stiker SET status=? WHERE notrans=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, 0);
            this.pstmt.setString(2, notrans);
            this.pstmt.executeUpdate();                   
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Delete successfully", notrans);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }
        }          
    }

    public ProcessCallBack billingList(){
        List<TransaksiStiker> os = new ArrayList();              
        TransaksiStiker o;
        Calendar cal = Calendar.getInstance();
        String strSQL = "SELECT * FROM transaksi_stiker WHERE cara_bayar=1";
        //String strSQL = "SELECT * FROM transaksi_stiker";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);            
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                o = new TransaksiStiker();
                o.setNotrans(rst.getString("notrans"));
                o.setNama(rst.getString("nama"));
                o.setAlamat(rst.getString("alamat"));
                o.setTelepon(rst.getString("telepon"));
                o.setJenis_transaksi(rst.getInt("jenis_transaksi"));
                o.setAwal(rst.getDate("awal"));                
                o.setHarga(rst.getDouble("harga"));
                o.setKeterangan((rst.getString("keterangan")));
                o.setTanggal(rst.getDate("tanggal"));
                o.setOperator(rst.getString("operator"));
                o.setAkhir(rst.getDate("akhir"));
                o.setMaks(rst.getInt("maks"));
                o.setNo_id(rst.getString("no_id"));
                o.setUnit_kerja(rst.getString("unit_kerja"));
                o.setNo_induk(rst.getString("no_induk"));
                o.setJenis_stiker(rst.getInt("jenis_stiker"));
                o.setHari_ke(rst.getString("hari_ke"));
                o.setJenis_langganan(rst.getString("jenis_langganan"));
                o.setExit_pass(rst.getInt("exit_pass"));
                o.setNo_kuitansi(rst.getString("no_kuitansi"));
                o.setTgl_edited(rst.getDate("tgl_edited"));
                o.setTipe_exit_pass(rst.getInt("tipe_exit_pass"));
                o.setSeq_code(rst.getString("seq_code"));
                o.setUnitno(rst.getString("unitno"));
                o.setArea(rst.getString("area"));
                o.setReserved(rst.getInt("reserved"));
                o.setCara_bayar(rst.getInt("cara_bayar"));   
                Date currentDate = new Date(System.currentTimeMillis());
                cal.setTime(o.getAkhir());
                cal.add(Calendar.MONTH, -1);
                Date billingLimitDate = cal.getTime();
                if(currentDate.before(billingLimitDate)){                                        
                    os.add(o);
                }
            }
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + os.size() + " rows found", os);
        }catch(SQLException ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }
        }        
    }
        
    
    public ProcessCallBack activeBillingList(){
        List<TransaksiStiker> os = new ArrayList();              
        TransaksiStiker o;
        Calendar cal = Calendar.getInstance();
        String strSQL = "SELECT * FROM transaksi_stiker WHERE cara_bayar=1 ORDER BY akhir ASC";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);            
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                o = new TransaksiStiker();
                o.setNotrans(rst.getString("notrans"));
                o.setNama(rst.getString("nama"));
                o.setAlamat(rst.getString("alamat"));
                o.setTelepon(rst.getString("telepon"));
                o.setJenis_transaksi(rst.getInt("jenis_transaksi"));
                o.setAwal(rst.getDate("awal"));                
                o.setHarga(rst.getDouble("harga"));
                o.setKeterangan((rst.getString("keterangan")));
                o.setTanggal(rst.getDate("tanggal"));
                o.setOperator(rst.getString("operator"));
                o.setAkhir(rst.getDate("akhir"));
                o.setMaks(rst.getInt("maks"));
                o.setNo_id(rst.getString("no_id"));
                o.setUnit_kerja(rst.getString("unit_kerja"));
                o.setNo_induk(rst.getString("no_induk"));
                o.setJenis_stiker(rst.getInt("jenis_stiker"));
                o.setHari_ke(rst.getString("hari_ke"));
                o.setJenis_langganan(rst.getString("jenis_langganan"));
                o.setExit_pass(rst.getInt("exit_pass"));
                o.setNo_kuitansi(rst.getString("no_kuitansi"));
                o.setTgl_edited(rst.getDate("tgl_edited"));
                o.setTipe_exit_pass(rst.getInt("tipe_exit_pass"));
                o.setSeq_code(rst.getString("seq_code"));
                o.setUnitno(rst.getString("unitno"));
                o.setArea(rst.getString("area"));
                o.setReserved(rst.getInt("reserved"));
                o.setCara_bayar(rst.getInt("cara_bayar"));   
                Date currentDate = new Date(System.currentTimeMillis());
                cal.setTime(o.getAkhir());
                cal.add(Calendar.MONTH, -1);
                Date billingLimitDate = cal.getTime();
                if(currentDate.before(billingLimitDate)){                                        
                    os.add(o);
                }
            }
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + os.size() + " rows found", os);
        }catch(SQLException ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
            }
        }        
    }    
}

