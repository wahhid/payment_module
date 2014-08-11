/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jakc.common.util.ProcessCallBack;
import org.jakc.payment.Auth;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.db.entity.BillingParking;
import org.jakc.payment.db.entity.RequestTransaksiStiker;
import org.jakc.payment.db.entity.TarifStiker;
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.os.gui.BillingGridFrame;
import org.jakc.payment.os.vo.BillingVo;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.QueryUtil;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author wahhid
 */
public class BillingGridController extends GridController implements GridDataLocator{

    private ProcessCallBack pcb;
    private Connection conn;
    private Auth auth;
    private BackingController bc;
    private BillingGridFrame frame;
    private BillingVo vo;
    
    
    
    public BillingGridController(Connection conn, Auth auth){
        this.conn = conn;
        this.auth = auth;
        this.bc = new BackingController(this.conn);
        this.frame = new BillingGridFrame(this);
        MDIFrame.add(this.frame);
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try{
          ArrayList vals = new ArrayList();
          String strSQL="SELECT " +
                  "billing.billing_id," +
                  "billing.billing_year," +
                  "billing.billing_month," +
                  "billing.billing_status" +
                  " FROM billing";
          
          Map attribute2dbField = new HashMap();
          attribute2dbField.put("billing_id","billing.billing_id");
          attribute2dbField.put("billing_year","billing.billing_year");
          attribute2dbField.put("billing_month","billing.billing_month");
          attribute2dbField.put("billing_status","billing.billing_status");

          return QueryUtil.getQuery(
            conn,
            strSQL,
            new ArrayList(), // list of values linked to "?" parameters in sql
            attribute2dbField,
            BillingVo.class, // v.o. to dinamically create for each row...
            "Y",
            "N",
            new GridParams(
              action,
              startIndex,
              filteredColumns,
              currentSortedColumns,
              currentSortedVersusColumns,
              new HashMap() // other params...
            ),
            25, // pagination size...
            true // log query...
          );
        }
        catch (Exception ex) {
          ex.printStackTrace();
          return new ErrorResponse(ex.getMessage());
        }
    }
   
    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Map attribute2dbField = new HashMap();        
        attribute2dbField.put("billing_year","billing_year");
        attribute2dbField.put("billing_month","billing_month");
        attribute2dbField.put("billing_status","billing_status");
        Response res = QueryUtil.insertTable(conn, newValueObjects, "billing", attribute2dbField, "Y", "N", true);        
        return res;
    }
    

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {        
        Map attribute2dbField = new HashMap();        
        attribute2dbField.put("billing_id","billing_id");
        attribute2dbField.put("billing_year","billing_year");
        attribute2dbField.put("billing_month","billing_month");
        attribute2dbField.put("billing_status","billing_status");
        HashSet pk = new HashSet();
        pk.add("billing_id");        
        Response res;
        BillingVo oldVo;
        BillingVo newVo;
        
        for(int i=0;i<persistentObjects.size();i++){
            oldVo = (BillingVo) oldPersistentObjects.get(i);
            newVo =  (BillingVo) persistentObjects.get(i);
            res = QueryUtil.updateTable(conn, pk, oldVo, newVo, "billing", attribute2dbField, "Y", "N", true);
            if(res.isError()){
                return new ErrorResponse("Error Update : " + oldVo.getBilling_id());
            }
        }
        return new VOListResponse(persistentObjects,false,persistentObjects.size());
    }
    
    @Override
    public void selectedCell(int rowNumber, int columnIndex, String attributedName, ValueObject persistentObject) {
        vo = (BillingVo) persistentObject;   
        this.frame.getGridController().setPk(vo.getBilling_id());
        this.frame.getGridControl2().reloadData();
    }
    
    
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public BackingController getBc() {
        return bc;
    }

    public void setBc(BackingController bc) {
        this.bc = bc;
    }

    public ProcessCallBack generateBilling() {                
        Calendar cal = Calendar.getInstance();
        TarifStiker ts;
        List<TransaksiStiker> os;
        try {
            //Delete All Billing
            System.out.println("Delete All Detail Billing Parking");
            this.bc.getBillingParkingController().deleteall(vo.getBilling_id());     
            System.out.println("Delete All Done!");
            
            //Get Stiker with cara_bayar equal 1
            System.out.println("Find Sticker with Billing Flag");
            this.pcb = this.bc.getTransaksiStikerController().billingList();
            if(this.pcb.isError()){
                System.out.println(this.pcb.getErrmsg());
                return this.pcb;
            }
            
            os = (List<TransaksiStiker>) this.pcb.getObject();                                                                        
            System.out.println("Number of Billing : " + os.size());          
            
            //Setup Billing Periode Date
            System.out.println("Setup Billing Periode Date"); 
            String year = Integer.toString(vo.getBilling_year());
            String month = Integer.toString(vo.getBilling_month());
            if(month.length() == 1){  
                month = "0" + month ;
            }
            String date = "01";                      
            //Date billingPeriode = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + date);
            Date billingPeriode = new SimpleDateFormat("yyyy-MM-dd").parse("2014-08-01");
            System.out.println("Billing Periode : " + billingPeriode);
            //Process Billing                        
            int billingProcessed = 0;                        
            for(TransaksiStiker o : os){
                this.pcb = this.bc.getRequestTransaksiStikerController().FetchLastRequest(o.getNotrans());                                
                if(!this.pcb.isError()){
                    RequestTransaksiStiker rts = (RequestTransaksiStiker) this.pcb.getObject();
                    System.out.println(rts.getNo_id() + "," + rts.getAwal() + "," + rts.getAkhir());
                }
            }
            for(TransaksiStiker o : os){  
                this.pcb = this.bc.getRequestTransaksiStikerController().FetchLastRequest(o.getNotrans());                                
                if(!this.pcb.isError()){                                                       
                    System.out.println("Process : " + o.getNotrans());
                    cal.setTime(o.getAkhir());                                
                    System.out.println("Cal getAkhir : " + cal.getTime());
                    int akhirYear = cal.get(Calendar.YEAR);
                    System.out.println("Akhir Year : " + akhirYear);
                    int akhirMonth = cal.get(Calendar.MONTH) + 1;
                    System.out.println("Akhir Month : " + akhirMonth);
                    int akhirDay = o.getAkhir().getDate();
                    System.out.println("Akhir Day : " + akhirDay);
                    if (akhirYear > vo.getBilling_year()){
                        akhirMonth = akhirMonth + 12;
                    }                
                    if((akhirMonth - vo.getBilling_month()) >= 2){
                        //Process Generate Billing if month difference above 2
                        System.out.println("Detected for Billing: " + o.getNotrans());
                        System.out.println("Generate Billing for " + o.getNotrans());
                        billingProcessed++;   

                        BillingParking bp = new BillingParking();                    
                        String jenis_langganan = o.getNotrans().substring(4);                                                                   
                        if(jenis_langganan.equals("1")){
                            jenis_langganan = "1st";
                        }
                        if(jenis_langganan.equals("2")){
                            jenis_langganan = "2nd";
                        }
                        if(jenis_langganan.equals("3")){
                            jenis_langganan = "3st";
                        }
                        if(jenis_langganan.equals("4")){
                            jenis_langganan = "4th";
                        }
                        if(jenis_langganan.equals("5")){
                            jenis_langganan = "5th";
                        }                    

                        cal.setTime(billingPeriode);
                        int currentYear = cal.get(Calendar.YEAR);
                        int currentMonth = cal.get(Calendar.MONTH) + 1;     

                        System.out.println("Int Current Month : " + currentMonth);

                        String strCurrentYear = Integer.toString(currentYear);
                        String strCurrentMonth = Integer.toString(currentMonth);
                        System.out.println("String Current Month : " + strCurrentMonth);
                        if(strCurrentMonth.length() == 1){
                            strCurrentMonth = "0" + strCurrentMonth;
                            System.out.println("String Current Month : " + strCurrentMonth);
                        }

                        String strFindLastDate = strCurrentYear + "-" + strCurrentMonth + "-01";
                        Date findLastDate = new SimpleDateFormat("yyyy-MM-dd").parse(strFindLastDate);                    
                        cal.setTime(findLastDate);
                        int maxDay = cal.getActualMaximum(Calendar.DATE);
                        System.out.println("Max Day  : "  + maxDay);
                        String strCurrentDay;
                        if(akhirDay > maxDay){
                            strCurrentDay = Integer.toString(maxDay);
                        }else{
                            strCurrentDay = Integer.toString(akhirDay);                        
                        }

                        if(strCurrentDay.length() == 1){
                                strCurrentDay = "0" + strCurrentDay;
                        }                    


                        String strReferenceDate = strCurrentYear + "-" + strCurrentMonth + "-" + strCurrentDay;
                        System.out.println("String Reference Date : " + strReferenceDate);                    
                        Date referenceDate = new SimpleDateFormat("yyyy-MM-dd").parse(strReferenceDate);                                            
                        System.out.println("Reference Date : " + referenceDate);                    

                        cal.setTime(referenceDate);

                        cal.add(Calendar.MONTH, 1);                    
                        Date awal = cal.getTime();
                        System.out.println("Awal Date : " + awal);                    

                        cal.add(Calendar.MONTH, 1);
                        Date akhir = cal.getTime();                                                
                        System.out.println("Akhir Date : " + akhir);

                        String description = "Contribution A (" + jenis_langganan + ") periode " + new SimpleDateFormat("dd/MM/yy").format(awal) + " - " +  new SimpleDateFormat("dd/MM/yy").format(akhir) + ")";                    

                        this.pcb = this.bc.getTarifStikerController().getByJenisLangganan(jenis_langganan);
                        if(this.pcb.isError()){
                            return this.pcb;
                        }else{
                            ts = (TarifStiker) this.pcb.getObject();
                            bp.setUnitno(o.getUnit_kerja());
                            bp.setDate_trans(new Date(System.currentTimeMillis()));                        
                            bp.setDescription(description);
                            bp.setAmount(ts.getTarif());
                            bp.setBilling_id(vo.getBilling_id());
                            bp.setJenis_langganan(jenis_langganan);
                            bp.setAwal(awal);                        
                            bp.setAkhir(akhir);                        
                            this.bc.getBillingParkingController().insert(bp);                                                       
                        }
                    }
                }else{
                    
                }                
            }
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + "Generate Billing Successfully", null);     
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }
    }        

    public void confirmBilling(){
        this.bc.getBillingController().CloseBilling(vo.getBilling_id());              
    }
    
    public BillingVo getVo() {
        return vo;
    }

    public ProcessCallBack transferBilling() {
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rst;
        String strSQL;
        List<BillingParking> os;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn = DriverManager.getConnection("jdbc:odbc:billing");   
            strSQL = "DELETE FROM billing_parking";            
            pstmt = conn.prepareStatement(strSQL);
            pstmt.execute();
            pstmt.close();
            System.out.println("Delete all data on MDB file");
            this.pcb = this.bc.getBillingParkingController().findByBillingId(this.vo.getBilling_id());
            if(this.pcb.isError()){
                return this.pcb;
            }else{
                os = (List<BillingParking>) this.pcb.getObject();
            }
            
            System.out.println("Number of Rows : " + os.size());
            strSQL = "INSERT INTO billing_parking(unitno,date_trans,description,amount) VALUES  (?,?,?,?)";
            int i = 0;
            for(BillingParking o : os){                                
                i++;
                pstmt = conn.prepareStatement(strSQL);
                pstmt.setString(1, o.getUnitno());
                pstmt.setDate(2, new java.sql.Date(o.getDate_trans().getTime()));
                pstmt.setString(3, o.getDescription());
                pstmt.setDouble(4, o.getAmount());
                pstmt.execute();     
                pstmt.close();
                System.out.println("Processed Row : " + i + " of " + os.size());
            }
            return new ProcessCallBack(false, this.getClass().getSimpleName() + " - " + "Transfer Billing Successfully", null);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return new ProcessCallBack(true, this.getClass().getSimpleName() + " - " + ex.getMessage(), null);
        }
    }
    
    private void insertIntoMDB(){
     
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    
    public ProcessCallBack processBilling(){
        try {
            //Define Periode           
            System.out.println("Setup Billing Periode Date"); 
            String year = Integer.toString(vo.getBilling_year());
            String month = Integer.toString(vo.getBilling_month());
            if(month.length() == 1){  
                month = "0" + month ;
            }
            String date = "01";                      
            Date billingPeriode = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + date);
            System.out.println("Billing Periode : " + billingPeriode);
            
            //Fetch Billing List
            this.pcb = this.fetchBillingList();
            if(this.pcb.isError()){
                return this.pcb;
            }  
            
            List<TransaksiStiker> os = (List<TransaksiStiker>) this.pcb.getObject();
            
            //Check Awal Date
            
            
            //Check Akhir Date
            
            
            //Add Billing to Billing Parking                                        
            return this.pcb;
        } catch (ParseException ex) {
            return new ProcessCallBack(true, ex.getMessage(), null);
        }        
    }
    
    private ProcessCallBack fetchBillingList(){
        return this.bc.getTransaksiStikerController().billingList();
    }
    
    
    
}