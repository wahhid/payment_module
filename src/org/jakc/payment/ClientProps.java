/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.payment;

import java.util.Properties;

/**
 *
 * @author wahhid
 */
public class ClientProps {


    public ClientProps(){

    }

    public Properties getProps(){
        Properties props = new Properties();

        //Common Properties
        props.setProperty("status", "Status");
        props.setProperty("createddate", "Created Date");
        props.setProperty("createdby", "Created By");
        props.setProperty("updateddate", "Updated Date");
        props.setProperty("updatedby", "Updated By");
        
        
        //Tarif Stiker Properties
        props.setProperty("jenis_langganan", "Member Type");
        props.setProperty("id_mobil", "Vehicle Type");
        props.setProperty("tarif", "Price");
        
        //Request Stiker
        
        props.setProperty("notrans", "Trans #");
        props.setProperty("tanggal", "Date");
        props.setProperty("nama", "Name");
        props.setProperty("no_id", "Stiker #");

        return props;
    }
}
