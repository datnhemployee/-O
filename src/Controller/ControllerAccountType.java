/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open acc template in acc editor.
 */
package Controller;

import Model.AccountType;
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
public class ControllerAccountType {

    private SQLConnectivity conSQL = null;
    public static ControllerAccountType Instance
            = new ControllerAccountType();

    public static final String AccountTypeIDFile
            = ReadTxt.currentFolderPath + "\\NextIDAccountType.txt";
    public final static int defaultID = 0;

    private ControllerAccountType() {
    }

    public void setConSQl(
            SQLConnectivity conSQL) {
        this.conSQL = conSQL;
    }

    public SQLConnectivity getConSQl() {
        return this.conSQL;
    }

    public ArrayList<AccountType> selectAll()
            throws Exception {
        String query = "SELECT * FROM ACCOUNTTYPE";
        PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
        ArrayList<AccountType> list = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        while (rs != null && rs.next()) {
            
            list.add(new AccountType(
                    rs.getInt("ACCOUNTTYPETYPEID"),
                    rs.getString("NAME")
            ));
        }
        stmt.close();
        return list;
    }

    public AccountType select(int id) throws Exception {
        ArrayList<AccountType> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAccountTypeID() == id) {
                        return list.get(i);
                    }
                }
            }
        }
        return null;
    }
    
    public void delete(int id) throws Exception {
        if (this.isExistID(id)) {
            String query = "DELETE FROM ACCOUNTTYPE "
                    + "WHERE ACCOUNTTYPEID = ? ";
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
                = ControllerID.Instance.getAll(AccountTypeIDFile);
        if (deletedID == null) {
            deletedID = new ArrayList();
        }
        deletedID
                = ControllerID.Instance.addID(id, deletedID);
        ControllerID.Instance.update(deletedID, AccountTypeIDFile);
    }

    public void remove_deletedID(int id) throws Exception {
        ArrayList<Integer> deletedID
                = ControllerID.Instance.getAll(AccountTypeIDFile);
        if (deletedID != null) {
            if (!deletedID.isEmpty()) {
                deletedID.remove(new Integer(id));
                ControllerID.Instance.update(deletedID, AccountTypeIDFile);
            }
        }

    }

    public void insert(AccountType acc) throws Exception {
        if (!this.isExistID(acc.getAccountTypeID())) {
            String query = "INSERT INTO "
                    + "ACCOUNTTYPE(ACCOUNTTYPEID,NAME)"
                    + " VALUES (?,?,?)";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, acc.getAccountTypeID());
            stmt.setString(2, acc.getName());
            
            stmt.executeUpdate();
            stmt.close();

            remove_deletedID(acc.getAccountTypeID());
        }
    }

    public ArrayList<Integer> getListID() throws Exception {
        ArrayList<AccountType> list = selectAll();
        ArrayList<Integer> listInt = new ArrayList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                listInt.add(list.get(i).getAccountTypeID());
            }
        }
        return listInt;

    }

    public void update(AccountType acc) throws Exception {
        if (this.isExistID(acc.getAccountTypeID())) {
            String query = "UPDATE ACCOUNTTYPE "
                    + "SET NAME = ?,"
                    + "WHERE ACCOUNTTYPEID = ? ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);

            stmt.setString(1, acc.getName());
            stmt.setInt(2, acc.getAccountTypeID());
            stmt.executeUpdate();

            stmt.close();

        }
    }

    public boolean isExistID(int id) throws Exception {
        ArrayList<AccountType> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAccountTypeID()== id) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getMaxID() throws Exception {
        ArrayList<AccountType> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                int MAX = list.get(0).getAccountTypeID();
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getAccountTypeID() > MAX) {
                        MAX = list.get(i).getAccountTypeID();
                    }
                }
                return MAX;
            }
        }
        return defaultID;
    }

    public int getNextID() throws Exception {
        int NextID = ControllerID.Instance.getNextDeletedID(AccountTypeIDFile);
        if (NextID == -1) {
            return this.getMaxID() + 1;
        }
        return NextID;
    }

}
