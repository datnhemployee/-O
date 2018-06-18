/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open acc template in acc editor.
 */
package Controller;

import Model.Account;
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
public class ControllerAccount {

    private SQLConnectivity conSQL = null;
    public static ControllerAccount Instance
            = new ControllerAccount();

    public static final String AccountIDFile
            = ReadTxt.currentFolderPath + "\\NextIDAccount.txt";
    public final static int defaultID = 0;

    private ControllerAccount() {
    }

    public void setConSQl(
            SQLConnectivity conSQL) {
        this.conSQL = conSQL;
    }

    public SQLConnectivity getConSQl() {
        return this.conSQL;
    }

    public ArrayList<Account> selectAll()
            throws Exception {
        String query = "SELECT * FROM ACCOUNT";
        PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
        ArrayList<Account> list = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        while (rs != null && rs.next()) {
            
            list.add(new Account(
                    rs.getInt("ACCOUNTID"),
                    rs.getInt("ACCOUNTTYPEID"),
                    rs.getString("URL")
            ));
        }
        stmt.close();
        return list;
    }

    public Account select(int id) throws Exception {
        ArrayList<Account> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAccountID() == id) {
                        return list.get(i);
                    }
                }
            }
        }
        return null;
    }
    
    public Account select_AccountTypeID(int id) throws Exception {
        ArrayList<Account> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAccountTypeID()== id) {
                        return list.get(i);
                    }
                }
            }
        }
        return null;
    }

    public void delete(int id) throws Exception {
        if (this.isExistID(id)) {
            String query = "DELETE FROM ACCOUNT "
                    + "WHERE ACCOUNTID = ? ";
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
                = ControllerID.Instance.getAll(AccountIDFile);
        if (deletedID == null) {
            deletedID = new ArrayList();
        }
        deletedID
                = ControllerID.Instance.addID(id, deletedID);
        ControllerID.Instance.update(deletedID, AccountIDFile);
    }

    public void remove_deletedID(int id) throws Exception {
        ArrayList<Integer> deletedID
                = ControllerID.Instance.getAll(AccountIDFile);
        if (deletedID != null) {
            if (!deletedID.isEmpty()) {
                deletedID.remove(new Integer(id));
                ControllerID.Instance.update(deletedID, AccountIDFile);
            }
        }

    }

    public void insert(Account acc) throws Exception {
        if (!this.isExistID(acc.getAccountID())) {
            String query = "INSERT INTO "
                    + "ACCOUNT(ACCOUNTID,ACCOUNTTYPEID,URL)"
                    + " VALUES (?,?,?)";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, acc.getAccountID());
            stmt.setInt(2, acc.getAccountTypeID());
            stmt.setString(3, acc.getURL());
            
            stmt.executeUpdate();
            stmt.close();

            remove_deletedID(acc.getAccountID());
        }
    }

    public ArrayList<Integer> getListID() throws Exception {
        ArrayList<Account> list = selectAll();
        ArrayList<Integer> listInt = new ArrayList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                listInt.add(list.get(i).getAccountID());
            }
        }
        return listInt;

    }

    public void update(Account acc) throws Exception {
        if (this.isExistID(acc.getAccountID())) {
            String query = "UPDATE ACCOUNT "
                    + "SET ACCOUNTTYPEID = ?,"
                    + "URL = ? "
                    + "WHERE ACCOUNTID = ? ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);

            stmt.setInt(1, acc.getAccountTypeID());
            stmt.setString(2, acc.getURL());
            stmt.setInt(3, acc.getAccountID());
            stmt.executeUpdate();

            stmt.close();

        }
    }

    public boolean isExistID(int id) throws Exception {
        ArrayList<Account> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAccountID()== id) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getMaxID() throws Exception {
        ArrayList<Account> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                int MAX = list.get(0).getAccountID();
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getAccountID() > MAX) {
                        MAX = list.get(i).getAccountID();
                    }
                }
                return MAX;
            }
        }
        return defaultID;
    }

    public int getNextID() throws Exception {
        int NextID = ControllerID.Instance.getNextDeletedID(AccountIDFile);
        if (NextID == -1) {
            return this.getMaxID() + 1;
        }
        return NextID;
    }

}
