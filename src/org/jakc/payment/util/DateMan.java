/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author wahhid
 */
public class DateMan {
 
    public boolean isLastDate(Date date){
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         int lastdate = calendar.getActualMaximum(Calendar.DATE);
         calendar.set(Calendar.DATE,lastdate);
         if(calendar.getTime().equals(date)){
             return true;
         }else{
             return false;
         }
         
    }
}
