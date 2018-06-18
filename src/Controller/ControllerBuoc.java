/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open buoc template in buoc editor.
 */
package Controller;

import Model.Buoc;
import Model.Buoc;
import Model.Buoc;
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
public class ControllerBuoc {

    private SQLConnectivity conSQL = null;

    public static final String BuocIDFile
            = ReadTxt.currentFolderPath + "\\NextIDBuoc.txt";
    public final static int defaultID = 0;

    private ControllerBuoc() {
    }

    public ControllerBuoc(SQLConnectivity con) {
        this.conSQL = con;
    }

    public void setConSQl(
            SQLConnectivity conSQL) {
        this.conSQL = conSQL;
    }

    public SQLConnectivity getConSQl() {
        return this.conSQL;
    }

    public ArrayList<Buoc> selectAll()
            throws Exception {
        String query = "SELECT * FROM BUOC";
        PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
        ArrayList<Buoc> list = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        while (rs != null && rs.next()) {
            list.add(new Buoc(
                    rs.getInt("BUOCID"),
                    rs.getInt("THEID"),
                    rs.getString("NOIDUNG"),
                    rs.getBoolean("TRANGTHAI")
            ));
        }

        stmt.close();
        return list;
    }

    public Buoc select(int id) throws Exception {
        ArrayList<Buoc> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBuocID() == id) {
                        return list.get(i);
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Buoc> select_TheID(int id) throws Exception {
        ArrayList<Buoc> list = selectAll();
        ArrayList<Buoc> rs = new ArrayList();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getTheID() == id) {
                        rs.add(list.get(i));
                    }
                }
            }
        }
        return rs;
    }

    public void delete(int id) throws Exception {

        if (this.isExistID(id)) {
            String query = "DELETE FROM BUOC "
                    + "WHERE BUOCID = ? ";
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
                = ControllerID.Instance.getAll(BuocIDFile);
        if (deletedID == null) {
            deletedID = new ArrayList();
        }
        deletedID
                = ControllerID.Instance.addID(id, deletedID);
        ControllerID.Instance.update(deletedID, BuocIDFile);
    }

    public void remove_deletedID(int id) throws Exception {
        ArrayList<Integer> deletedID
                = ControllerID.Instance.getAll(BuocIDFile);
        if (deletedID != null) {
            if (!deletedID.isEmpty()) {
                deletedID.remove(new Integer(id));
                ControllerID.Instance.update(deletedID, BuocIDFile);
            }
        }

    }

    public void insert(Buoc buoc) throws Exception {
        ControllerThe ctrlThe = new ControllerThe(this.getConSQl());
        ctrlThe.setConSQl(conSQL);
        if (!this.isExistID(buoc.getBuocID())
                && ctrlThe.isExistID(buoc.getTheID())) {
            String query = "INSERT INTO "
                    + "BUOC(BUOCID,THEID,NOIDUNG,"
                    + "TRANGTHAI)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, buoc.getBuocID());
            stmt.setInt(2, buoc.getTheID());
            stmt.setString(3, buoc.getNoiDung());
            stmt.setBoolean(4, buoc.isTrangThaiBuoc());
            stmt.executeUpdate();

            stmt.close();

            remove_deletedID(buoc.getBuocID());
        }
    }

    public ArrayList<Integer> getListID() throws Exception {
        ArrayList<Buoc> list = selectAll();
        ArrayList<Integer> listInt = new ArrayList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                listInt.add(list.get(i).getBuocID());
            }
        }
        return listInt;

    }

    public void update(Buoc buoc) throws Exception {
        ControllerThe ctrlThe = new ControllerThe(this.getConSQl());
        ctrlThe.setConSQl(conSQL);
        if (this.isExistID(buoc.getBuocID())
                && ctrlThe.isExistID(buoc.getTheID())) {
            String query = "UPDATE BUOC "
                    + "SET NOIDUNG = ?, "
                    + "THEID = ? , "
                    + "TRANGTHAI = ? "
                    + "WHERE BUOCID = ? ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);

            stmt.setString(1, buoc.getNoiDung());
            stmt.setInt(2, buoc.getTheID());
            stmt.setBoolean(3, buoc.isTrangThaiBuoc());
            stmt.setInt(4, buoc.getBuocID());
            stmt.executeUpdate();

            stmt.close();

        }
    }

    public boolean isExistID(int id) throws Exception {
        ArrayList<Buoc> list = selectAll();

        if (list != null) {
            if (!list.isEmpty()) {

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBuocID() == id) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getMaxID() throws Exception {
        ArrayList<Buoc> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                int MAX = list.get(0).getBuocID();
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getBuocID() > MAX) {
                        MAX = list.get(i).getBuocID();
                    }
                }
                return MAX;
            }
        }
        return defaultID;
    }

    public int getNextID() throws Exception {
        int NextID = ControllerID.Instance.getNextDeletedID(BuocIDFile);
        if (NextID == -1) {
            return this.getMaxID() + 1;
        }
        return NextID;
    }

}
