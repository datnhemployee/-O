/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open ooo template in ooo editor.
 */
package Controller;

import Model.OoO;
import Tools.ReadTxt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author DAT
 */
public class ControllerOOO {

    private SQLConnectivity conSQL = null;

    public static final String OoOIDFile
            = ReadTxt.currentFolderPath + "\\NextIDOoO.txt";
    public final static int defaultID = 0;

    private ControllerOOO() {
    }

    public ControllerOOO(SQLConnectivity conSQL) {
        this.conSQL = conSQL;
    }

    public void setConSQl(
            SQLConnectivity conSQL) {
        this.conSQL = conSQL;
    }

    public SQLConnectivity getConSQl() {
        return this.conSQL;
    }

    public ArrayList<OoO> selectAll()
            throws Exception {
        String query = "SELECT * FROM OOO";
        PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
        ArrayList<OoO> list = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        while (rs != null && rs.next()) {
            Date d = null;
            if (rs.getDate("NGAYBAO") != null) {
                d = new Date(rs.getDate("NGAYBAO").getTime());
            }
            list.add(new OoO(
                    rs.getInt("OOOID"),
                    d
            ));
        }

        stmt.close();
        return list;
    }

    public OoO select(int id) throws Exception {
        ArrayList<OoO> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getOOOID() == id) {
                        return list.get(i);
                    }
                }
            }
        }
        return null;
    }

    public void delete(int id) throws Exception {
        if (this.isExistID(id)) {
            String query = "DELETE FROM OOO "
                    + "WHERE OOOID = ? ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            insert_deletedID(id);
            stmt.close();
        } else {
        }
    }

    public void insert_deletedID(int id) throws Exception {
        ArrayList<Integer> deletedID
                = ControllerID.Instance.getAll(OoOIDFile);
        if (deletedID == null) {
            deletedID = new ArrayList();
        }
        deletedID
                = ControllerID.Instance.addID(id, deletedID);
        ControllerID.Instance.update(deletedID, OoOIDFile);
    }

    public void remove_deletedID(int id) throws Exception {
        ArrayList<Integer> deletedID
                = ControllerID.Instance.getAll(OoOIDFile);
        if (deletedID != null) {
            if (!deletedID.isEmpty()) {
                deletedID.remove(new Integer(id));
                ControllerID.Instance.update(deletedID, OoOIDFile);
            }
        }

    }

    public void insert(OoO ooo) throws Exception {
        ControllerThe ctrlThe = new ControllerThe(this.getConSQl());
        ctrlThe.setConSQl(conSQL);
        if (!this.isExistID(ooo.getOOOID())) {
            String query = "INSERT INTO "
                    + "OOO(OOOID,NGAYBAO)"
                    + " VALUES (?,?)";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, ooo.getOOOID());
            if (ooo.getNgayBao() != null) {
                stmt.setDate(
                        2,
                        new java.sql.Date(ooo.getNgayBao().getTime()));
            } else {
                stmt.setObject(2, null);
            }

            stmt.executeUpdate();
            stmt.close();

            remove_deletedID(ooo.getOOOID());
        }
    }

    public ArrayList<Integer> getListID() throws Exception {
        ArrayList<OoO> list = selectAll();
        ArrayList<Integer> listInt = new ArrayList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                listInt.add(list.get(i).getOOOID());
            }
        }
        return listInt;

    }

    public void update(OoO ooo) throws Exception {
        ControllerThe ctrlThe = new ControllerThe(this.getConSQl());
        ctrlThe.setConSQl(conSQL);
        if (this.isExistID(ooo.getOOOID())) {
            String query = "UPDATE OOO "
                    + "SET NGAYBAO = ?"
                    + "WHERE OOOID = ? ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);

            if (ooo.getNgayBao() != null) {
                stmt.setDate(1,
                        new java.sql.Date(ooo.getNgayBao().getTime()));
            } else {
                stmt.setObject(1, null);
            }
            stmt.setInt(2, ooo.getOOOID());
            stmt.executeUpdate();

            stmt.close();

        }
    }

    public ArrayList<OoO> select(Date date) throws Exception {
        ArrayList<OoO> rs = new ArrayList();
        ArrayList<OoO> list = selectAll();

        System.out.println("listOOO All =" + list.toString());
        for (int i = 0; i < list.size(); i++) {
            Period p = Tools.TimeTool.getPeriodOfTwo(
                    list.get(i).getNgayBao(), date);
            if (p.getDays() >= 0 && p.getMonths() >= 0
                    && p.getYears() >= 0) {
                rs.add(list.get(i));
            };
        }
        return rs;
    }

    public boolean isExistID(int id) throws Exception {
        ArrayList<OoO> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getOOOID() == id) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isExistDate(Date date) throws Exception {
        ArrayList<OoO> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getNgayBao().equals(date)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getMaxID() throws Exception {
        ArrayList<OoO> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                int MAX = list.get(0).getOOOID();
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getOOOID() > MAX) {
                        MAX = list.get(i).getOOOID();
                    }
                }
                return MAX;
            }
        }
        return defaultID;
    }

    public int getNextID() throws Exception {
        int NextID = ControllerID.Instance.getNextDeletedID(OoOIDFile);
        if (NextID == -1) {
            return this.getMaxID() + 1;
        }
        return NextID;
    }

}
