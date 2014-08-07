/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.entity;

import java.util.Date;

/**
 *
 * @author wahhid
 */
public class Pegawai {
    private String nomer;
    private String nama;
    private String alamat;
    private Date tgl_lahir;
    private Date tgl_masuk;
    private String username;
    private String password;
    private byte[] foto;
    private int bisalogin;
    private int status;
    private String agama;
    private String telepon;
    private String alamat_lahir;
    private String alamat_sekarang;
    private String nip;
    private int statusabsen;
    private String level_pegawai;

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public Date getTgl_masuk() {
        return tgl_masuk;
    }

    public void setTgl_masuk(Date tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getBisalogin() {
        return bisalogin;
    }

    public void setBisalogin(int bisalogin) {
        this.bisalogin = bisalogin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat_lahir() {
        return alamat_lahir;
    }

    public void setAlamat_lahir(String alamat_lahir) {
        this.alamat_lahir = alamat_lahir;
    }

    public String getAlamat_sekarang() {
        return alamat_sekarang;
    }

    public void setAlamat_sekarang(String alamat_sekarang) {
        this.alamat_sekarang = alamat_sekarang;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public int getStatusabsen() {
        return statusabsen;
    }

    public void setStatusabsen(int statusabsen) {
        this.statusabsen = statusabsen;
    }

    public String getLevel_pegawai() {
        return level_pegawai;
    }

    public void setLevel_pegawai(String level_pegawai) {
        this.level_pegawai = level_pegawai;
    }
    
    
    
         
}
