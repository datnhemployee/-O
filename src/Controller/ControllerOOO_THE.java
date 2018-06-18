/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open ooo template in ooo editor.
 */
package Controller;

import Model.OoO;
import Model.OoO_The;
import Model.The;
import Tools.ReadTxt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author DAT
 */
public class ControllerOOO_THE {

    private SQLConnectivity conSQL = null;
    private ControllerThe ctrlThe;
    private ControllerOOO ctrlOOO;

    private ControllerOOO_THE() {
    }

    public ControllerOOO_THE(SQLConnectivity con) {
        this.conSQL = con;
        ctrlThe = new ControllerThe(conSQL);
        ctrlOOO = new ControllerOOO(conSQL);

    }

    public void setConSQl(
            SQLConnectivity conSQL) {
        this.conSQL = conSQL;
        ctrlThe = new ControllerThe(conSQL);
        ctrlOOO = new ControllerOOO(conSQL);
    }

    public SQLConnectivity getConSQl() {
        return this.conSQL;
    }

    public ArrayList<OoO_The> selectAll()
            throws Exception {
        String query = "SELECT * FROM OOO_THE";
        PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
        ArrayList<OoO_The> list = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        while (rs != null && rs.next()) {
            list.add(new OoO_The(
                    rs.getInt("OOOID"),
                    rs.getInt("THEID")
            ));
        }

        stmt.close();
        return list;
    }

    public ArrayList<OoO_The> select_OOOID(int id) throws Exception {
        ArrayList<OoO_The> list = selectAll();
        ArrayList<OoO_The> rs = new ArrayList();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getOOOID() == id) {
                        rs.add(list.get(i));
                    }
                }
            }
        }
        return rs;
    }

    public ArrayList<OoO_The> select_THEID(int id) throws Exception {
        ArrayList<OoO_The> list = selectAll();
        ArrayList<OoO_The> rs = new ArrayList();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getTHEID() == id) {
                        rs.add(list.get(i));
                    }
                }
            }
        }
        return rs;
    }

    public void delete(OoO_The ooo) throws Exception {
        ControllerThe ctrlThe = new ControllerThe(this.getConSQl());
        ControllerOOO ctrlOOO = new ControllerOOO(this.getConSQl());

        ctrlThe.setConSQl(conSQL);
        ctrlOOO.setConSQl(conSQL);
        if (ctrlThe.isExistID(ooo.getTHEID())
                && ctrlOOO.isExistID(ooo.getOOOID())
                && this.isExistID(ooo)) {
            String query = "DELETE FROM OOO_THE "
                    + "WHERE OOOID = ? AND "
                    + "THEID = ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, ooo.getOOOID());
            stmt.setInt(2, ooo.getTHEID());

            stmt.executeUpdate();
            insert_deletedID(ooo.getOOOID(), ooo.getTHEID());
            stmt.close();
        } else {
        }
    }

    public void insert_deletedID(int OOOID, int THEID) throws Exception {
        ControllerThe ctrlThe = new ControllerThe(this.getConSQl());
        ControllerOOO ctrlOOO = new ControllerOOO(this.getConSQl());

        ctrlThe.setConSQl(conSQL);
        ctrlOOO.setConSQl(conSQL);

        ctrlThe.insert_deletedID(THEID);
        ctrlOOO.insert_deletedID(OOOID);
    }

    public void remove_deletedID(int OOOID, int THEID) throws Exception {
        ControllerThe ctrlThe = new ControllerThe(this.getConSQl());
        ControllerOOO ctrlOOO = new ControllerOOO(this.getConSQl());

        ctrlThe.setConSQl(conSQL);
        ctrlOOO.setConSQl(conSQL);

        ctrlThe.remove_deletedID(THEID);
        ctrlOOO.remove_deletedID(OOOID);
    }

    public void insert(OoO_The ooo) throws Exception {
        ControllerThe ctrlThe = new ControllerThe(this.getConSQl());
        ControllerOOO ctrlOOO = new ControllerOOO(this.getConSQl());

        ctrlThe.setConSQl(conSQL);
        ctrlOOO.setConSQl(conSQL);
        if (ctrlThe.isExistID(ooo.getTHEID())
                && ctrlOOO.isExistID(ooo.getOOOID())
                && !this.isExistID(ooo)) {
            String query = "INSERT INTO "
                    + "OOO_THE(OOOID,THEID)"
                    + " VALUES (?,?)";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, ooo.getOOOID());
            stmt.setInt(2, ooo.getTHEID());

            stmt.executeUpdate();
            stmt.close();

            remove_deletedID(ooo.getOOOID(), ooo.getTHEID());
        }
    }

    public ArrayList<The> select(Date NgayBao) throws Exception {
        ArrayList<The> rs = new ArrayList();
        ArrayList<OoO> listOOO = ctrlOOO.select(NgayBao);
        System.out.println("List OOO =" + listOOO.toString());
        System.out.println("List OOO =" 
                + new SimpleDateFormat("dd-MM-yyyy")
                        .format(NgayBao));
        for (int i = 0; i < listOOO.size(); i++) {
            for (OoO_The e : this.select_OOOID(
                    listOOO.get(i).getOOOID())) {
                if (!rs.contains(e)) {
                    rs.add(ctrlThe.select(e.getTHEID()));
                }
            }
        }
        return rs;
    }

    public boolean isExistID(OoO_The ot) throws Exception {
        ArrayList<OoO_The> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getOOOID() == ot.getOOOID()
                            && list.get(i).getTHEID() == ot.getTHEID()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
