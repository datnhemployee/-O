/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OoO.database.Connectivity;

import java.sql.Connection;
/**
 *
 * @author DAT
 */
public class MapperDB {
    private Connection Connection;
    private sqlDatabaseConnectivity SQLConnectivity;

    public MapperDB()
        throws NullPointerException {
        try{
        SQLConnectivity = new sqlDatabaseConnectivity(
        "OoO",
        "sa",
        "123456",
        "1433");
        Connection = SQLConnectivity.getConnection();
        if(Connection == null)
            System.out.println("Failed in constructor in MapperDB");
        }
        catch(NullPointerException e){
             System.out.println(e);
        }
    }
    
    public void closeConnection() throws Exception{
        try{
            this.getConnection().close();
        }catch(Exception e){
            System.out.println("Failed in closeOperation method in MapperDB: "+e);
        }
    }
    
    public Connection getConnection(){
        return this.Connection;
    }
    
    public sqlDatabaseConnectivity getSQLConnectivity(){
        return SQLConnectivity;
    }
}
