/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.vo;

import org.jakc.payment.db.entity.*;
import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

/**
 *
 * @author wahhid
 */
public class RequestTransaksiStikerVo extends ValueObjectImpl {
    
    private String notrans;
    private String nama;
    private String no_id;
    private String unit_kerja;   
    private Date awal;
    private Date akhir;
    private double harga;
    private Date tanggal;
    private String adm;
    private int jenis_transaksi;
    private int cara_bayar;
    private String nopol;
    private String jenis_mobil;
    private String jenis_member;
    private int status;
    private String merk;
    private String tipe;
    private String tahun;
    private String warna;
    private Date tgl_approved;
    private String adm_approved;
    private int flag;
    private String remark;
    private int start_date_status;
    private int duration;
    private int approvedstatus;
    
    public String getNotrans() {
        return notrans;
    }

    public void setNotrans(String notrans) {
        this.notrans = notrans;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_id() {
        return no_id;
    }

    public void setNo_id(String no_id) {
        this.no_id = no_id;
    }

    public String getUnit_kerja() {
        return unit_kerja;
    }

    public void setUnit_kerja(String unit_kerja) {
        this.unit_kerja = unit_kerja;
    }

    public Date getAwal() {
        return awal;
    }

    public void setAwal(Date awal) {
        this.awal = awal;
    }

    public Date getAkhir() {
        return akhir;
    }

    public void setAkhir(Date akhir) {
        this.akhir = akhir;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    public int getJenis_transaksi() {
        return jenis_transaksi;
    }

    public void setJenis_transaksi(int jenis_transaksi) {
        this.jenis_transaksi = jenis_transaksi;
    }

    public int getCara_bayar() {
        return cara_bayar;
    }

    public void setCara_bayar(int cara_bayar) {
        this.cara_bayar = cara_bayar;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getJenis_mobil() {
        return jenis_mobil;
    }

    public void setJenis_mobil(String jenis_mobil) {
        this.jenis_mobil = jenis_mobil;
    }

    public String getJenis_member() {
        return jenis_member;
    }

    public void setJenis_member(String jenis_member) {
        this.jenis_member = jenis_member;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public Date getTgl_approved() {
        return tgl_approved;
    }

    public void setTgl_approved(Date tgl_approved) {
        this.tgl_approved = tgl_approved;
    }

    public String getAdm_approved() {
        return adm_approved;
    }

    public void setAdm_approved(String adm_approved) {
        this.adm_approved = adm_approved;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStart_date_status() {
        return start_date_status;
    }

    public void setStart_date_status(int start_date_status) {
        this.start_date_status = start_date_status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getApprovedstatus() {
        return approvedstatus;
    }

    public void setApprovedstatus(int approvedstatus) {
        this.approvedstatus = approvedstatus;
    }
 
    
    
}
