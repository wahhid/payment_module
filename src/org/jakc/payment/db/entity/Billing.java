/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.entity;

/**
 *
 * @author wahhid
 */
public class Billing {

    private int billing_id;
    private int billing_year;
    private int billing_month;
    private int billing_status;

    public int getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(int billing_id) {
        this.billing_id = billing_id;
    }

    public int getBilling_year() {
        return billing_year;
    }

    public void setBilling_year(int billing_year) {
        this.billing_year = billing_year;
    }

    public int getBilling_month() {
        return billing_month;
    }

    public void setBilling_month(int billing_month) {
        this.billing_month = billing_month;
    }

    public int getBilling_status() {
        return billing_status;
    }

    public void setBilling_status(int billing_status) {
        this.billing_status = billing_status;
    }
    
    
    
}
