package com.example.electronicshop.dao;

import java.sql.SQLException;

public interface TransactionOperation<E> {
    E operation() throws SQLException;
}
