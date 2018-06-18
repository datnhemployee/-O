/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.The;
import Model.The;
import Model.The;
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
public class ControllerThe {

    private SQLConnectivity conSQL = null;

    public static final String TheIDFile
            = ReadTxt.currentFolderPath + "\\NextIDThe.txt";
    public final static int defaultID = 0;

    private ControllerThe() {
    }

    public ControllerThe(SQLConnectivity con) {
        this.conSQL = con;
    }

    public void setConSQl(
            SQLConnectivity conSQL) {
        this.conSQL = conSQL;
    }

    public SQLConnectivity getConSQl() {
        return this.conSQL;
    }

    public ArrayList<The> selectAll()
            throws Exception {

        String query = "SELECT * FROM THE";
        PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
        ArrayList<The> list = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        while (rs != null && rs.next()) {
            Date ngayBao = null;
            Date hanChot = null;
            if (rs.getDate("NGAYBAO_KETIEP") != null) {
                ngayBao = new Date(rs.getDate("NGAYBAO_KETIEP").getTime());
            }
            if (rs.getDate("HANCHOT") != null) {
                hanChot = new Date(rs.getDate("HANCHOT").getTime());
            }
            list.add(new The(
                    rs.getInt("THEID"),
                    rs.getInt("BANGID"),
                    rs.getString("TEN"),
                    ngayBao,
                    hanChot,
                    rs.getInt("TONGSOBUOC"),
                    rs.getInt("SOBUOCHOANTAT")
            ));
        }

        stmt.close();
        return list;
    }

    public The select(int id) throws Exception {
        ArrayList<The> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getTheID() == id) {
                        return list.get(i);
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<The> select_BangID(int id) throws Exception {
        ArrayList<The> rs = new ArrayList();
        ArrayList<The> list = selectAll();

        if (list != null) {
            if (!list.isEmpty()) {
                
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBangID() == id) {
                        rs.add(list.get(i));
                    }
                }
            }
        }
        return rs;
    }

    public void delete(int id) throws Exception {
        if (this.isExistID(id)) {
            String query = "DELETE FROM THE "
                    + "WHERE THEID = ? ";
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
                = ControllerID.Instance.getAll(TheIDFile);
        if (deletedID == null) {
            deletedID = new ArrayList();
        }
        deletedID
                = ControllerID.Instance.addID(id, deletedID);
        ControllerID.Instance.update(deletedID, TheIDFile);
    }

    public void remove_deletedID(int id) throws Exception {
        ArrayList<Integer> deletedID
                = ControllerID.Instance.getAll(TheIDFile);
        if (deletedID != null) {
            if (!deletedID.isEmpty()) {
                deletedID.remove(new Integer(id));
                ControllerID.Instance.update(deletedID, TheIDFile);
            }
        }

    }

    public void insert(The the) throws Exception {
        ControllerBang ctrlBang = new ControllerBang(this.getConSQl());
        ctrlBang.setConSQl(conSQL);
        if (!this.isExistID(the.getTheID())
                && ctrlBang.isExistID(the.getBangID())) {
            String query = "INSERT INTO "
                    + "THE(THEID,TEN,BANGID,"
                    + "HANCHOT)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, the.getTheID());
            stmt.setString(2, the.getTen());
            stmt.setInt(3, the.getBangID());
            if (the.getHanChot() != null) {
                stmt.setDate(4,
                        new java.sql.Date(the.getHanChot().getTime()));
            } else {
                stmt.setObject(4, null);
            }
            stmt.executeUpdate();

            stmt.close();

            remove_deletedID(the.getTheID());
        }
    }

    public ArrayList<Integer> getListID() throws Exception {
        ArrayList<The> list = selectAll();
        ArrayList<Integer> listInt = new ArrayList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                listInt.add(list.get(i).getTheID());
            }
        }
        return listInt;

    }

    public void update(The the) throws Exception {
        ControllerBang ctrlBang = new ControllerBang(this.getConSQl());
        ctrlBang.setConSQl(conSQL);
        if (this.isExistID(the.getTheID())
                && ctrlBang.isExistID(the.getBangID())) {
            String query = "UPDATE THE "
                    + "SET TEN = ?, "
                    + "BANGID = ? , "
                    + "HANCHOT = ? "
                    + "WHERE THEID = ? ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);

            stmt.setString(1, the.getTen());
            stmt.setInt(2, the.getBangID());
            if (the.getHanChot() != null) {
                stmt.setDate(3,
                        new java.sql.Date(the.getHanChot().getTime()));
            } else {
                stmt.setObject(3, null);
            }
            stmt.setInt(4, the.getTheID());
            stmt.executeUpdate();

            stmt.close();

        }
    }

    public boolean isExistID(int id) throws Exception {
        ArrayList<The> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getTheID() == id) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getMaxID() throws Exception {
        ArrayList<The> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                int MAX = list.get(0).getTheID();
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getTheID() > MAX) {
                        MAX = list.get(i).getTheID();
                    }
                }
                return MAX;
            }
        }
        return defaultID;
    }

    public int getNextID() throws Exception {
        int NextID = ControllerID.Instance.getNextDeletedID(TheIDFile);
        if (NextID == -1) {
            return this.getMaxID() + 1;
        }
        return NextID;
    }

}
