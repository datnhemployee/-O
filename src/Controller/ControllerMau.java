/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Mau;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Tools.ReadTxt;

/**
 *
 * @author DAT
 */
public class ControllerMau {
    
    public static final String MauIDFile
            = ReadTxt.currentFolderPath + "\\NextIDMau.txt";
    public final static int defaultID = 0;
    
    private SQLConnectivity con;
    private ArrayList<Mau> listMau;
    private ArrayList<Integer> deletedID;
    
    
    
    public SQLConnectivity getCon() {
        return con;
    }
    
    public void setCon(SQLConnectivity con) {
        this.con = con;
    }
    
    public ArrayList<Integer> getDeletedID() {
        return deletedID;
    }
    
    private ControllerMau() {
    }
    
    public ControllerMau(
            SQLConnectivity con) {
        this.con = con;
        listMau = new ArrayList();
        deletedID = new ArrayList();
    }
    
    public Mau getMau(int id) throws Exception {
        if (listMau.isEmpty()) {
            this.init();
        }
        return this.select(id);
    }
    
    public ArrayList<Mau> getListMau() throws Exception {
        if (listMau.isEmpty()) {
            this.init();
        }
        return listMau;
    }
    
    public void setListMau(ArrayList<Mau> listMau) {
        this.listMau = listMau;
    }
    
    public void insert(Mau mau) throws Exception {
        try {
            if (!this.isExistID(mau.getMauID())) {
                this.insertToArray(mau);
                this.insertToDB(mau);
                
                ControllerID.Instance.usedID(mau.getMauID(),
                        MauIDFile,
                        deletedID);
            } else {
                throw new Exception("\n Mau is existed.");
            }
        } catch (Exception e) {
            throw new Exception(""
                    + "\n It is impossible to insert new Mau."
                    + "\n Message: " + e);
        }
    }
    
    protected void insertToArray(Mau mau) throws Exception {
        
        this.listMau.add(mau);
    }
    
    protected void insertToDB(Mau mau) throws Exception {
        String query = "INSERT INTO MAU(MAUID,RED,GREEN,BLUE,ALPHA) "
                + "VALUES(?,?,?,?,?)";
        PreparedStatement stmt = this.con.getCon().prepareStatement(query);
        
        stmt.setInt(1, mau.getMauID());
        stmt.setInt(2, mau.getRed());
        stmt.setInt(3, mau.getGreen());
        stmt.setInt(4, mau.getBlue());
        stmt.setInt(5, mau.getAlpha());
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    public void delete(Mau mau) throws Exception {
        try {
            if (this.isExistID(mau.getMauID())) {
            this.deleteFromDB(mau);
            this.deleteFromList(mau);
            updateDeletedID(mau.getMauID());
            } else {
                throw new Exception("Mau is not existed.");
            }
        } catch (Exception e) {
            throw new Exception(""
                    + "\n It is impossible to delete the Mau."
                    + "\n Message: " + e);
        }
    }
    
    protected void deleteFromList(Mau mau) {
        this.listMau.remove(con);
    }
    
    protected void deleteFromDB(Mau mau) throws Exception {
        String query = "DELETE FROM MAU "
                + "WHERE MAUID = ?";
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);
        
        stmt.setInt(1, mau.getMauID());
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    protected void updateDeletedID(int id) throws Exception {
        deletedID = ControllerID.Instance.addID(id, deletedID);
        ControllerID.Instance.update(deletedID, MauIDFile);
    }
    
    public void update(Mau mau) throws Exception {
        try {
            if (this.isExistID(mau.getMauID())) {
                this.updateDB(mau);
                this.updateList(mau);
            } else {
                throw new Exception("Mau is not existed.");
            }
        } catch (Exception e) {
            throw new Exception(""
                    + "\n It is impossible to update Mau."
                    + "\n Message: " + e);
        }
    }
    
    protected void updateList(Mau mau) {
        for (Mau e : this.listMau) {
            if (e.getMauID() == mau.getMauID()) {
                e = mau;
            }
        }
    }
    
    protected void updateDB(Mau mau)
            throws Exception {
        String query = "UPDATE MAU "
                + "SET RED = ? ,"
                + "GREEN = ? ,"
                + "BLUE = ? ,"
                + "ALPHA = ? "
                + "WHERE MAUID = ?";
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);
        
        stmt.setInt(1, mau.getRed());
        stmt.setInt(2, mau.getGreen());
        stmt.setInt(3, mau.getBlue());
        stmt.setInt(4, mau.getAlpha());
        stmt.setInt(5, mau.getMauID());
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    public ArrayList<Mau> selectAll() throws Exception {
        try {
            this.listMau.clear();
            this.selectAllFromDB();
            return this.listMau;
        } catch (Exception e) {
            throw new Exception(""
                    + "\n It is impossible to select all Mau."
                    + "\n Message: " + e);
        }
    }
    
    protected void selectAllFromDB() throws Exception {
        String query = "SELECT * FROM MAU";
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);
        
        ResultSet rs = stmt.executeQuery();
        while ((rs != null) && rs.next()) {
            this.listMau.add(new Mau(
                    rs.getInt("MAUID"),
                    rs.getInt("RED"),
                    rs.getInt("GREEN"),
                    rs.getInt("BLUE"),
                    rs.getInt("ALPHA")
            ));
        }
        stmt.close();
    }
    
    protected Mau select(int id) throws SQLException {
        return selectFromDB(id);
    }

    protected Mau selectFromDB(int id) throws SQLException {
        String query = "SELECT * FROM MAU "
                + "WHERE MAUID = ?";
        Mau mau = null;
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs != null && rs.next()) {
            mau = new Mau(
                    rs.getInt("MAUID"),
                    rs.getInt("RED"),
                    rs.getInt("GREEN"),
                    rs.getInt("BLUE"),
                    rs.getInt("ALPHA")
            );
        }

        stmt.close();
        return mau;
    }
    
    public boolean isExist(Mau mau) throws Exception {
         try {
            return this.getMau(mau.getMauID()).equals(mau);
        } catch (Exception e) {
            throw new Exception("\nIt's impossible to check hinh"
                    + "\n Message: " + e);
        }
    }
    
    public boolean isExistID(int id) throws Exception {
        try {
            
            return this.getMau(id) != null;
        } catch (Exception e) {
            throw new Exception("\nIt's impossible to check hinh"
                    + "\n Message: " + e);
        }
    }
    
    public int getMaxID() throws Exception {
        String query = "SELECT MAX(MAUID) FROM MAU";
        int result = -1;
        PreparedStatement stmt
                = this.con.getCon().prepareStatement(query);
        
        ResultSet rs = stmt.executeQuery();
        if ((rs != null) && rs.next()) {
            result = rs.getInt(1);
        }
        stmt.close();
        return result;
    }
    
    public void init() throws Exception {
        try {
            ControllerID.Instance.init(MauIDFile);
            this.selectAll();
            
            this.deletedID = ControllerID.Instance.getAll(MauIDFile);
        } catch (Exception e) {
            throw new Exception("It is not able to init."
                    + "\n Message: " + e);
        }
    }
    
    public int getNextID() throws Exception {
        int NextID = ControllerID.Instance.getNextDeletedID(MauIDFile);
        if (NextID == -1) {
            return this.getMaxID() + 1;
        }
        return NextID;
    }
}
