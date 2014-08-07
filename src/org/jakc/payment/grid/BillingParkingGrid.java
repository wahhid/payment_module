/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.grid;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jakc.payment.os.vo.BillingParkingVo;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.QueryUtil;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author wahhid
 */
public class BillingParkingGrid extends GridController implements GridDataLocator {

    private Connection conn;
    private int pk;    
    
    
    public BillingParkingGrid(Connection conn){
        this.conn = conn;        
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try{
          ArrayList vals = new ArrayList();
          String strSQL="SELECT " +
                  "billing_parking.unitno," +
                  "billing_parking.date_trans," +
                  "billing_parking.description," +
                  "billing_parking.amount," +
                  "billing_parking.jenis_langganan," +
                  "billing_parking.awal," +
                  "billing_parking.akhir" +
                  " FROM billing_parking WHERE billing_parking.billing_id=" + this.pk;
          Map attribute2dbField = new HashMap();
          attribute2dbField.put("unitno","billing_parking.unitno");
          attribute2dbField.put("date_trans","billing_parking.date_trans");
          attribute2dbField.put("description","billing_parking.description");
          attribute2dbField.put("amount","billing_parking.amount");             
          attribute2dbField.put("jenis_langganan","billing_parking.jenis_langganan");    
          attribute2dbField.put("awal","billing_parking.awal");    
          attribute2dbField.put("akhir","billing_parking.akhir");    

          return QueryUtil.getQuery(
            conn,
            strSQL,
            new ArrayList(), // list of values linked to "?" parameters in sql
            attribute2dbField,
            BillingParkingVo.class, // v.o. to dinamically create for each row...
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

    
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
    
    
    
}
