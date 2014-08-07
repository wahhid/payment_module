/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jakc.payment.Auth;
import org.jakc.payment.db.entity.TransaksiStiker;
import org.jakc.payment.os.gui.TransaksiStikerGridFrame;
import org.openswing.swing.mdi.client.MDIFrame;
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
public class TransaksiStikerGridController extends GridController implements GridDataLocator{

    private Connection conn;
    private Auth auth;
    private TransaksiStikerGridFrame frame;
    
    public TransaksiStikerGridController(Connection conn, Auth auth){
        this.conn = conn;
        this.auth = auth;
        this.frame = new TransaksiStikerGridFrame(this,this.conn);
        MDIFrame.add(frame);        
        
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try{
          ArrayList vals = new ArrayList();
          String strSQL="SELECT " +
                  "transaksi_stiker.notrans," +
                  "transaksi_stiker.nama" +
                  " FROM transaksi_stiker";
          Map attribute2dbField = new HashMap();
          attribute2dbField.put("notrans","transaksi_stiker.notrans");
          attribute2dbField.put("nama","transaksi_stiker.nama");

          return QueryUtil.getQuery(
            conn,
            strSQL,
            new ArrayList(), // list of values linked to "?" parameters in sql
            attribute2dbField,
            TransaksiStiker.class, // v.o. to dinamically create for each row...
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
            30, // pagination size...
            true // log query...
          );
        }
        catch (Exception ex) {
          ex.printStackTrace();
          return new ErrorResponse(ex.getMessage());
        }
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    
    
}
