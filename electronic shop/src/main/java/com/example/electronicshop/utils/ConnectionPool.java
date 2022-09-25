package com.example.electronicshop.utils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static ConnectionPool instance = null;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Context ctx;
        Connection connection;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydatabase");
            connection = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            throw new IllegalStateException("not initializade ds ", ex);
        }
        return connection;
    }
}