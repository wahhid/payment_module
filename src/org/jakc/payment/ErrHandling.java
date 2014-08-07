/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment;

/**
 *
 * @author wahhid
 */
public class ErrHandling {

    protected String ClassName="";
    protected String MethodName="";
    protected int ErrStatus=0;
    protected String ErrMsg="";
    
    public ErrHandling(){
        
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getErrStatus() {
        return ErrStatus;
    }

    public void setErrStatus(int ErrStatus) {
        this.ErrStatus = ErrStatus;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String MethodName) {
        this.MethodName = MethodName;
    }
    
    
            
}
