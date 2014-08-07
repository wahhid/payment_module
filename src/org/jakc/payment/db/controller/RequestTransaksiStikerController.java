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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.db.entity.RequestTransaksiStiker;
import org.jakc.payment.report.entity.RequestTransaksiStikerRpt;

/**
 *
 * @author wahhid
 */
public class RequestTransaksiStikerController {
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rst ;
     
    
    public RequestTransaksiStikerController(Connection conn){
        this.conn = conn;
    }

    public ProcessCallBack insert(RequestTransaksiStiker o){
        String strSQL = "INSERT INTO request_transaksi_stiker VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, o.getNotrans());
            this.pstmt.setString(2, o.getNama());
            this.pstmt.setString(3, o.getNo_id());
            this.pstmt.setString(4, o.getUnit_kerja());
            this.pstmt.setDate(5, new java.sql.Date(o.getAwal().getTime()));
            this.pstmt.setDate(6, new java.sql.Date(o.getAkhir().getTime()));
            this.pstmt.setDouble(7, o.getHarga());
            this.pstmt.setDate(8, new java.sql.Date(new Date(System.currentTimeMillis()).getTime()));
            this.pstmt.setString(9, o.getAdm());
            this.pstmt.setInt(10, o.getJenis_transaksi());
            this.pstmt.setInt(11, o.getCara_bayar());
            this.pstmt.setString(12, o.getNopol());
            this.pstmt.setString(13, o.getJenis_mobil());
            this.pstmt.setString(14, o.getJenis_member());
            this.pstmt.setInt(15, o.getStatus());
            this.pstmt.setString(16, o.getMerk());
            this.pstmt.setString(17, o.getTipe());
            this.pstmt.setString(18, o.getTahun());
            this.pstmt.setString(19, o.getWarna());            
            this.pstmt.setDate(20, null);        
            this.pstmt.setString(21, o.getAdm_approved());
            this.pstmt.setInt(22, o.getFlag());
            this.pstmt.setString(23, o.getRemark());
            this.pstmt.setInt(24, o.getStart_date_status());
            this.pstmt.setInt(25, o.getDuration());     
            this.pstmt.setInt(26, o.getApprovedstatus());
            this.pstmt.executeUpdate();                               
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Insert successfully", o);                                    
        }catch(Exception ex){
            ex.printStackTrace();           
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }            
        }
    }
    public ProcessCallBack update(RequestTransaksiStiker o){
        String strSQL = "UPDATE request_transaksi_stiker SET "
                + "notrans=?,"
                + "nama=?,"
                + "no_id=?,"
                + "unit_kerja=?,"
                + "awal=?,"
                + "akhir=?,"
                + "harga=?,"
                + "tanggal=?,"
                + "adm=?,"
                + "jenis_transaksi=?,"
                + "cara_bayar=?,"
                + "nopol=?,"
                + "jenis_mobil=?,"
                + "jenis_member=?,"
                + "status=?,"
                + "merk=?,"
                + "tipe=?,"
                + "tahun=?,"
                + "warna=?,"
                + "tgl_approved=?,"
                + "adm_approved=?,"
                + "flag=?,"
                + "remark=?,"
                + "start_date_status=?,"
                + "duration=?,"
                + "approvedstatus=?"
                + " WHERE notrans=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setString(1, o.getNotrans());
            this.pstmt.setString(2, o.getNama());
            this.pstmt.setString(3, o.getNo_id());
            this.pstmt.setString(4, o.getUnit_kerja());
            this.pstmt.setDate(5, new java.sql.Date(o.getAwal().getTime()));
            this.pstmt.setDate(6, new java.sql.Date(o.getAkhir().getTime()));
            this.pstmt.setDouble(7, o.getHarga());
            this.pstmt.setDate(8, new java.sql.Date(o.getTanggal().getTime()));
            this.pstmt.setString(9, o.getAdm());
            this.pstmt.setInt(10, o.getJenis_transaksi());
            this.pstmt.setInt(11, o.getCara_bayar());
            this.pstmt.setString(12, o.getNopol());
            this.pstmt.setString(13, o.getJenis_mobil());
            this.pstmt.setString(14, o.getJenis_member());
            this.pstmt.setInt(15, o.getStatus());
            this.pstmt.setString(16, o.getMerk());
            this.pstmt.setString(17, o.getTipe());
            this.pstmt.setString(18, o.getTahun());
            this.pstmt.setString(19, o.getWarna());            
            this.pstmt.setDate(20, null);
            this.pstmt.setString(21, o.getAdm_approved());            
            this.pstmt.setInt(22, o.getFlag());
            this.pstmt.setString(23, o.getRemark());
            this.pstmt.setInt(24, o.getStart_date_status());
            this.pstmt.setInt(25, o.getDuration());
            this.pstmt.setInt(26, o.getApprovedstatus());
            this.pstmt.setString(27, o.getNotrans());
            this.pstmt.executeUpdate();                    
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Update succesfully", o);                        
        }catch(Exception ex){           
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
        }
    }
    
    public ProcessCallBack delete(String notrans){
        String strSQL = "UPDATE request_transaksi_stiker SET "
                + "status=?"
                + " WHERE notrans=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setInt(1, 0);
            this.pstmt.setString(2, notrans);
            this.pstmt.executeUpdate();                    
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Delete successfully", notrans);                        
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                                    
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
        }
    }    
    public ProcessCallBack approved(String notrans,String adm_approved){
        String strSQL = "UPDATE request_transaksi_stiker SET "
                + "tgl_approved=?,"
                + "adm_approved=?,"
                + "approvedstatus=?"
                + " WHERE notrans=?";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setDate(1, new java.sql.Date(new Date(System.currentTimeMillis()).getTime()));
            this.pstmt.setString(2, adm_approved);
            this.pstmt.setInt(3, 1);
            this.pstmt.setString(4, notrans);
            this.pstmt.executeUpdate();                    
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Approved successfully", notrans);                        
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
        }        
    }
    
    
    public ProcessCallBack getByNoTrans(String notrans){
        RequestTransaksiStiker o = new RequestTransaksiStiker();
        String strSQL = "SELECT * FROM request_transaksi_stiker WHERE notrans=?";        
        try {
            this.pstmt = conn.prepareStatement(strSQL);
            this.pstmt.setString(1, notrans);
            rst = this.pstmt.executeQuery();
            rst.next();
            if(rst.getRow() == 1){
                o.setNotrans(rst.getString("notrans"));
                o.setNama(rst.getString("nama"));
                o.setNo_id(rst.getString("no_id"));
                o.setUnit_kerja(rst.getString("unit_kerja"));
                o.setAwal(rst.getDate("awal"));
                o.setAkhir(rst.getDate("akhir"));
                o.setHarga(rst.getDouble("harga"));
                o.setTanggal(rst.getDate("tanggal"));
                o.setAdm(rst.getString("adm"));
                o.setJenis_transaksi(rst.getInt("jenis_transaksi"));
                o.setCara_bayar(rst.getInt("cara_bayar"));
                o.setNopol(rst.getString("nopol"));
                o.setJenis_mobil(rst.getString("jenis_mobil"));
                o.setJenis_member(rst.getString("jenis_member"));
                o.setStatus(rst.getInt("status"));
                o.setMerk(rst.getString("merk"));
                o.setTipe(rst.getString("tipe"));
                o.setTahun(rst.getString("tahun"));
                o.setWarna(rst.getString("warna"));
                o.setTgl_approved(rst.getDate("tgl_approved"));
                o.setAdm_approved(rst.getString("adm_approved"));
                o.setFlag(rst.getInt("flag"));
                o.setRemark(rst.getString("remark"));
                o.setStart_date_status(rst.getInt("start_date_status"));
                o.setDuration(rst.getInt("duration"));
                o.setApprovedstatus(rst.getInt("approvedstatus"));
                return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Data found", o);                        
            }else{
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + "Data not found", null);                        
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();                
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }            
        }
    }

    public ProcessCallBack FetchLastRequest(String no_id){
        RequestTransaksiStiker o = new RequestTransaksiStiker();
        String strSQL = "SELECT * FROM request_transaksi_stiker WHERE no_id=? ORDER BY notrans DESC LIMIT 1";        
        try {
            this.pstmt = conn.prepareStatement(strSQL);
            this.pstmt.setString(1, no_id);
            rst = this.pstmt.executeQuery();
            rst.next();
            if(rst.getRow() == 1){
                o.setNotrans(rst.getString("notrans"));
                o.setNama(rst.getString("nama"));
                o.setNo_id(rst.getString("no_id"));
                o.setUnit_kerja(rst.getString("unit_kerja"));
                o.setAwal(rst.getDate("awal"));
                o.setAkhir(rst.getDate("akhir"));
                o.setHarga(rst.getDouble("harga"));
                o.setTanggal(rst.getDate("tanggal"));
                o.setAdm(rst.getString("adm"));
                o.setJenis_transaksi(rst.getInt("jenis_transaksi"));
                o.setCara_bayar(rst.getInt("cara_bayar"));
                o.setNopol(rst.getString("nopol"));
                o.setJenis_mobil(rst.getString("jenis_mobil"));
                o.setJenis_member(rst.getString("jenis_member"));
                o.setStatus(rst.getInt("status"));
                o.setMerk(rst.getString("merk"));
                o.setTipe(rst.getString("tipe"));
                o.setTahun(rst.getString("tahun"));
                o.setWarna(rst.getString("warna"));
                o.setTgl_approved(rst.getDate("tgl_approved"));
                o.setAdm_approved(rst.getString("adm_approved"));
                o.setFlag(rst.getInt("flag"));
                o.setRemark(rst.getString("remark"));
                o.setStart_date_status(rst.getInt("start_date_status"));
                o.setDuration(rst.getInt("duration"));
                o.setApprovedstatus(rst.getInt("approvedstatus"));
                return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "Data found", o);                        
            }else{
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + "Data not found", null);                        
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();                
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }            
        }        
    }
    
    public ProcessCallBack ForDailyReport(Date tanggal, int approvedstatus){
        String strSQL;
        List<RequestTransaksiStiker> os = new ArrayList();            
        strSQL = "SELECT * FROM request_transaksi_stiker WHERE tanggal=? AND approvedstatus=? ORDER BY jenis_transaksi";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setDate(1, new java.sql.Date(tanggal.getTime()));
            this.pstmt.setInt(2, approvedstatus);
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                RequestTransaksiStiker o = new RequestTransaksiStiker();
                o.setNotrans(rst.getString("notrans"));
                o.setNama(rst.getString("nama"));
                o.setNo_id(rst.getString("no_id"));
                o.setUnit_kerja(rst.getString("unit_kerja"));
                o.setAwal(rst.getDate("awal"));
                o.setAkhir(rst.getDate("akhir"));
                o.setHarga(rst.getDouble("harga"));
                o.setTanggal(rst.getDate("tanggal"));
                o.setAdm(rst.getString("adm"));
                o.setJenis_transaksi(rst.getInt("jenis_transaksi"));
                o.setCara_bayar(rst.getInt("cara_bayar"));
                o.setNopol(rst.getString("nopol"));
                o.setJenis_mobil(rst.getString("jenis_mobil"));
                o.setJenis_member(rst.getString("jenis_member"));
                o.setStatus(rst.getInt("status"));
                o.setMerk(rst.getString("merk"));
                o.setTipe(rst.getString("tipe"));
                o.setTahun(rst.getString("tahun"));
                o.setWarna(rst.getString("warna"));
                o.setTgl_approved(rst.getDate("tgl_approved"));
                o.setAdm_approved(rst.getString("adm_approved"));
                o.setFlag(rst.getInt("flag"));
                o.setRemark(rst.getString("remark"));
                o.setStart_date_status(rst.getInt("start_date_status"));
                o.setDuration(rst.getInt("duration"));
                o.setApprovedstatus(rst.getInt("approvedstatus"));
                os.add(o);
            }            
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "List found", os);                        
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
        }
    }    

    public ProcessCallBack ForDailyReport(Date starttanggal, Date endtanggal, int approvedstatus) {
        String strSQL;
        List<RequestTransaksiStiker> os = new ArrayList();            
        strSQL = "SELECT * FROM request_transaksi_stiker WHERE tanggal between ? AND ? AND approvedstatus=? ORDER BY tanggal,jenis_transaksi";
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setDate(1, new java.sql.Date(starttanggal.getTime()));
            this.pstmt.setDate(2, new java.sql.Date(endtanggal.getTime()));
            this.pstmt.setInt(3, approvedstatus);
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                RequestTransaksiStiker o = new RequestTransaksiStiker();
                o.setNotrans(rst.getString("notrans"));
                o.setNama(rst.getString("nama"));
                o.setNo_id(rst.getString("no_id"));
                o.setUnit_kerja(rst.getString("unit_kerja"));
                o.setAwal(rst.getDate("awal"));
                o.setAkhir(rst.getDate("akhir"));
                o.setHarga(rst.getDouble("harga"));
                o.setTanggal(rst.getDate("tanggal"));
                o.setAdm(rst.getString("adm"));
                o.setJenis_transaksi(rst.getInt("jenis_transaksi"));
                o.setCara_bayar(rst.getInt("cara_bayar"));
                o.setNopol(rst.getString("nopol"));
                o.setJenis_mobil(rst.getString("jenis_mobil"));
                o.setJenis_member(rst.getString("jenis_member"));
                o.setStatus(rst.getInt("status"));
                o.setMerk(rst.getString("merk"));
                o.setTipe(rst.getString("tipe"));
                o.setTahun(rst.getString("tahun"));
                o.setWarna(rst.getString("warna"));
                o.setTgl_approved(rst.getDate("tgl_approved"));
                o.setAdm_approved(rst.getString("adm_approved"));
                o.setFlag(rst.getInt("flag"));
                o.setRemark(rst.getString("remark"));
                o.setStart_date_status(rst.getInt("start_date_status"));
                o.setDuration(rst.getInt("duration"));
                o.setApprovedstatus(rst.getInt("approvedstatus"));
                os.add(o);
            }            
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "List found", os);                        
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
        }
    }

    public ProcessCallBack ForDailyReport(Date starttanggal, Date endtanggal,int billingstatus, int approvedstatus) {
        String strSQL;
        List<RequestTransaksiStiker> os = new ArrayList();            
        if(billingstatus == -1){
            strSQL = "SELECT * FROM request_transaksi_stiker WHERE tanggal between ? AND ? AND approvedstatus=? ORDER BY tanggal,jenis_transaksi";
        }else{
            strSQL = "SELECT * FROM request_transaksi_stiker WHERE tanggal between ? AND ? AND cara_bayar=? AND approvedstatus=? ORDER BY tanggal,jenis_transaksi";            
        }
        
        try{
            this.pstmt = this.conn.prepareStatement(strSQL);
            this.pstmt.setDate(1, new java.sql.Date(starttanggal.getTime()));
            this.pstmt.setDate(2, new java.sql.Date(endtanggal.getTime()));
            if(billingstatus == -1){                
                this.pstmt.setInt(3, approvedstatus);
            }else{
                this.pstmt.setInt(3, billingstatus);
                this.pstmt.setInt(4, approvedstatus);   
            }
            
            this.rst = this.pstmt.executeQuery();
            while(rst.next()){
                RequestTransaksiStiker o = new RequestTransaksiStiker();
                o.setNotrans(rst.getString("notrans"));
                o.setNama(rst.getString("nama"));
                o.setNo_id(rst.getString("no_id"));
                o.setUnit_kerja(rst.getString("unit_kerja"));
                o.setAwal(rst.getDate("awal"));
                o.setAkhir(rst.getDate("akhir"));
                o.setHarga(rst.getDouble("harga"));
                o.setTanggal(rst.getDate("tanggal"));
                o.setAdm(rst.getString("adm"));
                o.setJenis_transaksi(rst.getInt("jenis_transaksi"));
                o.setCara_bayar(rst.getInt("cara_bayar"));
                o.setNopol(rst.getString("nopol"));
                o.setJenis_mobil(rst.getString("jenis_mobil"));
                o.setJenis_member(rst.getString("jenis_member"));
                o.setStatus(rst.getInt("status"));
                o.setMerk(rst.getString("merk"));
                o.setTipe(rst.getString("tipe"));
                o.setTahun(rst.getString("tahun"));
                o.setWarna(rst.getString("warna"));
                o.setTgl_approved(rst.getDate("tgl_approved"));
                o.setAdm_approved(rst.getString("adm_approved"));
                o.setFlag(rst.getInt("flag"));
                o.setRemark(rst.getString("remark"));
                o.setStart_date_status(rst.getInt("start_date_status"));
                o.setDuration(rst.getInt("duration"));
                o.setApprovedstatus(rst.getInt("approvedstatus"));
                os.add(o);
            }            
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - "  + "List found", os);                        
        }catch(Exception ex){
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
        }finally{
            try {
                this.rst.close();
                this.pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ProcessCallBack(true, this.getClass().getSimpleName() + " - "  + ex.getMessage(), null);                        
            }
        }
    }    
    
}
