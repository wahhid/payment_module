/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.payment;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.openswing.swing.tree.java.OpenSwingTreeNode;

/**
 *
 * @author wahhid
 */
public class ClientMenu  {

    public ClientMenu(){

    }

    public DefaultTreeModel getMenu(){
        
        DefaultMutableTreeNode root = new OpenSwingTreeNode();
        DefaultTreeModel model = new DefaultTreeModel(root);

        ApplicationFunction n1 = new ApplicationFunction("Master",null);        
        ApplicationFunction n11 = new ApplicationFunction("Price List","tarifstiker",null,"getTarifStiker");
        
        ApplicationFunction n2 = new ApplicationFunction("Transaction",null);
        ApplicationFunction n21 = new ApplicationFunction("Subscription","find",null,"getRequestTransaksiStiker");        
        ApplicationFunction n22 = new ApplicationFunction("Billing Period","billing",null,"getBilling");
        ApplicationFunction n23 = new ApplicationFunction("Approval","approval",null,"getApproval");
        
        ApplicationFunction n3 = new ApplicationFunction("Report",null);
        ApplicationFunction n31 = new ApplicationFunction("Daily Payment","dailypayment",null,"getDailyPayment");                       
        ApplicationFunction n32 = new ApplicationFunction("Monthly Payment","monthlypayment",null,"getMonthlyPayment");                       
        ApplicationFunction n33 = new ApplicationFunction("Monthly Billing","monthlybilling",null,"getMonthlyBilling");                       
        
        n1.add(n11);                    
        n2.add(n21);
        n2.add(n22); 
        n2.add(n23);
        
        n3.add(n31);
        n3.add(n32);
        n3.add(n33);        
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        
        return model;
    }
}
