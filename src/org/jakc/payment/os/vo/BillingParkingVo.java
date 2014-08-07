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
public class BillingParkingVo extends ValueObjectImpl{
    
    private String unitno;
    private Date date_trans;
    private String description;
    private int amount;
    private int billing_id;
    private String jenis_langganan;
    private Date awal;
    private Date akhir;

    public String getUnitno() {
        return unitno;
    }

    public void setUnitno(String unitno) {
        this.unitno = unitno;
    }

    public Date getDate_trans() {
        return date_trans;
    }

    public void setDate_trans(Date date_trans) {
        this.date_trans = date_trans;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(int billing_id) {
        this.billing_id = billing_id;
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

    public String getJenis_langganan() {
        return jenis_langganan;
    }

    public void setJenis_langganan(String jenis_langganan) {
        this.jenis_langganan = jenis_langganan;
    }
    
    
    
        
}
