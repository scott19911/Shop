package com.example.electronicshop.dao;

import com.example.electronicshop.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    public ConnectionPool pool;

    public TransactionManager(ConnectionPool pool) {
        this.pool = pool;
    }

    public<E>  E doInTransaction(TransactionOperation<E> transactionOperation, int transactionIsolation) {
        E result = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(transactionIsolation);
            result = transactionOperation.operation();
            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionPool.getConnectionThreadLocal().remove();
            }
        }
        return result;
    }

    public<E>  E doWithoutTransaction(TransactionOperation<E> transactionOperation) {
        E result;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            result = transactionOperation.operation();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionPool.getConnectionThreadLocal().remove();
            }
        }
        return result;
    }
}
