/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hinh;
import Tools.ReadTxt;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DAT
 */
public class ControllerHinh {

    public final static int defaultID = 0;

    private SQLConnectivity con;

    private ArrayList<Hinh> listHinh;
    private ArrayList<Integer> deletedID;

    public static final String HinhIDFile
            = ReadTxt.currentFolderPath + "\\NextIDHinh.txt";

    private ControllerHinh() {
    }

    public ControllerHinh(
            SQLConnectivity con) {
        this.con = con;
        listHinh = new ArrayList();
        deletedID = new ArrayList();
    }

    public SQLConnectivity getCon() {
        return con;
    }

    public void setCon(SQLConnectivity con) {
        this.con = con;
    }

    public Hinh getHinh(int id) throws Exception {
        if (listHinh.isEmpty()) {
            this.init();
            this.updateNewHinhDB();
        }
        for (Hinh e : this.listHinh) {
            if (e.getHinhID() == id) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Hinh> getListHinh() throws Exception {
        if (listHinh.isEmpty()) {
            this.init();
            this.updateNewHinhDB();
        }
        return this.listHinh;
    }

    public void init() throws Exception {
        try {
            ControllerID.Instance.init(HinhIDFile);
            this.selectAll();
            this.deletedID = ControllerID.Instance.getAll(HinhIDFile);
        } catch (Exception e) {
            throw new Exception("It is not able to init."
                    + "\n Message: " + e);
        }
    }

    public ArrayList<Integer> getDeleteID() {
        return deletedID;
    }

    public void insert(Hinh hinh) throws Exception {
        try {
            if (!isExist(hinh)
                    && ReadTxt.Instance.isFileExist(hinh.getPath())) {
//                System.out.printf("\n Inserting");
                insertToDB(hinh);
                insertToList(hinh);

                ControllerID.Instance.usedID(hinh.getHinhID(),
                        HinhIDFile,
                        deletedID);
            } else {
                throw new Exception("Hinh is exist.");
            }
        } catch (Exception e) {
            throw new Exception("Not able to insert Hinh."
                    + "\n Message: " + e);
        }
    }

    public void insertToList(Hinh hinh) {
        this.listHinh.add(hinh);
    }

    public void insertToDB(Hinh hinh) throws Exception {
        String query = "INSERT INTO HINH(HINHID,PATH)"
                + " VALUES(?,?)";
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);

        stmt.setInt(1, hinh.getHinhID());
        stmt.setString(2, hinh.getPath());

        stmt.executeUpdate();
        stmt.close();
    }

    public void update(Hinh hinh) throws Exception {
        try {
            if (isExist(hinh)
                    && ReadTxt.Instance.isFileExist(hinh.getPath())) {
                updateDB(hinh);
                updateList(hinh);
            } else {
                throw new Exception("Hinh is not exist.");
            }
        } catch (Exception e) {
            throw new Exception("Not able to update Hinh."
                    + "\n Message: " + e);
        }
    }

    public void updateList(Hinh hinh) {
        for (Hinh e : this.listHinh) {
            if (e.getHinhID() == hinh.getHinhID()) {
                e = hinh;
            }
        }
    }

    public void updateDB(Hinh hinh) throws Exception {
        String query = "UPDATE HINH SET "
                + " PATH = ? "
                + " WHERE HINHID = ?";
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);

        stmt.setInt(2, hinh.getHinhID());
        stmt.setString(1, hinh.getPath());

        stmt.executeUpdate();
        stmt.close();
    }

    public void delete(Hinh hinh) throws Exception {
        try {
            if (isExist(hinh)
                    && ReadTxt.Instance.isFileExist(hinh.getPath())) {
                deleteFromList(hinh);
                deleteFromDB(hinh);
                updateDeletedID(hinh.getHinhID());
            } else {
                throw new Exception("Hinh is not exist.");
            }
        } catch (Exception e) {
            throw new Exception("Not able to delete Hinh."
                    + "\n Message: " + e);
        }
    }

    protected void updateDeletedID(int id) throws Exception {
        deletedID = ControllerID.Instance.addID(id, deletedID);
        ControllerID.Instance.update(deletedID, HinhIDFile);

    }

    public void deleteFromList(Hinh hinh) {
        this.listHinh.remove(hinh);
    }

    public void deleteFromDB(Hinh hinh) throws Exception {
        String query = "DELETE FROM HINH "
                + " WHERE HINHID = ? AND"
                + " PATH = ?";
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);

        stmt.setInt(1, hinh.getHinhID());
        stmt.setString(2, hinh.getPath());

        stmt.executeUpdate();
        stmt.close();
    }

    protected Hinh select(int id) throws SQLException {
        return selectFromDB(id);
    }

    protected Hinh selectFromDB(int id) throws SQLException {
        String query = "SELECT * FROM HINH "
                + "WHERE HINHID = ?";
        Hinh hinh = null;
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs != null && rs.next()) {
            hinh = new Hinh(
                    rs.getInt("HINHID"),
                    rs.getString("PATH"));
        }

        stmt.close();
        return hinh;
    }

    protected ArrayList<Hinh> selectAll() throws Exception {
        try {
            this.listHinh.clear();
            this.selectAllFromDB();
            return this.listHinh;
        } catch (Exception e) {
            throw new Exception("Not able to delete Hinh."
                    + "\n Message: " + e);
        }
    }

    protected void selectAllFromDB() throws SQLException {
        String query = "SELECT * FROM HINH";
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);

        ResultSet rs = stmt.executeQuery();
        while (rs != null && rs.next()) {
            this.listHinh.add(
                    new Hinh(
                            rs.getInt("HINHID"),
                            rs.getString("PATH")));
        }

        stmt.close();
    }

    public int getMaxID() throws SQLException {
        int MaxID = defaultID;
        String query = "SELECT MAX(HINHID) FROM HINH";
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);

        ResultSet rs = stmt.executeQuery();
        if (rs != null && rs.next()) {
            MaxID = rs.getInt(1);
        }

        stmt.close();
        return MaxID;
    }

    public boolean isExist(Hinh hinh) throws Exception {
        try {
            if (this.getHinh(hinh.getHinhID())!= null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception("\nIt's impossible to check hinh"
                    + "\n Message: " + e);
        }
    }

    public boolean isExistID(int id) throws Exception {
        try {
            if (this.getHinh(id) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new Exception("\nIt's impossible to check hinh"
                    + "\n Message: " + e);
        }
    }

    public void updateNewHinhDB() throws Exception {
        String currentPath
                = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        try {

            for (Hinh e : this.listHinh) {

                this.updateDB(new Hinh(
                        e.getHinhID(),
                        currentPath
                        + e.getPath().substring(
                                e.getPath().lastIndexOf("\\src"),
                                e.getPath().lastIndexOf(".") + 4)
                ));
            }
        } catch (Exception e) {
            throw new Exception("There is some error in getting true path of "
                    + "user Background image."
                    + "\n Message: " + e);
        }
    }

    public int getNextID() throws Exception {
        int NextID = ControllerID.Instance.getNextDeletedID(HinhIDFile);
        if (NextID == -1) {
            return this.getMaxID() + 1;
        }
        return NextID;
    }
}
