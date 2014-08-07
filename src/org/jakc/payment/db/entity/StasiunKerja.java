/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.entity;

/**
 *
 * @author wahhid
 */
public class StasiunKerja {
    private String kode;
    private String nama;
    private int margin;
    private String spv;
    private int target;
    private int status;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public String getSpv() {
        return spv;
    }

    public void setSpv(String spv) {
        this.spv = spv;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
