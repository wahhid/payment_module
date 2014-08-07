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
public class JenisMobil {
    
    private String id;
    private String nama;
    private Date tanggal;
    private String short_cut;
    private int orang;
    private int validasi;
    private int need_access;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getShort_cut() {
        return short_cut;
    }

    public void setShort_cut(String short_cut) {
        this.short_cut = short_cut;
    }

    public int getOrang() {
        return orang;
    }

    public void setOrang(int orang) {
        this.orang = orang;
    }

    public int getValidasi() {
        return validasi;
    }

    public void setValidasi(int validasi) {
        this.validasi = validasi;
    }

    public int getNeed_access() {
        return need_access;
    }

    public void setNeed_access(int need_access) {
        this.need_access = need_access;
    }            
        
}
