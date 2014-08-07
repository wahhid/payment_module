/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.db.entity;

/**
 *
 * @author wahhid
 */
public class TarifStiker {
    private String id_mobil;
    private String jenis_langganan;
    private double tarif;

    public String getId_mobil() {
        return id_mobil;
    }

    public void setId_mobil(String id_mobil) {
        this.id_mobil = id_mobil;
    }

    public String getJenis_langganan() {
        return jenis_langganan;
    }

    public void setJenis_langganan(String jenis_langganan) {
        this.jenis_langganan = jenis_langganan;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }
    
    
}
