/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author wahhid
 */
public class LoadConfiguration {
    
    private Properties conf;
    private HashMap data;
    
    public LoadConfiguration(){
        
    }
    
    public Properties load(){
        try{
            this.conf = new Properties();
            File file = new File("config.properties");                        
            if(file.exists()){      
                FileInputStream fis = new FileInputStream("config.properties");
                conf.load(fis);           
                if(Integer.parseInt(this.conf.getProperty("update")) == 1){
                    ConfigurationDialog dialog = new ConfigurationDialog(null, true);
                    this.data = new HashMap();
                    this.data.put("server",this.conf.getProperty("server"));
                    this.data.put("port",this.conf.getProperty("port"));
                    this.data.put("database",this.conf.getProperty("database"));
                    this.data.put("user",this.conf.getProperty("user"));
                    this.data.put("password","");                                        
                    dialog.setData(data);
                    dialog.show();                            
                    if(dialog.getStatus() == 1){
                        //Save Configuration
                        FileOutputStream fos = new FileOutputStream("config.properties");
                        this.conf.setProperty("server", (String)this.data.get("server"));
                        this.conf.setProperty("port", (String)this.data.get("port"));
                        this.conf.setProperty("database", (String)this.data.get("database"));
                        this.conf.setProperty("user", (String)this.data.get("user"));
                        this.conf.setProperty("password", (String)this.data.get("password"));
                        this.conf.setProperty("update", "0");
                        this.conf.store(fos, null);
                    }
                }
            }else{
                file.createNewFile();
                FileInputStream fis = new FileInputStream("config.properties");
                conf.load(fis);
                ConfigurationDialog dialog = new ConfigurationDialog(null, true);
                this.data = new HashMap();
                dialog.setData(data);
                dialog.show();                            
                if(dialog.getStatus() == 1){
                    //Save Configuration
                    FileOutputStream fos = new FileOutputStream("config.properties");
                    this.conf.setProperty("server", (String)this.data.get("server"));
                    this.conf.setProperty("port", (String)this.data.get("port"));
                    this.conf.setProperty("database", (String)this.data.get("database"));
                    this.conf.setProperty("user", (String)this.data.get("user"));
                    this.conf.setProperty("password", (String)this.data.get("password"));
                    this.conf.setProperty("update", "0");
                    this.conf.store(fos, null);
                }                                
            }             
            return conf;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
