package com.example.electronicshop.utils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

    public static ThreadLocal<Connection> getConnectionThreadLocal() {
        return CONNECTION_THREAD_LOCAL;
    }

    public Connection getConnection() {
        Context ctx;
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null) {
            try {
                ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydatabase");
                connection = ds.getConnection();
                CONNECTION_THREAD_LOCAL.set(connection);
            } catch (NamingException | SQLException ex) {
                throw new IllegalStateException("not initialized ds ", ex);
            }
        }
        return connection;
    }
}