/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.payment;



import java.sql.Connection;
import org.jakc.payment.os.controller.ApprovalFormController;
import org.jakc.payment.os.controller.BillingGridController;
import org.jakc.payment.os.controller.RequestTransaksiStikerGridController;
import org.jakc.payment.os.controller.TarifStikerGridController;
import org.jakc.payment.report.controller.DailyPaymentFormController;
import org.jakc.payment.report.controller.MonthlyBillingMemberReportController;
import org.jakc.payment.report.controller.MonthlyBillingReportController;
import org.jakc.payment.report.controller.MonthlyPaymentFormController;
import org.openswing.swing.mdi.client.ClientFacade;


/**
 *
 * @author wahhid
 */
public class AppFacade implements ClientFacade {
            
    private Connection conn;
    private Auth auth;
    
    public AppFacade(Connection conn,Auth auth){
        this.conn = conn;
        this.auth = auth;
    }
    
    public void getRequestTransaksiStiker(){
        new RequestTransaksiStikerGridController(this.conn,this.auth);
    }
    
    public void getTarifStiker(){
        new TarifStikerGridController(this.conn,this.auth);
    }
   
    public void getBilling(){
        new BillingGridController(this.conn,this.auth);
    }
   
    public void getApproval(){
        new ApprovalFormController(conn, auth);
    }
    
    public void getDailyPayment(){
        new DailyPaymentFormController(this.conn,this.auth);
    }
    
    public void getMonthlyPayment(){
        new MonthlyPaymentFormController(this.conn,this.auth);
    }    
    
    public void getMonthlyBilling(){
        new MonthlyBillingReportController(this.conn);
    }
    
    public void getMonthlyBillingMember(){
        new MonthlyBillingMemberReportController(this.conn);
    }
}

