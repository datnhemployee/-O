/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open bang template in bang editor.
 */
package Controller;

import Model.Bang;
import Model.Bang;
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
public class ControllerBang {

    private SQLConnectivity conSQL;

    public static final String BangIDFile
            = ReadTxt.currentFolderPath + "\\NextIDBang.txt";
    public final static int defaultID = 0;

    private ControllerBang() {
    }
    
    public ControllerBang(SQLConnectivity con){
        this.conSQL = con;
    }

    public void setConSQl(
            SQLConnectivity conSQL) {
        this.conSQL = conSQL;
    }

    public SQLConnectivity getConSQl() {
        return this.conSQL;
    }

    public ArrayList<Bang> selectAll()
            throws Exception {
        String query = "SELECT * FROM BANG";
        PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
        ArrayList<Bang> list = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        
        while (rs != null && rs.next()) {
            Date ngayBao = null;
            Date hanChot = null;
            if (rs.getDate("NGAYBAO_GANNHAT") != null) {
                ngayBao = new Date(rs.getDate("NGAYBAO_GANNHAT").getTime());
            }
            if (rs.getDate("NGAYBAO_GANNHAT") != null) {
                hanChot = new Date(rs.getDate("NGAYKETTHUC_XANHAT").getTime());
            }
            Bang bang =new Bang(
                    rs.getInt("BANGID"),
                    rs.getString("TEN"),
                    rs.getString("MOTA"),
                    ngayBao,
                    hanChot,
                    rs.getInt("SOTHEHOANTAT"),
                    rs.getInt("TONGSOTHE"),
                    rs.getInt("MAUID"),
                    rs.getInt("HINHID")
            );
            list.add(bang);
        }

        stmt.close();
        return list;
    }

    public Bang select(int id) throws Exception {
        ArrayList<Bang> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBangID() == id) {
                        return list.get(i);
                    }
                }
            }
        }
        return null;
    }

    public void delete(int id) throws Exception {
        if (this.isExistID(id)) {
            String query = "DELETE FROM BANG "
                    + "WHERE BANGID = ? ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            insert_deletedID(id);
            stmt.close();
        }
    }

    public void insert_deletedID(int id) throws Exception {
        ArrayList<Integer> deletedID
                = ControllerID.Instance.getAll(BangIDFile);
        if (deletedID == null) {
            deletedID = new ArrayList();
        }
        deletedID
                = ControllerID.Instance.addID(id, deletedID);
        ControllerID.Instance.update(deletedID, BangIDFile);
    }

    public void remove_deletedID(int id) throws Exception {
        ArrayList<Integer> deletedID
                = ControllerID.Instance.getAll(BangIDFile);
        if (deletedID != null) {
            if (!deletedID.isEmpty()) {
                deletedID.remove(new Integer(id));
                ControllerID.Instance.update(deletedID, BangIDFile);
            }
        }

    }

    public void insert(Bang bang) throws Exception {
        if (!this.isExistID(bang.getBangID())) {
            String query = "INSERT INTO "
                    + "BANG(BANGID,TEN,MOTA,"
                    + "MAUID,HINHID)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, bang.getBangID());
            stmt.setString(2, bang.getTen());
            stmt.setString(3, bang.getMoTa());
            stmt.setInt(4, bang.getMauID());
            stmt.setInt(5, bang.getHinhID());

            stmt.executeUpdate();

            stmt.close();

            remove_deletedID(bang.getBangID());
        }
    }

    public ArrayList<Integer> getListID() throws Exception {
        ArrayList<Bang> list = selectAll();
        ArrayList<Integer> listInt = new ArrayList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                listInt.add(list.get(i).getBangID());
            }
        }
        return listInt;

    }

    public void update(Bang bang) throws Exception {
        if (this.isExistID(bang.getBangID())) {
            String query = "UPDATE BANG "
                    + "SET TEN = ?, "
                    + "MOTA = ? , "
                    + "MAUID = ? , "
                    + "HINHID = ? "
                    + "WHERE BANGID = ? ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);

            stmt.setString(1, bang.getTen());
            stmt.setString(2, bang.getMoTa());
            stmt.setInt(3, bang.getMauID());
            stmt.setInt(4, bang.getHinhID());
            stmt.setInt(5, bang.getBangID());
            stmt.executeUpdate();

            stmt.close();

        }
    }

    public boolean isExistID(int id) throws Exception {
        ArrayList<Bang> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBangID() == id) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getMaxID() throws Exception {
        ArrayList<Bang> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                int MAX = list.get(0).getBangID();
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getBangID() > MAX) {
                        MAX = list.get(i).getBangID();
                    }
                }
                return MAX;
            }
        }
        return defaultID;
    }

    public int getNextID() throws Exception {
        int NextID = ControllerID.Instance.getNextDeletedID(BangIDFile);
        if (NextID == -1) {
            return this.getMaxID() + 1;
        }
        return NextID;
    }

    @Override
    public String toString() {
        return "ControllerBang{" + "conSQL=" + conSQL + '}';
    }

}
