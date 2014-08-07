/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jakc.payment;


import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultTreeModel;
import nabiladbv20.createPOSTGRESConnection;
import org.jakc.payment.db.controller.PegawaiController;
import org.jakc.payment.db.entity.Pegawai;
import org.jakc.payment.util.LoadConfiguration;
import org.openswing.swing.internationalization.java.EnglishOnlyResourceFactory;
import org.openswing.swing.mdi.client.ClientFacade;
import org.openswing.swing.mdi.client.MDIController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.permissions.client.LoginController;
import org.openswing.swing.permissions.client.LoginDialog;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.client.ClientUtils;

/**
 *
 * @author wahhid
 */
public class ClientApplication extends ClientUtils implements MDIController,LoginController {

    private createPOSTGRESConnection Conn;
    private Connection conn=null;

    private AppFacade appFacade = null;
    private ClientMenu cm = new ClientMenu();
    private ClientProps cp = new ClientProps();
    private Properties conf = new Properties();    
    protected String username=null;
    private PegawaiController userdbcontroller = null;
    private Auth auth;
    
    public ClientApplication(String argv[]){
        this.auth = new Auth();  
        this.conf = new LoadConfiguration().load();       
        if(this.conf == null){
            System.exit(1);
        }
        this.connectdb();
        this.userdbcontroller = new PegawaiController(this.conn);                
        ClientSettings clientSettings = new ClientSettings(
            new EnglishOnlyResourceFactory("$",cp.getProps(),true),
            new Hashtable()
        );
        
        ClientSettings.GRID_ACTIVE_CELL_BACKGROUND = new Color(205,239,255);
        ClientSettings.GRID_SELECTION_BACKGROUND = new Color(195,229,254);
        ClientSettings.FILTER_PANEL_ON_GRID=false;
        ClientSettings.ASK_BEFORE_CLOSE=true;
        ClientSettings.VIEW_BACKGROUND_SEL_COLOR = true;
        ClientSettings.VIEW_MANDATORY_SYMBOL = true;
        ClientSettings.ALLOW_OR_OPERATOR = false;
        ClientSettings.INCLUDE_IN_OPERATOR = false;
        ClientSettings.SHOW_SCROLLBARS_IN_MDI = false;
        ClientSettings.SHOW_PAGINATION_BUTTONS_ON_GRID=true;
        ClientSettings.SHOW_PAGE_NUMBER_IN_GRID=true;
       

        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.nilo.plaf.nimrod.NimRODLookAndFeel";
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.smart.SmartLookAndFeel";        
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";        
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"; 
        ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.aero.AeroLookAndFeel";        
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.hifi.HifiLookAndFeel";        
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.luna.LunaLookAndFeel";        
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";        
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.mint.MintLookAndFeel";        
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.noire.NoireLookAndFeel";        
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";        
        
        Properties props = new Properties();

        props.put("logoString", "Parking Payment Module v 1.1.10");
        props.put("backgroundColor", "232 232 232");
        String color =  "220 220 220";
        props.put("disabledBackgroundColor",color);
        props.put("systemTextFont","Arial PLAIN 14");
        props.put("controlTextFont","Arial PLAIN 14");
        props.put("menuTextFont","Arial PLAIN 14");
        props.put("userTextFont","Arial PLAIN 14");
        props.put("subTextFont","Arial PLAIN 14");
        try {
            try {
                try {
                    Class.forName(ClientSettings.LOOK_AND_FEEL_CLASS_NAME).getMethod("setCurrentTheme", new Class[]{Properties.class}).invoke(null, new Object[]{props});
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            UIManager.setLookAndFeel(ClientSettings.LOOK_AND_FEEL_CLASS_NAME);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }


        //MDIFrame mdi = new MDIFrame(this);
        LoginDialog d = new LoginDialog(
                              null,
                              false,
                              this,
                              "Authentication - Parking Payment Module v 1.1.10",
                              "Login",
                              'L',
                              "Exit",
                              'E',
                              null,
                              "Parking Payment Modules v 1.1.10",
                              null,
                              null,
                              "EN"
                            );
        
    }
       
    private void connectdb(){
        try{
            this.Conn = new createPOSTGRESConnection(this.conf.getProperty("server"),
                                                  this.conf.getProperty("port"),
                                                  this.conf.getProperty("database"),
                                                  this.conf.getProperty("user"),
                                                  this.conf.getProperty("password"));

    //        this.Conn = new createPOSTGRESConnection(conf.getProperty("dbserver"), 
    //                                                conf.getProperty("dbport"), 
    //                                                conf.getProperty("dbname"), 
    //                                                conf.getProperty("user"), 
    //                                                conf.getProperty("password"));
            this.Conn.openConnection();
            if(this.Conn.getErrStatus() == 1){
                 System.err.println(this.Conn.getErrMsg());
                 System.exit(1);
            }else{            
                this.conn = this.Conn.getConn();
            }            
        } catch (Exception ex){
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String argv[]){
        new ClientApplication(argv);
    }
    public void afterMDIcreation(MDIFrame frame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAboutImage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getAboutText() {
        return
        "Parking Payment Module Application\n"+
        "\n"+
        "Copyright: Copyright (C) 2013 Jakc.com\n"+
        "Author: Wahyu Hidayat\n"+
        "Release: 1.1.10\n\n";
    }

    public DefaultTreeModel getApplicationFunctions() {
        return cm.getMenu();
    }

    public ClientFacade getClientFacade() {
        return this.appFacade;
    }

    public int getExtendedState() {
        return JFrame.MAXIMIZED_BOTH;
    }

    public ArrayList getLanguages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getMDIFrameTitle() {
        return "Parking Payment Module v 1.1.10 - Jakc.com";
    }

    public void stopApplication() {
        System.exit(0);
    }

    public boolean viewChangeLanguageInMenuBar() {
        return false;
    }

    @Override
    public boolean viewFunctionsInMenuBar() {
        return true;
    }

    public boolean viewFunctionsInTreePanel() {
        return true;
    }

    public JDialog viewLoginDialog(JFrame parentFrame) {
        LoginDialog d = new LoginDialog(
                              null,
                              false,
                              this,
                              "Authentication - Parking Payment Module v 1.1.10",
                              "Login",
                              'L',
                              "Exit",
                              'E',
                              null,
                              "DEMO38",
                              null,
                              null,
                              "EN"
                            );
        return d;
    }

    public boolean viewLoginInMenuBar() {
        return true;
    }

    @Override
    public boolean viewOpenedWindowIcons() {
        return true;
    }

    @Override
    public boolean authenticateUser(Map loginInfo) throws Exception {
        boolean loginstatus=false;
        this.username = (String) loginInfo.get("username");
        String password = (String) loginInfo.get("password");
        if(username == null || password == null){
            loginstatus = false;
        }else{            
            //ADAuthenticator ada = new ADAuthenticator("tamananggrekmall.com","ldap://172.16.0.2:389","dc=tamananggrekmall,dc=com");
            Pegawai pegawai = this.userdbcontroller.checkuser(username, password);
            if(pegawai != null){
                if(pegawai.getBisalogin() == 1){
                    this.auth.setPegawai(pegawai);
                    this.appFacade = new AppFacade(conn,this.auth);                   
                    loginstatus = true;
                }                
            }else{
                System.out.println("User Not Found");
            }  
        }
        return loginstatus;
    }

    @Override
    public int getMaxAttempts() {
        return 3;
    }

    @Override
    public void loginSuccessful(Map loginInfo) {
        MDIFrame mdi = new MDIFrame(this);
    }

    @Override
    public boolean viewFileMenu() {
        return true;
    }

    
}
