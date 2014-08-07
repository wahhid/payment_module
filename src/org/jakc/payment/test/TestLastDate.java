/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.test;

import java.util.Calendar;

/**
 *
 * @author wahhid
 */
public class TestLastDate {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int year = 2009;
        int month = Calendar.FEBRUARY;
        int date = 1;

        calendar.set(year, month, date);        
        
        int lastDate = calendar.getActualMaximum(Calendar.DATE);

        calendar.set(Calendar.DATE, lastDate);
        
        int lastDay = calendar.get(Calendar.DAY_OF_WEEK);

        System.out.println("Last Date: " + calendar.getTime());

        System.out.println("Last Day : " + lastDay);
    }
}
