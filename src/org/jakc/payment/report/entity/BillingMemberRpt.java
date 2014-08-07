/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.report.entity;

import java.util.Date;

/**
 *
 * @author wahhid
 */
public class BillingMemberRpt {
    
    private String notrans;
    private String nama;
    private Date awal;
    private Date akhir;

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
    
    
            
}
