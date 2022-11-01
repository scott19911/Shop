package com.example.electronicshop.utils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    public static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();
    private static DataSource ds;
    public ConnectionPool(){
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydatabase");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ThreadLocal<Connection> getConnectionThreadLocal() {
        if (CONNECTION_THREAD_LOCAL.get() == null ){
            getConnection();
        }
        return CONNECTION_THREAD_LOCAL;
    }

    public static Connection getConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null) {
            try {
                connection = ds.getConnection();
                CONNECTION_THREAD_LOCAL.set(connection);
            } catch ( SQLException ex) {
                throw new IllegalStateException("not initialized ds ", ex);
            }
        }
        return connection;
    }
}