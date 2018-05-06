/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OoO.Bus;

import OoO.DAO.EggDAO;
import java.util.ArrayList;

/**
 *
 * @author DAT
 */
public class EggBus {
    public int updateHatchingTime_byEggID(
            ArrayList<java.util.Date> hatchingTime,
            ArrayList<String> eggID)
            throws Exception{
        EggDAO eggDAO = null;
        int result = 0;
        try{
            eggDAO = new EggDAO();
            result = eggDAO.updateHatchingDate_byEggID(hatchingTime, eggID);
            eggDAO.closeConnection();
        }
        catch(Exception e){
            throw e;
        }
        return result;
    }
    
    
}
