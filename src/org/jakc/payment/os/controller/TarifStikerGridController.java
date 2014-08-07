/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakc.payment.os.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.jakc.payment.Auth;
import org.jakc.payment.db.controller.BackingController;
import org.jakc.payment.os.gui.TarifStikerGridFrame;
import org.jakc.payment.os.vo.TarifStikerVo;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.QueryUtil;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author wahhid
 */
public class TarifStikerGridController extends GridController implements GridDataLocator {

    private Connection conn;
    private Auth auth;
    private TarifStikerGridFrame frame;
    private BackingController bc;
    
    
    public TarifStikerGridController(Connection conn, Auth auth){
        this.conn = conn;        
        this.auth = auth;
        this.bc = new BackingController(this.conn);
        this.frame = new TarifStikerGridFrame(this,this.conn);
        MDIFrame.add(this.frame);        
    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try{
          ArrayList vals = new ArrayList();
          String strSQL="SELECT " +
                  "tarif_stiker.id_mobil," +
                  "tarif_stiker.jenis_langganan," +
                  "tarif_stiker.tarif" +
                  " FROM tarif_stiker";
          Map attribute2dbField = new HashMap();
          attribute2dbField.put("id_mobil","tarif_stiker.id_mobil");
          attribute2dbField.put("jenis_langganan","tarif_stiker.jenis_langganan");
          attribute2dbField.put("tarif","tarif_stiker.tarif");

          return QueryUtil.getQuery(
            conn,
            strSQL,
            new ArrayList(), // list of values linked to "?" parameters in sql
            attribute2dbField,
            TarifStikerVo.class, // v.o. to dinamically create for each row...
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
    

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Map attribute2dbField = new HashMap();        
        attribute2dbField.put("id_mobil","id_mobil");
        attribute2dbField.put("jenis_langganan","jenis_langganan");
        attribute2dbField.put("tarif","tarif");
        Response res = QueryUtil.insertTable(conn, newValueObjects, "tarif_stiker", attribute2dbField, "Y", "N", true);        
        return res;
    }        

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        Map attribute2dbField = new HashMap();        
        attribute2dbField.put("id_mobil","id_mobil");
        attribute2dbField.put("jenis_langganan","jenis_langganan");
        attribute2dbField.put("tarif","tarif");
        HashSet pk = new HashSet();
        pk.add("jenis_langganan");        
        Response res;
        TarifStikerVo oldVo;
        TarifStikerVo newVo;
        
        for(int i=0;i<persistentObjects.size();i++){
            oldVo = (TarifStikerVo) oldPersistentObjects.get(i);
            newVo =  (TarifStikerVo) persistentObjects.get(i);
            res = QueryUtil.updateTable(conn, pk, oldVo, newVo, "tarif_stiker", attribute2dbField, "Y", "N", true);
            if(res.isError()){
                return new ErrorResponse("Error Update : " + oldVo.getJenis_langganan());
            }
        }
        return new VOListResponse(persistentObjects,false,persistentObjects.size());        
    }
    public BackingController getBc() {
        return bc;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    
    
}
