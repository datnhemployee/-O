/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DAT
 */
public class SQLConnection {

    /**
     *
     */
    protected String username;

    /**
     *
     */
    protected String password;

    /**
     *
     */
    protected String database;

    /**
     *
     */
    protected String port;

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        try {
            if (!username.isEmpty()) {
                this.username = username;
            }
        } catch (NullPointerException eNull) {
            throw new NullPointerException(
                    "\n SQLConnectivity.setUsername(param1)"
                    + "\n The param must not be null."
                    + "\n System message: " + eNull);
        }
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        try {
            if (!password.isEmpty()) {
                this.password = password;
            }
        } catch (NullPointerException eNull) {
            throw new NullPointerException(
                    "\n SQLConnectivity.setPassword(param1)"
                    + "\n The param must not be null."
                    + "\n System message: " + eNull);
        }
    }

    /**
     *
     * @return
     */
    public String getDatabase() {
        return database;
    }

    /**
     *
     * @param database
     */
    public void setDatabase(String database) {
        try {
            if (!database.isEmpty()) {
                this.database = database;
            }
        } catch (NullPointerException eNull) {
            throw new NullPointerException(
                    "\n SQLConnectivity.setDatabase(param1)"
                    + "\n The param must not be null."
                    + "\n System message: " + eNull);
        }
    }

    /**
     *
     * @return
     */
    public String getPort() {
        return port;
    }

    /**
     *
     * @param port
     */
    public void setPort(String port) {
        try {
            if (!port.isEmpty()) {
                this.port = port;
            }
        } catch (NullPointerException eNull) {
            throw new NullPointerException(
                    "\n SQLConnectivity.setPort(param1)"
                    + "\n The param must not be null."
                    + "\n System message: " + eNull);
        }
    }

    private SQLConnection() {}

    @Override
    public String toString() {
        return "SQLConnection{" + "username=" + username + ", password=" + password + ", database=" + database + ", port=" + port + '}';
    }

    /**
     *
     * @param username specify the username of SQL Server.
     *
     * @param password specify the password of SQL Server.
     *
     * @param database specify the name of the database which use for the
     * system.
     *
     * @param port specify the port of database server
     * @throws Exception
     */
    public SQLConnection(
            String username,
            String password,
            String database,
            String port) throws Exception {
        try {
            if (!username.isEmpty()
                    && !password.isEmpty()
                    && !database.isEmpty()
                    && !port.isEmpty()) {
                this.username = username;
                this.password = password;
                this.database = database;
                this.port = port;
            }
            else{
                throw new Exception(
                    "\n It is imposible to initialize SQLConnectivity");
            }
        } catch (NullPointerException eNull) {
            throw new NullPointerException(
                    ConstantIndex.Instance.SQLConnectivity_NullInit
                    + "\n System message: " + eNull);
        } 
    }
}
