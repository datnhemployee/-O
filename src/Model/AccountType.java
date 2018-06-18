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
public class AccountType {
    private int AccountTypeID;
    private String Name;

    public AccountType(int AccountTypeID, String Name) {
        this.AccountTypeID = AccountTypeID;
        this.Name = Name;
    }

    public AccountType() {
    }

    public int getAccountTypeID() {
        return AccountTypeID;
    }

    public void setAccountTypeID(int AccountTypeID) {
        this.AccountTypeID = AccountTypeID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "AccountType{" + "AccountTypeID=" + AccountTypeID + ", Name=" + Name + '}';
    }
    
    
}
