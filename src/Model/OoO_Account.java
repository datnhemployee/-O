/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DAT
 */
public class OoO_Account {
    private int OOOID;
    private int ACCOUNTID;

    private OoO_Account() {
    }

    public OoO_Account(int OOOID, int ACCOUNTID) {
        this.OOOID = OOOID;
        this.ACCOUNTID = ACCOUNTID;
    }

    public int getOOOID() {
        return OOOID;
    }

    public void setOOOID(int OOOID) {
        this.OOOID = OOOID;
    }

    public int getACCOUNTID() {
        return ACCOUNTID;
    }

    public void setACCOUNTID(int ACCOUNTID) {
        this.ACCOUNTID = ACCOUNTID;
    }

    @Override
    public String toString() {
        return "OoO_Account{" + "OOOID=" + OOOID + ", ACCOUNTID=" + ACCOUNTID + '}';
    }
    
    
}
