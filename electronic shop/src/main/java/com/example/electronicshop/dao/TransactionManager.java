package com.example.electronicshop.dao;

import com.example.electronicshop.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager<E> {
    public E doInTransaction(TransactionOperation<E> transactionOperation, int transactionIsolation) {
        E result = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(transactionIsolation);
            result = transactionOperation.operation(connection);
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
            }
        }
        return result;
    }

    public E doWithoutTransaction(TransactionOperation<E> transactionOperation) {
        E result;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            result = transactionOperation.operation(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
