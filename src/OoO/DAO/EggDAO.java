/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OoO.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import OoO.database.Connectivity.*;
import OoO.DTO.*;
import OoO.Tools.TimeTool;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DAT
 */
public class EggDAO extends MapperDB {

    public EggDAO() throws NullPointerException {
        super();
    }

    /*
    * SQL STATEMENT
     */
    // UPDATE 
    protected int updateColumnValue_byColumnValue(
            String firstColName,
            String secColName,
            ArrayList<String> firstValue,
            ArrayList<String> secValue) {
        int result = 0;
        Connection temp = this.getConnection();
        if (temp != null
                || firstValue.size() != secValue.size()) {
            PreparedStatement preparedStatement = null;

            String createTabelStatement
                    = "update " + super.getSQLConnectivity().getDatabaseName() + ".EGG "
                    + "set ? = ? where ? = ? ";
            try {
                temp.setAutoCommit(false);
                preparedStatement = temp.prepareStatement(createTabelStatement);
                preparedStatement.setString(1, firstColName);
                preparedStatement.setString(3, secColName);

                for (int i=0;i<firstValue.size();i++) {
                    preparedStatement.setString(2, firstValue.get(i));
                    preparedStatement.setString(4, secValue.get(i));
                    preparedStatement.execute();
                    temp.commit();
                }
                result = 1;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.print("Transasction is being rolled back");
                try {
                    temp.rollback();
                    result = -1;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    result = -2;
                }
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    temp.setAutoCommit(true);
                } catch (SQLException excep) {
                    excep.printStackTrace();
                    result = -3;
                }
            }
        }
        return result;
    }

    public int updateHatchingTime_byEggID(
            ArrayList<java.util.Date> hatchingTime,
            ArrayList<String> eggID) {
        ArrayList<String> dataList = null;
        for(java.util.Date e: hatchingTime){
            SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm");
            dataList.add(sDateFormat.format(e));
        }
        return this.updateColumnValue_byColumnValue("HATCHINGTIME", "EGGID", dataList, eggID);
    }

    public int updateHatchingDate_byEggID(
            ArrayList<java.util.Date> hatchingDate,
            ArrayList<String> eggID) {
        ArrayList<String> dataList = null;
        for(java.util.Date e: hatchingDate){
            SimpleDateFormat sDateFormat = new SimpleDateFormat("dd:mm:YYYY");
            dataList.add(sDateFormat.format(e));
        }
        return this.updateColumnValue_byColumnValue("HATCHINGDATE", "EGGID", dataList, eggID);
    }
    
    public int updateEggName_byEggID(
            ArrayList<String> eggName,
            ArrayList<String> eggID) {
        return this.updateColumnValue_byColumnValue("EGGNAME", "EGGID", eggName, eggID);
    }

    public int updateEggParentID_byEggID(
            ArrayList<String> parentID,
            ArrayList<String> eggID) {
        return this.updateColumnValue_byColumnValue("PARENTID", "EGGID", parentID, eggID);
    }

    public int updateNextPreferredID_byEggID(
            ArrayList<String> nextPreferredID,
            ArrayList<String> eggID) {
        return this.updateColumnValue_byColumnValue("NEXTPREFERREDID", "EGGID", nextPreferredID, eggID);
    }

// SELECT 
    protected ArrayList<Egg> selectEgg(String byColumnName, String value) {
        Connection temp = this.getConnection();
        ArrayList<Egg> arrEgg = null;
        if (temp != null) {
            PreparedStatement preparedStament = null;
            StringBuffer sql = new StringBuffer();
            arrEgg = new ArrayList<Egg>();
            sql.append(" SELECT * FROM OoO.EGG ");
            sql.append(" WHERE ? = ? ");

            try {
                temp.setAutoCommit(false);
                preparedStament = temp.prepareStatement(sql.toString());
                preparedStament.setString(1, byColumnName);
                preparedStament.setString(2, value);

                ResultSet rs = preparedStament.executeQuery();
                while ((rs != null) && (rs.next())) {
                    arrEgg.add(new Egg(
                            rs.getNString("EGGID"),
                            rs.getNString("EGGNAME"),
                            rs.getNString("EGGDESCRIPTION"),
                            rs.getNString("PARENTID"),
                            rs.getNString("NEXTPREFERREDID"),
                            rs.getDate("HATCHINGDATE"),
                            TimeTool.getDate(
                                    rs.getDate("HATCHINGDATE"),
                                    rs.getTime("HATCHINGTIME"))
                    ));
                    
                }
                temp.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.print("Transasction is being rolled back");
                try {
                    temp.rollback();
                    return null;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            } finally {
                try {
                    if (preparedStament != null) {
                        preparedStament.close();
                    }
                    temp.setAutoCommit(true);
                } catch (SQLException excep) {
                    excep.printStackTrace();
                    return null;
                }
            }
        }
        return arrEgg;
    }

    public ArrayList<Egg> selectEgg_byEggID(String id) {
        return this.selectEgg("OoO.EGG.EGGID", id);
    }

    public ArrayList<Egg> selectEgg_byParentID(String id) {
        return this.selectEgg("OoO.EGG.PARENTID", id);
    }

    public ArrayList<Egg> selectEgg_byNextPreferredID(String id) {
        return this.selectEgg("OoO.EGG.NEXTPREFERREDID", id);
    }

    public ArrayList<Egg> selectEgg_byHatchingTime(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String timeValue = sdf.format(time);
        return this.selectEgg("OoO.EGG.HATCHINGTIME", timeValue);
    }

    public ArrayList<Egg> selectEgg_byHatchingDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:mm:YYYY");
        String dateValue = sdf.format(date);
        return this.selectEgg("OoO.EGG.HATCHINGTIME", dateValue);
    }

    // INSERT 
    protected int insertEgg(ArrayList<Egg> varlist) {
        int result = 0;
        Connection temp = this.getConnection();
        if (temp != null) {
            String dateValue;
            PreparedStatement preparedStament = null;
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO EGG ");
            sql.append(" VALUE ( ? , ? , ? , ? , ? , ? , ? )");

            try {
                temp.setAutoCommit(false);
                preparedStament = temp.prepareStatement(sql.toString());

                for (Egg e : varlist) {
                    preparedStament.setString(1, e.getEggID());
                    preparedStament.setString(3, e.getEggName());
                    preparedStament.setString(5, e.getNextPreferredID());
                    if (e.getEggDescription().isEmpty()) {
                        preparedStament.setString(2, "NULL");
                    } else {
                        preparedStament.setString(2, e.getEggDescription());
                    }

                    if (e.getParentID().isEmpty()) {
                        preparedStament.setString(4, "NULL");
                    } else {
                        preparedStament.setString(4, e.getParentID());
                    }

                    if (e.getHatchingDate() == null) {
                        preparedStament.setString(6, "NULL");
                    } else {
                        SimpleDateFormat sDateFormat = new SimpleDateFormat("dd:mm:YYYY");
                        dateValue = sDateFormat.format(e.getHatchingDate());
                        preparedStament.setString(6, dateValue);
                    }

                    if (e.getHatchingTime() == null) {
                        preparedStament.setString(7, "NULL");
                    } else {
                        SimpleDateFormat sTimeFormat = new SimpleDateFormat("HH:mm");
                        dateValue = sTimeFormat.format(e.getHatchingDate());
                        preparedStament.setString(7, dateValue);
                    }
                    preparedStament.execute();
                    temp.commit();
                }
                result = 1;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.print("Transasction is being rolled back");
                try {
                    temp.rollback();
                    result = -1;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    result = -2;
                }
            } finally {
                try {
                    if (preparedStament != null) {
                        preparedStament.close();
                    }
                    temp.setAutoCommit(true);
                } catch (SQLException excep) {
                    excep.printStackTrace();
                    result = -3;
                }
            }
        }
        return result;
    }

    // DELETE
    protected int deleteColumnValue_byColumnValue(
            String byColumnName, 
            String value) {
        int result = 0;
        Connection temp = this.getConnection();
        ArrayList<Egg> arrEgg = null;
        if (temp != null) {
            PreparedStatement preparedStament = null;
            StringBuffer sql = new StringBuffer();
            arrEgg = new ArrayList<Egg>();
            sql.append(" DELETE FROM OoO.EGG ");
            sql.append(" WHERE ? = ? ");

            try {
                temp.setAutoCommit(false);
                preparedStament = temp.prepareStatement(sql.toString());
                preparedStament.setString(1, byColumnName);
                preparedStament.setString(2, value);

                ResultSet rs = preparedStament.executeQuery();
                preparedStament.execute();
                temp.commit();
                result = 1;
            }catch (SQLException e) {
                e.printStackTrace();
                System.err.print("Transasction is being rolled back");
                try {
                    temp.rollback();
                    result = -1;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    result = -2;
                }
            } finally {
                try {
                    if (preparedStament != null) {
                        preparedStament.close();
                    }
                    temp.setAutoCommit(true);
                } catch (SQLException excep) {
                    excep.printStackTrace();
                    result = -3;
                }
            }
        }
        return result;
    }
    
    public ArrayList<Egg> deleteEgg_byEggID(String id) {
        return this.selectEgg("OoO.EGG.EGGID", id);
    }

    public ArrayList<Egg> deleteEgg_byParentID(String id) {
        return this.selectEgg("OoO.EGG.PARENTID", id);
    }

    public ArrayList<Egg> deleteEgg_byNextPreferredID(String id) {
        return this.selectEgg("OoO.EGG.NEXTPREFERREDID", id);
    }

    public ArrayList<Egg> deleteEgg_byHatchingTime(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String timeValue = sdf.format(time);
        return this.selectEgg("OoO.EGG.HATCHINGTIME", timeValue);
    }

    public ArrayList<Egg> deleteEgg_byHatchingDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:mm:YYYY");
        String dateValue = sdf.format(date);
        return this.selectEgg("OoO.EGG.HATCHINGTIME", dateValue);
    }

}
