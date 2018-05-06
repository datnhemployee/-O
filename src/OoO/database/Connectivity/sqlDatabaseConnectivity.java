/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OoO.database.Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DAT
 */
    
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.imageio.ImageIO;

public class sqlDatabaseConnectivity {

    private String databaseName;
    private String username;
    private String password;
    private String port;

    public sqlDatabaseConnectivity() {
        this.databaseName = "master";
        this.databaseName = "1433";
    }

    public sqlDatabaseConnectivity(
            String databaseName,
            String username,
            String password,
            String port
    ) throws NullPointerException {
        if (databaseName == null
                || port == null) {
            throw new NullPointerException(
                    "\n Database name and the port must be not equal to null."
                    + "Try again, please.");
        } else {
            if (!databaseName.equals("master")
                    && username != null
                    && password != null) {
                this.databaseName = databaseName;
                this.port = port;
                this.username = username;
                this.password = password;
            } else {
                this.databaseName = "master";
                this.port = port;
            }
        }
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(String port) {
        this.port = port;
    }

    protected boolean loadJDBCDriver(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return true;
        }
        catch(ClassNotFoundException e){
            System.err.println("SQL JDBC Driver not found ...");
            return false;
        }
    }
    
    public Connection getConnection(){
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://"
                + "localhost:" + port + ";"
                + "username=" + username + ";"
                + "password=" + password + ";"
                + "databaseName=" + databaseName + ";";

        try {
            // Establish the connection.
            if(this.loadJDBCDriver())
                return DriverManager.getConnection(connectionUrl);
            return null;
        } // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
