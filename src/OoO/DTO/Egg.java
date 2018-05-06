/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OoO.DTO;

import java.util.Date;


/**
 *
 * @author DAT
 */
public class Egg {

    private String eggID;
    private String eggDescription;
    private String eggName;
    private String ParentID;
    private String NextPreferredID;
    private Date hatchingDate;
    private Date hatchingTime;

    public Egg() 
        throws NullPointerException{
        throw new NullPointerException("Failed in laying empty egg.");
    }

//    public Egg(
//            String eggID, 
//            String eggName) {
//        this.eggID = eggID;
//        this.eggName = eggName;
//    }
//    
    
    public Egg(
            String eggID, 
            String eggName, 
            String eggDescription, 
            String ParentID, 
            String NextPreferredID, 
            Date hatchingDate, 
            Date hatchingTime) 
        throws NullPointerException{
        if(eggID == null 
                || eggName == null
                || NextPreferredID == null)
            throw new NullPointerException("Failed in laying empty egg.");
        this.eggID = eggID;
        this.eggDescription = eggDescription;
        this.eggName = eggName;
        this.ParentID = ParentID;
        this.NextPreferredID = NextPreferredID;
        this.hatchingDate = hatchingDate;
        this.hatchingTime = hatchingTime;
    }

    public String getEggID() {
        return eggID;
    }

    public void setEggID(String eggID) {
        this.eggID = eggID;
    }

    public String getEggDescription() {
        return eggDescription;
    }

    public void setEggDescription(String eggDescription) {
        this.eggDescription = eggDescription;
    }

    public String getEggName() {
        return eggName;
    }

    public void setEggName(String eggName) {
        this.eggName = eggName;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public String getNextPreferredID() {
        return NextPreferredID;
    }

    public void setNextPreferredID(String NextPreferredID) {
        this.NextPreferredID = NextPreferredID;
    }

    public Date getHatchingDate() {
        return hatchingDate;
    }

    public void setHatchingDate(Date hatchingDate) {
        this.hatchingDate = hatchingDate;
    }

    public Date getHatchingTime() {
        return hatchingTime;
    }

    public void setHatchingTime(Date hatchingTime) {
        this.hatchingTime = hatchingTime;
    }

}
