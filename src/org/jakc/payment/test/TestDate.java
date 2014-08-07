/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wahhid
 */
public class TestDate {
 
    public static void main(String[] args){
//        try {
            //Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2013-07-01");            
            //System.out.println("Month : " + currentDate.getMonth());
            Calendar cal = Calendar.getInstance();
            Date currentDate = new Date(System.currentTimeMillis());
            cal.setTime(currentDate);
            cal.set(Calendar.DATE,1);
            System.out.println(cal.getTime());
//            cal.add(Calendar.MONTH, 2);
//            Date futureDate = cal.getTime();
//            System.out.println("Future : " + new SimpleDateFormat("yyyy-MM-dd").format(futureDate));
//        } catch (ParseException ex) {
//            System.out.println(ex.getMessage());
//        }
    }
}
