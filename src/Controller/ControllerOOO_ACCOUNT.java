/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open ooo template in ooo editor.
 */
package Controller;

import Model.OoO_Account;
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
public class ControllerOOO_ACCOUNT {

    private SQLConnectivity conSQL = null;
    public static ControllerOOO_ACCOUNT Instance
            = new ControllerOOO_ACCOUNT();

    private ControllerOOO_ACCOUNT() {
    }

    public void setConSQl(
            SQLConnectivity conSQL) {
        this.conSQL = conSQL;
    }

    public SQLConnectivity getConSQl() {
        return this.conSQL;
    }

    public ArrayList<OoO_Account> selectAll()
            throws Exception {
        String query = "SELECT * FROM OOO_ACCOUNT";
        PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
        ArrayList<OoO_Account> list = new ArrayList();

        ResultSet rs = stmt.executeQuery();
        while (rs != null && rs.next()) {
            list.add(new OoO_Account(
                    rs.getInt("OOOID"),
                    rs.getInt("ACCOUNTID")
            ));
        }

        stmt.close();
        return list;
    }

    public OoO_Account select_OOOID(int id) throws Exception {
        ArrayList<OoO_Account> list = selectAll();
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

    public OoO_Account select_ACCOUNTID(int id) throws Exception {
        ArrayList<OoO_Account> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getACCOUNTID() == id) {
                        return list.get(i);
                    }
                }
            }
        }
        return null;
    }

    public void delete(OoO_Account ooo) throws Exception {
        ControllerAccount.Instance.setConSQl(conSQL);
        ControllerOOO ctrlOOO = new ControllerOOO(conSQL);
        if (ControllerAccount.Instance.isExistID(ooo.getACCOUNTID())
                && ctrlOOO.isExistID(ooo.getOOOID())
                && this.isExistID(ooo)) {
            String query = "DELETE FROM OOO_ACCOUNT "
                    + "WHERE OOOID = ? AND "
                    + "ACCOUNTID = ";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, ooo.getOOOID());
            stmt.setInt(2, ooo.getACCOUNTID());

            stmt.executeUpdate();
            insert_deletedID(ooo.getOOOID(), ooo.getACCOUNTID());
            stmt.close();
        } else {
        }
    }

    public void insert_deletedID(int OOOID, int ACCOUNTID) throws Exception {
        ControllerOOO ctrlOOO = new ControllerOOO(conSQL);
        ControllerAccount.Instance.setConSQl(conSQL);
        ctrlOOO.setConSQl(conSQL);

        ControllerAccount.Instance.insert_deletedID(ACCOUNTID);
        ctrlOOO.insert_deletedID(OOOID);
    }

    public void remove_deletedID(int OOOID, int ACCOUNTID) throws Exception {
        ControllerOOO ctrlOOO = new ControllerOOO(conSQL);
        ControllerAccount.Instance.setConSQl(conSQL);
        ctrlOOO.setConSQl(conSQL);

        ControllerAccount.Instance.remove_deletedID(ACCOUNTID);
        ctrlOOO.remove_deletedID(OOOID);
    }

    public void insert(OoO_Account ooo) throws Exception {
        ControllerAccount.Instance.setConSQl(conSQL);
        ControllerOOO ctrlOOO = new ControllerOOO(conSQL);
        if (ControllerAccount.Instance.isExistID(ooo.getACCOUNTID())
                && ctrlOOO.isExistID(ooo.getOOOID())
                && this.isExistID(ooo)) {
            String query = "INSERT INTO "
                    + "OOO_ACCOUNT(OOOID,ACCOUNTID)"
                    + " VALUES (?,?)";
            PreparedStatement stmt = this.conSQL.getCon().prepareStatement(query);
            stmt.setInt(1, ooo.getOOOID());
            stmt.setInt(1, ooo.getACCOUNTID());

            stmt.executeUpdate();
            stmt.close();

            remove_deletedID(ooo.getOOOID(), ooo.getACCOUNTID());
        }
    }

    public boolean isExistID(OoO_Account ot) throws Exception {
        ArrayList<OoO_Account> list = selectAll();
        if (list != null) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getOOOID() == ot.getOOOID()
                            && list.get(i).getACCOUNTID() == ot.getACCOUNTID()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
