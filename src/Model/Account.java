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
public class Account {

    private int AccountID;
    private int AccountTypeID;
    private String URL;

    private Account() {
    }

    public Account(int AccountID, int AccountTypeID, String URL) {
        this.AccountID = AccountID;
        this.AccountTypeID = AccountTypeID;
        this.URL = URL;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public int getAccountTypeID() {
        return AccountTypeID;
    }

    public void setAccountTypeID(int AccountTypeID) {
        this.AccountTypeID = AccountTypeID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "Account{" + "AccountID=" + AccountID + ", AccountTypeID=" + AccountTypeID + ", URL=" + URL + '}';
    }

}
