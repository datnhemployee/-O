/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DAT
 */
public class SQLConnectivity {

    private Connection con;
    private SQLConnection conSQL;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    private SQLConnectivity() {
    }

    public SQLConnectivity(SQLConnectivity con) throws Exception {
        this.conSQL = con.getConSQL();
        this.con = con.getCon();
        connect();
    }

    public SQLConnectivity(SQLConnection conSQL) throws Exception {
        this.conSQL = conSQL;
        connect();
    }

    public SQLConnection getConSQL() {
        return conSQL;
    }

    public void setConSQL(SQLConnection conSQL) {
        this.conSQL = conSQL;
    }

    protected void loadJDBCDriver()
            throws ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(
                    "\n SQLConnectivity.loadJDBCDriver can not load JDBC Driver ..."
                    + "\n System message: " + e);
        }
    }

    public void connect()
            throws Exception {
        if (this.con != null) {
            return;
        }
        String SQLConnection = "jdbc:sqlserver://"
                + "localhost:" + this.conSQL.getPort() + ";"
                + "username=" + this.conSQL.getUsername() + ";"
                + "password=" + this.conSQL.getPassword() + ";"
                + "databaseName=" + this.conSQL.getDatabase() + ";"
                + "sendTimeAsDateTime = " + false;
        this.loadJDBCDriver();
        this.con = DriverManager.getConnection(SQLConnection);

    }

    public void close() {
        try {
            this.con.close();
        } catch (SQLException eSQL) {
            System.err.printf(""
                    + "\n SQLConnectivity.closeConnection() can not close connection."
                    + "\n System message: " + eSQL);
        }
    }

    @Override
    public String toString() {
        return "SQLConnectivity{" + "con=" + con + ", conSQL=" + conSQL + '}';
    }
}
