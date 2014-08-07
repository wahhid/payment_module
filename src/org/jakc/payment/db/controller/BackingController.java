/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.controller;

import java.sql.Connection;

/**
 *
 * @author wahhid
 */
public class BackingController {
    
        
    private TransaksiStikerController transaksiStikerController;
    private DetailTransaksiStikerController detailTransaksiStikerController;
    private StasiunKerjaController stasiunKerjaController;
    private RequestTransaksiStikerController requestTransaksiStikerController;
    private CaraBayarController caraBayarController;
    private JenisMobilController jenisMobilController;
    private TarifStikerController tarifStikerController;
    private JenisTransaksiController jenisTransaksiController;
    private BillingController billingController;
    private BillingParkingController billingParkingController;
    private MonthNameController monthNameController;
    
    public BackingController(Connection conn){
        this.transaksiStikerController = new TransaksiStikerController(conn);
        this.detailTransaksiStikerController = new DetailTransaksiStikerController(conn);
        this.stasiunKerjaController = new StasiunKerjaController(conn);
        this.requestTransaksiStikerController = new RequestTransaksiStikerController(conn);
        this.caraBayarController = new CaraBayarController(conn);
        this.jenisMobilController = new JenisMobilController(conn);
        this.tarifStikerController = new TarifStikerController(conn);
        this.jenisTransaksiController = new JenisTransaksiController(conn);
        this.billingController = new BillingController(conn);
        this.billingParkingController = new BillingParkingController(conn);
        this.monthNameController= new MonthNameController(conn);
    }

    public TransaksiStikerController getTransaksiStikerController() {
        return transaksiStikerController;
    }

    public DetailTransaksiStikerController getDetailTransaksiStikerController() {
        return detailTransaksiStikerController;
    }
        
    public StasiunKerjaController getStasiunKerjaController() {
        return stasiunKerjaController;
    }

    public RequestTransaksiStikerController getRequestTransaksiStikerController() {
        return requestTransaksiStikerController;
    }

    public CaraBayarController getCaraBayarController() {
        return caraBayarController;
    }

    public JenisMobilController getJenisMobilController() {
        return jenisMobilController;
    }

    public TarifStikerController getTarifStikerController() {
        return tarifStikerController;
    }

    public JenisTransaksiController getJenisTransaksiController() {
        return jenisTransaksiController;
    }

    public BillingController getBillingController() {
        return billingController;
    }

    public void setBillingController(BillingController billingController) {
        this.billingController = billingController;
    }

    public MonthNameController getMonthNameController() {
        return monthNameController;
    }

    public BillingParkingController getBillingParkingController() {
        return billingParkingController;
    }
    
    
    
                            
}
