/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.report.entity;


import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

/**
 *
 * @author wahhid
 */
public class BillingParkingRpt extends ValueObjectImpl{
    
    private String unit_no;
    private Date date_trans;
    private String description;
    private double amount;
    private int billing_id;

    public String getUnit_no() {
        return unit_no;
    }

    public void setUnit_no(String unit_no) {
        this.unit_no = unit_no;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(int billing_id) {
        this.billing_id = billing_id;
    }
    
        
}
